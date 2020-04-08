package cn.ltcraft;

import cn.ltcraft.rcon.Rcon;
import cn.ltcraft.rcon.ex.AuthenticationException;
import net.mamoe.mirai.console.command.*;
import net.mamoe.mirai.console.plugins.Config;
import net.mamoe.mirai.console.plugins.PluginBase;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class Main extends PluginBase {
    private Config config;
    private Rcon rcon;
    public static Boolean isPE = true;

    public void onLoad(){
        config = this.loadConfig("config.yml");
        config.setIfAbsent("serverAdder填写", "服务器地址,填写你服务器地址即可");
        config.setIfAbsent("serverAdder", "");
        config.setIfAbsent("serverPort填写", "服务器端口,填写你服务器端口即可");
        config.setIfAbsent("serverPort", 0);
        config.setIfAbsent("passworld填写", "服务器Rcon密码 在server.properties文件内找到rcon.password");
        config.setIfAbsent("passworld", "");
        config.setIfAbsent("isPe填写", "如果是PE服务器填写true 如果是pc服务器填写false");
        config.setIfAbsent("isPe", true);
        config.setIfAbsent("怎么开启Rcon", "在server.properties找到enable-rcon=off改为on即可!不行自行百度.");
        config.setIfAbsent("注意", "填写完成重载一下即可/reload");
        config.save();
        super.onLoad();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (rcon!=null){
            try {
                rcon.disconnect();
                rcon = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onEnable(){
        if (!config.getString("serverAdder").equals("") && config.getInt("serverPort")!=0 && !config.getString("passworld").equals("")) {
            isPE = config.getBoolean("isPe");
            if (!connected()){
                getLogger().info("连接Rcon服务器失败！请检查密码和服务器地址连通性。");
                return;
            }
            JCommandManager.getInstance().register(this, new BlockingCommand(
                    "c", new ArrayList<>(), "向服务器发送命令用法/c 命令", "/c 命令"
            ) {
                @Override
                public boolean onCommandBlocking(@NotNull CommandSender commandSender, @NotNull List<String> list) {
                    if (rcon!=null){
                        String command = utils.listToString(list, ' ');
                        try {
                            commandSender.sendMessageBlocking(command(command));
                        } catch (IOException e) {
                            getLogger().info("重新连接...");
                            if (!connected()){
                                commandSender.sendMessageBlocking("连接Rcon服务器失败！请检查密码和服务器地址连通性。");
                                return true;
                            }
                            try {
                                commandSender.sendMessageBlocking(command(command));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                commandSender.sendMessageBlocking("执行失败！");
                            }
                        }
                    }
                    return true;
                }
            });
            super.onEnable();
        }
    }
    public String command(String command) throws IOException {
        String results = rcon.command(command);
        if (results.length()>0) {
            results = utils.clean(results);
            return results;
        }else {
            return "执行成功！服务器返回空！";
        }
    }
    public boolean connected(){
        try {
            getLogger().info("连接"+config.getString("serverAdder")+":"+config.getInt("serverPort")+"...");
            rcon = new Rcon(config.getString("serverAdder"), config.getInt("serverPort"), config.getString("passworld").getBytes());
            return true;
        } catch (IOException| AuthenticationException e) {
            e.printStackTrace();
            return false;
        }
    }
}