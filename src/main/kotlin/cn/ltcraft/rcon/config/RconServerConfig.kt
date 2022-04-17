package cn.ltcraft.rcon.config

import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.PluginDataExtensions.mapKeys
import net.mamoe.mirai.console.data.PluginDataExtensions.withEmptyDefault
import net.mamoe.mirai.console.data.value

/**
 * Created by Angel、 on 2022/4/17 17:54
 */
class RconServerConfig(saveName: String) : AutoSavePluginData(saveName) {
    var remarks: String by value()
    var serverAddress: String by value()
    var serverPort : Int by value()
    var password : String by value()
    var licensor : List<Long> by value(mutableListOf(2665337794))
    var prefixes : List<String> by value(mutableListOf("\\", "!"))
}