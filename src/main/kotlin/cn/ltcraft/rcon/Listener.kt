package cn.ltcraft.rcon

import cn.ltcraft.rcon.rcon.Rcon
import net.mamoe.mirai.event.GlobalEventChannel.subscribeAlways
import net.mamoe.mirai.event.events.MessageEvent
import java.util.regex.Pattern

/**
 * Created by Angel、 on 2022/4/17 18:18
 */
object Listener {
    fun registerEvents(){
        subscribeAlways<MessageEvent> {
            for (rcon in Main.rcons) {
                if (
                    rcon.prefixes.contains(message.contentToString().substring(0, 1)) &&
                    rcon.licensor.contains(sender.id)
                ) {
                    val rconServer = Rcon(rcon)
                    if (rconServer.isFail()){
                        subject.sendMessage("连接" + rcon.serverAddress + ":" + rcon.serverPort + "失败！请检查密码和服务器地址连通性。")
                        continue//如果玩家设置了多个服务器
                    }
                    val result = rconServer.command(message.contentToString().substring(1).trim())
                    if (result.isEmpty() || result.clean().isEmpty()){
                        subject.sendMessage("执行完成，服务器无返回！")
                    }
                    subject.sendMessage(result.clean())
                }
            }
        }
    }

    /**
     * 清理§x 颜色标签
     */
    private fun String.clean() = Pattern.compile("(?i)" + "\u00A7" + "[0-9A-FK-OR]").matcher(this).replaceAll("")

}