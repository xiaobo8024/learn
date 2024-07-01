下载mysq:

https://downloads.mysql.com/archives/community/

创建MySQL用户组和用户，并赋予相应的权限：

```
groupadd mysql
useradd -r -g mysql -s /bin/false mysql
chown -R mysql:mysql /usr/local/mysql
```

初始化数据库：

```text
./bin/mysqld --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data --initialize
```

安装数据库依赖

```text
yum install libaio*
```

修改my.cnf文件将datadir修改到自己的目录下，如：/usr/local/mysql。socket改为/tmp/mysql.sock

```
#默认自动在etc目录下生成my.cnf
vim /etc/my.cnf
# my.cnf文件内容
[mysqld]
datadir=/usr/local/mysql/data
socket=/tmp/mysql.sock
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0
# Settings user and group are ignored when systemd is used.
# If you need to run mysqld under a different user or group,
# customize your systemd unit file for mariadb according to the
# instructions in http://fedoraproject.org/wiki/Systemd

[mysqld_safe]
log-error=/var/log/mariadb/mariadb.log
pid-file=/var/run/mariadb/mariadb.pid

#
# include all files from the config directory
#
!includedir /etc/my.cnf.d
```

创建日志文件后，赋予相应的权限

```
mkdir /var/log/mariadb/
touch /var/log/mariadb/mariadb.log
chown -R mysql.mysql /var/log/mariadb/mariadb.log
```

安装完成后，我们可以使用以下命令启动MySQL服务：

```
./support-files/mysql.server start
```

配置mysql服务开机自动启动

```
# 拷贝启动文件到/etc/init.d/下并重命令为mysqld
cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysqld
# 增加执行权限 
chmod 755 /etc/init.d/mysqld                                           
# 查自启动项列表中没有mysqld这个
chkconfig --list mysqld    
# 如果没有就添加mysqld                                           
chkconfig --add mysqld
# 用这个命令设置开机启动                                              
chkconfig mysqld on 
```

系统环境配置

```
vim /etc/profile
# 在profile最下面填写以下信息
export MYSQL_HOME=/usr/local/mysql
export PATH=$MYSQL_HOME/bin:$PATH
# 刷新配置文件
source /etc/profile
```

MySQL连接与操作

```
mysql -u root -p
```

MySQL登录之后，设置远程连接密码，并刷新配置。在终端中输入以下命令：

```
# 刷新
FLUSH PRIVILEGES;
# 设置远程登录密码:123456替换成你的密码
grant all privileges on *.* to root@'%' identified by '123456';
# 再次刷新
FLUSH PRIVILEGES
```



忘记密码：

```
mysqld_safe --skip-grant-tables &
UPDATE mysql.user SET authentication_string = PASSWORD('new_password') WHERE User = 'root';
FLUSH PRIVILEGES;
```

