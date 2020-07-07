# Rcon插件

本项目是[Mirai](https://github.com/mamoe/mirai)机器人的插件，使用[RCON](https://wiki.vg/RCON)协议，与Minecraft服务器建立连接，从而在QQ群内执行服务器命令。

可以添加多个配置文件，通过多个QQ群管理多个Minecraft服务器！

## 插件用法

### 安装

下载Release中的jar文件，复制到Mirai的plugins文件夹中；在Mirai控制台中输入`reload`命令，或者重启Mirai，以加载插件。

### Minecraft服务器设置

修改`server.properties`中如下三行：

```
enable-rcon=true
rcon.password=
rcon.port=25575
```

将`enable-rcon`对应的值改为`true`以启用RCON协议，设置密码`rcon.password`，以及端口`rcon.port`。

### 插件设置

1. 在Mirai控制台中输入`rcon add 群号`，添加一个QQ群。
2. 修改Mirai目录下`plugins/RCON`文件夹中，以群号为文件名的配置文件。其中：
    * `serverAdder`是Minecraft服务器的地址；
    * `serverPort`是RCON协议的端口，与`server.properties`中的`rcon.port`相同；
    * `passworld`是RCON协议的密码，与`rcon.password`相同；
    * `groupID`是群号；
    * `canPerform`为列表，列表中的QQ号将有执行RCON命令的权限。
3. 在Mirai控制台中输入`rcon reload`以加载配置。

### 使用

在RCON命令前加上`\`，发送到QQ群里。比如发送`\list`以显示在线玩家列表。
