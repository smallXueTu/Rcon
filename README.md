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
2. 修改Mirai目录下`plugins/RCON`文件夹中，以群号为文件名的配置文件。
3. 在Mirai控制台中输入`rcon reload`以加载配置。

配置文件中各项说明如下：

```yaml
# 命令前缀。列表中的任意一项开头的信息会被识别为RCON命令，去掉前缀后发送到Minecraft服务器。
# 比如，在群里发送#list，将执行list指令；发送\help，将执行help指令。
prefixes:
- \
- '#'

# Minecraft服务器地址
serverAddr: '127.0.0.1'

# Minecraft RCON协议的端口，与server.properties中的rcon.port一致
serverPort: 25575

# RCON协议的密码，与server.properties中的rcon.password一致
password: 'PaperMC'

# 对应的QQ群号
groupID: 716868667

# 该群中可以执行RCON命令的群成员的QQ号
canPerform:
- 123456
```

### 使用

在RCON命令前加上配置文件中`prefixes`中的任意一项，发送到QQ群里。比如发送`\list`或者`#list`以显示在线玩家列表。

## 如何构建

参考：[如何开发Mirai-console插件](https://github.com/mamoe/mirai-console/blob/reborn/PluginDocs/ToStart.MD)
