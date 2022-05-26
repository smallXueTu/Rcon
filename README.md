# Rcon插件

本项目是[Mirai](https://github.com/mamoe/mirai)机器人的插件，使用[RCON](https://wiki.vg/RCON)协议，与Minecraft服务器建立连接，从而在QQ群内执行服务器命令。

可以添加多个配置文件，通过多个Minecraft服务器！

## 插件用法

### 安装

下载Release中的jar文件，复制到Mirai的plugins文件夹中；重启Mirai，以加载插件。

### Minecraft服务器设置

修改`server.properties`中如下三行：

```
enable-rcon=true
rcon.password=你的密码
rcon.port=25575
```

将`enable-rcon`对应的值改为`true`以启用RCON协议，设置密码`rcon.password`，以及端口`rcon.port`。

### 插件设置

1. 在Mirai控制台中输入`rcon add 备注`，添加一个Rcon服务器。
2. 修改Mirai目录下`data/cn.ltcraft.rcon`文件夹中，以备注为文件名的配置文件。
3. 在Mirai控制台中输入`rcon reload`以加载配置。

配置文件中各项说明如下：

```yaml
#服务器不会加载示例.yml 此文件仅供参考
#请在控制台使用[/rcon add 备注]来新建一个配置文件
#备注：
remarks: test
#Rcon服务器地址 如：
serverAddress: play.ltcraft.cn
#Rcon服务器端口 如：
serverPort: 19132
#Rcon服务器密码 如：
password: 132456
#执行的前缀 只能是一个字符！
#只有开头为以下字符才会被当做命令解析：
prefixes:
  - \
  - !
#可执行的用户QQ号：
licensor:
  - 2665337794
```
### 使用

配置文件`prefixes`中的任意一项，作为前缀发送到QQ群里。比如`\list`或者`!list`以执行list命令。

<i>tip：可以通过不同的前缀管理不同的Minecraft服务器，设为相同的一下管理多个Minecraft服务器。</i>

## 如何构建
打开cmd或者shell
<br />
cd到项目所在目录
<br />
执行：
```
./gradlew clean buildPlugin
```
然后复制 build/mirai/rcon-xxx.jar到你Mirai的plugins下即可

## 详见：
[Mirai开发手册](https://github.com/mamoe/mirai/blob/dev/docs/README.md)
