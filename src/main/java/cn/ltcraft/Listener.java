package cn.ltcraft;

import cn.ltcraft.rcon.Rcon;
import java.io.IOException;
import net.mamoe.mirai.message.GroupMessage;

public class Listener {
    private Main plugin;

    public Listener(Main plugin) {
        this.plugin = plugin;
        this.registerEvents();
    }
    private void registerEvents() {
        this.plugin.getEventListener().subscribeAlways(GroupMessage.class, (event) -> {
            String message = event.getMessage().contentToString();
            if (message.startsWith("\\")) {
                Rcon rcon = (Rcon)this.plugin.getRcon().get(event.getGroup().getId());
                if (rcon != null && rcon.getConfig().getLongList("canPerform").contains(event.getSender().getId())) {
                    message = message.substring(1, message.length());

                    try {
                        event.getSubject().sendMessage(this.plugin.command(message, rcon));
                    } catch (IOException e) {
                        this.plugin.getLogger().info("重新连接" + rcon.getConfig().getString("serverAdder") + ":" + rcon.getConfig().getInt("serverPort") + "...");
                        try {
                            rcon.disconnect();
                        } catch (IOException ex) {

                        }
                        Rcon rcon1 = this.plugin.connected(rcon.getConfig());
                        if (rcon1 == null) {
                            event.getSubject().sendMessage("连接Rcon服务器失败！请检查密码和服务器地址连通性。");
                        } else {
                            try {
                                this.plugin.getRcon().put(event.getGroup().getId(), rcon1);
                                event.getSubject().sendMessage(this.plugin.command(message, rcon1));
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                event.getSubject().sendMessage("执行失败！");
                            }
                        }
                    }
                }
            }

        });
    }
}
