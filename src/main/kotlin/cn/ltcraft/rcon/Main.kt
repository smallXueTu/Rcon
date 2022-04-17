package cn.ltcraft.rcon

import cn.ltcraft.rcon.config.RconServerConfig
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.permission.PermissionService
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info
import java.io.File
import java.nio.file.Files


/**
 * Created by Angel、 on 2022/4/17 17:07
 */
object Main : KotlinPlugin(
    JvmPluginDescription(
        id = "cn.ltcraft.rcon",
        version = "2.0.0",
    ) {
        author("Angel、")
    }
) {
    val LTCraft_Rcon_Man by lazy {
        PermissionService.INSTANCE.register(permissionId("ltcraft.rcon"), "Rcon管理权限")
    }
    override fun onEnable() {
        logger.info { "Rcon插件已加载！" }

        Command.register()

        Listener.registerEvents()
        checkExamples()
        reloadAllRconServerConfig()
        LTCraft_Rcon_Man

    }

    /**
     * 插件卸载
     */
    override fun onDisable() {
        Command.unregister()
    }

    /**
     * 重新加载Rcon配置文件
     */
    fun reloadAllRconServerConfig(){
        rcons.clear()
        for (listFile in dataFolder.listFiles()!!) {
            if (listFile.name == "." || listFile.name == ".." || listFile.name.startsWith("示例")) continue
            rcons.add(RconServerConfig(listFile.name.substring(0, listFile.name.length - 4)))
        }
        rcons.forEach {
            it.reload()
        }
    }

    /**
     * 检查示例文件
     */
    private fun checkExamples(){
        val file = File(dataFolder.path + "/示例.yml")
        if (!file.exists()) {
            val inputStream = getResourceAsStream("示例.yml") ?: return
            Files.copy(inputStream, file.toPath())
        }
    }
    val rcons :MutableList<RconServerConfig> = ArrayList()
}