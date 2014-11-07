#Angel
###中文描述
####功能列表
1. 如何使用
    从github上下载，dist目录为发布目录，在WEB-INF/classes/Redis-Server-Config.xml文件中修改配置信息，文件中已经有配置示例，修改相应的ip地址与端口即可，虽然工具中有可以动态添加节点的功能，但还是必须先手动添加一个，这算是一个bug，以后会修改掉。
    改完配置文件，运行dist/bin/start.bat就可以启动内嵌的jetty服务器默认端口为8888，在浏览器中输入：http://127.0.0.1:8888即可以看到redis的信息
2. 功能介绍
    1. 当成功运行后，进入的首页面就redis运行信息，侧显示是的命令info的信息，右侧上图显示的是内存点用情况，中图是key的数量，最下面是当前redis中执行较慢的命令，右上方的flushAll,flushDB按钮，请谨慎操作，会清空redis中的内容
    2. key管理页面，可以用通配符搜索key，点击相应的key可以在右侧显示key的值，如果是string类型，可以进行json，xml格式化，也可以进行修改和删除操作
    3. redis configuration 管理页面，显示当前redis配置信息，现在还没有修改这些配置的功能，陆续会加上
    4. redis 节点管理功能，可以动态的添加redis，方便切换到要监控的redis上面
    5. 日志监控页面，实时的监控redis命令执行日志，为了不影响redis的性能，有些日志可能会丢失。
    
###English
1. Install
    * download source code from github ,
    * modify ip and port in file of "dist/WEB-INF/classes/Redis-Server-Config.xml" 
    * execute dist/bin/start.bat script will start a embedded jetty server on default prot 8888,and then visit http://127.0.0.1:8888 with chrome or firefox browser
2. Introduction
    1. we will see the home page if everything goes well ,  that show the runtime infomation like keys number , memery use and so on , be careful when use flushAll or flushDB button , it will clear redis.
    2. keys page , search keys with wildcard , update , delete , format if the type of value is String .
    3. redis configuration , this page just show the configuration not allow be modify , it will be implement in future .
    4. redis node page , add , delete ,update node of redis dynamically
    5. console page , print executed order log , some log will lost for not greatly impact performance