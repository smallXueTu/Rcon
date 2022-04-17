package cn.ltcraft.rcon

import cn.ltcraft.rcon.Main.save
import cn.ltcraft.rcon.config.RconServerConfig
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.console.permission.PermissionService.Companion.hasPermission

/**
 * Created by Angel、 on 2022/4/17 17:28
 */
object Command : SimpleCommand(
    Main, "rcon",
    description = "Rcon主命令"
) {
    @Handler
    suspend fun CommandSender.handle(vararg args: String) {
        if (this.hasPermission(Main.LTCraft_Rcon_Man).not()) {
            sendMessage(
                """
                你没有 ${Main.LTCraft_Rcon_Man.id} 权限.
                可以在控制台使用 /permission 管理权限.
            """.trimIndent()
            )
        }else {
            val remarks: String
            when(args[0]){
                "add" -> {
                    if (args.size < 2) {
                        sendMessage("/rcon add 备注")
                        return
                    }
                    remarks = args[1]
                    val config = RconServerConfig(remarks)
                    config.remarks = remarks
                    config.save()
                    sendMessage("添加成功,请去data/cn.ltcraft.rcon/$remarks.yml 修改配置,然后/rcon reload 重载配置。")
                }
                "reload" -> {
                    Main.reloadAllRconServerConfig()
                    sendMessage("重载完成！")
                }
                else -> {
                    sendMessage("添加群/rcon add 群号\n" +
                            "重载/rcon reload")
                }
            }
        }
    }
}