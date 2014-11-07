
@echo off
@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT"  setlocal

set ENV_PATH=.\
if "%OS%" == "Windows_NT" set ENV_PATH=%~dp0%

set conf_dir=%ENV_PATH%\..\conf
set webapp_dir=%ENV_PATH%\..\
set monitor_conf=%conf_dir%\config.properties


set CLASSPATH=%webapp_dir%;%conf_dir%;%conf_dir%\..\lib\*;%CLASSPATH%

set JAVA_MEM_OPTS= -Xms128m -Xmx512m -XX:PermSize=128m
set JAVA_OPTS_EXT= -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dapplication.codeset=UTF-8 -Dfile.encoding=UTF-8

set JAVA_OPTS= %JAVA_MEM_OPTS% %JAVA_OPTS_EXT%

set CMD_STR= java %JAVA_OPTS% -classpath "%CLASSPATH%" java %JAVA_OPTS% -classpath "%CLASSPATH%"  com.redis.monitor.server.jetty.JettyEmbedServer
echo start cmd : %CMD_STR%

java %JAVA_OPTS% -classpath "%CLASSPATH%" com.redis.monitor.server.jetty.JettyEmbedServer
pause