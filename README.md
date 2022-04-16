校园空闲时间预约

## 测试账号

管理员：admin	密码：admin

学生：111		密码：1111

教师：222		密码：2222
    
## 甘特图

```mermaid
gantt
    title 校园空闲时间预约第一次迭代
    section 准备
        搭建java本地开发环境    :done,   a1, 2018-04-07, 2d
        数据库设计连接          :done,   a2, after a1,   1d
    section 基本核心功能
        可预约时间展示          :        b1, 2018-04-15, 2d
        预约时间发布            :        b2, after b1,   1d
    section 拓展
        注册登录                :        c1, after b2,   1d
        banner                  :        c2, after c1,   1d
```

---
## 工程目录结构

- ~~功能/逻辑视图~~
- ~~开发/结构视图~~
- ~~用户动作/反馈视图~~

### MVC 模式

MVC 模式中业务逻辑相关的部分无法单独处理，但 service+serviceImpl、dao+daoImpl 又太冗余，于是将其单独列为业务逻辑层：前端页面（View）、控制层（Controller）、业务逻辑层（Service）、数据持久层（Model）。

==Model 中数据的变化一般会通过一种刷新机制被公布。为了实现这种机制，那些用于监视此 Model 的 View 必须事先在此 Model 上注册，从而，View 可以了解在数据 Model 上发生的改变。==

[Spring Boot 通过 JPA 连接 MySQL](http://www.aidansu.com/2017/spring-boot-mysql-jpa/)

 [Spring Boot 快速开发 REST 服务最佳实践](https://www.cnblogs.com/jeffwongishandsome/p/quick-develop-rest-api-by-using-spring-boot.html)

[关于SSH架构中Entity/Dao/Service/Controller的理解](https://www.zybuluo.com/Beeder/note/1053661)

### src/main/java/com.campus 根目录：后台

> ==错误：== 找不到主类
>
> 解决：不应将 Application.java 和其它所有文件放在 src/main/java 下，要将它放在src/main/java下的根包里（例如 src/main/java/com.campus 下）。

#### 公共模块

common：公共类，如枚举、常量、业务无关的通用公共实体等
common.utils：常用实用的帮助类，如反射、字符串、集合、枚举、正则、缓存、队列等

#### 控制层

controller：用于组织不同层面，控制流程，页面访问控制和交互，==对外暴露 Rest API 接口==  。路由解析、页面跳转、展示表现层、接收并转发原始数据和请求、调用 service 层处理事件。处理请求和响应事件，“事件”包括用户的行为和数据的改变。

#### 数据持久层

Entity：实体层，用对象映射数据表，二者一一对应，本质是数据表的对象化。是一种 ORM 对象关系映射。
==repository==：==通过对 Entity 层的封装提供 CURD 接口== 。继承 JpaRepository。[Spring Boot 中 Repository 的使用](https://segmentfault.com/a/1190000012346333) [Repository（资源库）接口介绍](http://perfy315.iteye.com/blog/1460226) 

#### 业务逻辑层

service：用于处理事件，实现具体的业务逻辑。

#### 程序入口

Application.java：包括一个静态 main 方法，可以做一些框架配置，比如==mybatis、swagger==等，作为 Spring boot 的启动配置。

### resources 目录：前端页面

#### static : 静态文件

css : CSS 样式
img : 图片
js : js 文件

#### templates : 模板文件，主要是 html 文件

admin：后台管理页面
freetime：预约相关页面
myspace：个人中心
user：用户页面
index.html：主页

#### 配置文件

> Spring Boot 支持 .properties 格式和 .yml 格式配置文件。yml 类型文件属性名后面冒号和值之间必须有一个空格。

application.properties : Spring Boot 自动加载的配置文件，可在以下环境切换
application-dev.properties 开发环境
application-prod.properties 生产环境
application-test.properties 测试环境

### ==src/test/java  测试程序==

### 其它文件

Spring Element

@Autowired 注解与自动装配，自定义 bean

DB：存放数据库文件

[Spring Boot在idea上集成MySQL数据库 以及在idea上配置MySQL](https://blog.csdn.net/cui_yonghua/article/details/92835824)

target：注解

pom.xml：Maven [项目对象模型](https://zh.wikipedia.org/wiki/%E9%A1%B9%E7%9B%AE%E5%AF%B9%E8%B1%A1%E6%A8%A1%E5%9E%8B)（Project Object Model，POM）。主要描述项目的配置文件、开发者需要遵循的规则、缺陷管理系统、组织和licenses、项目的url、项目的依赖性、以及其他所有的项目相关因素。 [Maven pom.xml 配置详解](https://blog.csdn.net/ithomer/article/details/9332071)

README.md：项目说明

spring-boot-jpa.iml：

--------------------

## 准备

### 安装 Eclipse for Java Web Developer

#### 下载 JDK（Java Development Kit）

[SE JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)，选择`Java Platform (JDK) 10`（包含 Java 
Runtime Environment），下载到`\Java\JDK`

>[EE JDK](http://www.oracle.com/technetwork/java/javaee/downloads/index.html)下载下来是`glassfish`格式，==弄不太懂==，发现安装`SE JDK`也是一样的

#### 设置环境变量

1. `我的电脑`->右键`属性`->`高级系统设置`->`环境变量`，下同
2. 新建***系统变量*** `JAVA_HOME`，并赋值JDK的安装路径，如：
    `D:\Develop\Java\jdk1.8.0_161`
3. 在***系统变量*** `PATH`后***追加***
    `;%JAVA_HOME%\bin;%JAVA_HOME%\jre;`

#### 下载 Eclipse 安装包

[eclipse-inst-win64.exe](https://www.eclipse.org/downloads/)
#### 运行安装软件，选择`Eclipse for Java Web Developer`并安装

>推荐所有Java相关软件全部都安装到非系统盘同一目录`\Java`下，如：
>`D:\Develop\Java`

#### 汉化

1. [Eclipse Babel Project Downloads](http://www.eclipse.org/babel/downloads.php) 
    ->Babel Language Pack Zips
    ->对应版本（如Oxygen）
    ->Language: Chinese (Simplified)
    ->[BabelLanguagePack-eclipse-zh_4.7.0.v20171231020002.zip (85.5%)](http://www.eclipse.org/downloads/download.php?file=/technology/babel/babel_language_packs/R0.15.1/oxygen/BabelLanguagePack-eclipse-zh_4.7.0.v20171231020002.zip)
2. 将`features`和`plugins`解压到`\Eclipse\dropins\`下。
3. 重启Eclipse

---

### Eclipse 快捷键

| 快捷键         | 功能 |
| -------------- | ---- |
| ---编辑相关--- |      |
|自动补全	| `alt + /`	|
|注释               | `Ctrl + /`	|
|快速修复           | `Ctrl + 1`	|
|删除当前行         | `Ctrl + d`|
|格式化文档         | `Ctrl + Shift + f`|
|插入空行           | `Shift + Enter`|
|自动生成 get set 方法  |  `alt + shift + S`|
|显示大纲           | `Ctrl + o`|
|---窗口相关---     | |
|编辑窗口最大化     | `Ctrl + m`|
|切换编辑器/控制台/资源管理器/Servers| `ctrl + F7`|
|---查看，定位---| |
|查找替换           | `ctrl + F`|
|重构、重命名       | `Alt+Shift+R`|
|查找 Java 类文件     | `Ctrl + Shift + T `|
|查找引用           | `Ctrl+Shift+G`|
|后退历史记录       | `Alt+ ←、Alt+ →`|
|快速查找选定的内容 | `Ctrl+K`|

---
### ~~安装 Tomcat~~

#### 下载安装包

[Tomcat 9](https://tomcat.apache.org/download-90.cgi)，选择`core`下的`32/64-bit Windows zip (pgp, sha1, sha512)`，解压到`\Java\Tomcat`

#### 设置 Tomcat

1. 在 Eclipse 中，点击菜单栏 `Window->Preferences`，点击 `Server / Runtime Environments`
2. 点击 `Add`按钮， 新建一个服务器，选择`Tomcat 9.0`->`Next`
3. 在`Tomcat installation directory`->`Browse`选择Tomcat的安装目录，点击`finish`完成配置。

---
### ~~新建第一个 Java Web 项目并部署在 Tomcat 服务器~~

#### 新建 Java Web 项目

1. 新建->`Dynamic Web Project`
2. `Target runtime`选择安装的服务器（如：`Apache Tomcat v9.0`）
3. `module version`选择默认`3.1`，其他保持默认，`finish`。

#### 新建第一个 html 页面

在`\WebContent`目录下新建`Index.html`
```
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Hello World!<br>
This is CampusDate/WebContent/Index.html!
</body>
</html>
```
#### 部署在 Tomcat 服务器

在 Index.html 上右键->`Run as`->`Run on Server`.

---

### 安装 MySQL

#### 下载MySQL

到 [MySQL官网](https://dev.mysql.com/downloads/mysql/)下载对应操作系统32/64位的.zip压缩包到`\Java\MyMSQL`

> .msi格式的安装包直接点击安装即可，不用再配置配置文件

#### 解压

```
解压到目标安装目录（比如统一安装到同一文件夹下`\Java`）
```

#### 设置环境变量

```
在->系统变量->`PATH` ***追加***
```

```
;安装目录\Java\mysql-5.7.21-winx64\bin;

比如：
;D:\Develop\Java\mysql-5.7.21-winx64\bin;
```

#### 配置文件

在安装目录`\Java\mysql-5.7.21-winx64`新建`my.ini`配置文件如下：

```
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8

[mysqld]
# 设置3306端口
port = 3306
# 设置mysql的安装目录，比如：
basedir=D:\Develop\Java\mysql-5.7.21-winx64
# 设置mysql数据库的数据的存放目录 \data，比如：
datadir=D:\Develop\Java\mysql-5.7.21-winx64\data

# 允许最大连接数
max_connections=20
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
```

#### 以管理员身份运行cmd

```
- 进入`C:\Windows\System32`
- 右键单击cmd.exe
- 选择“以管理员身份运行”
```

> 如果不用管理员身份运行，将会因为权限不够而出现错误：
> `Install/Remove of the Service Denied!`

#### `cd`到安装目录`\bin`下]

```
d:
cd 安装目录\Java\mysql-5.7.21-winx64\bin

比如：
d:
cd D:\Develop\Java\mysql-5.7.21-winx64\bin
```

#### 安装

```
mysqld -install
```

显示`Service successfully installed.`即为安装成功

#### 启动MySQL

```
net start mysql
```

#### 登陆

```
mysql -u root -p
Enter password:
```

> 注意密码为空（直接回车）

#### 新建测试数据库

```
mysql>CREATE DATABASE CampusDate;   //创建一个数据库
mysql>use CampusDate;  //指定test为当前要操作的数据库
mysql>CREATE TABLE user (UseID VARCHAR(20),PassWord VARCHAR(20));   //创建一个表user，设置两个字段。
mysql>INSERT INTO user VALUES('daixiaoke','shishazi'); //插入一条数据到表中
```

### [用 Navicat 导出导入 MySQL](https://blog.csdn.net/davidchengx/article/details/75912013)

---

## 新建 Spring Boot 项目

### 安装 [STS 插件 ](https://spring.io/tools/sts)搭建开发环境

> 要在 eclipse 使用 spring boot 创建项目，==必须== 先安装 Spring Tool Suite (STS) for Eclipse，耗时较长。

1. 在线or离线安装
2. 重启

### [用 Eclipse 创建 Spring Boot 工程并运行](http://rensanning.iteye.com/blog/2355933)

> 必选：Web
>
> 可选：JPA、mysql、dev、thymeleaf、devtools （热部署）

![SpringBoot工程从创建到执行](src/main/resources/static/img/b33a58aa-08a0-3503-9d1d-af6c34189917.jpg)

### 热部署

勾选依赖或添加依赖后，配置 pom.xml 文件。另外可结合 Liveroad 插件，使修改静态文件时浏览器自动刷新。

```
## 热部署
# 此目录下修改文件自动重启
spring.devtools.restart.additional-paths=./src/main/java
#spring.devtools.restart.additional-paths=./src/main/java, ./src/main/resources/templates
# 此目录下修改文件不自动重启
spring.devtools.restart.exclude=static/**,public/**
# 类文件修改后不会自动重启
# spring.thymeleaf.cache=false
```

### 端口占用错误

> The Tomcat connector configured to listen on port 8080 failed to start. The port may already be in use or the connector may be misconfigured.

> 解决：在控制台 stop project

---

## ~~方法一：用 JDBC 连接 MySQL~~

> 前提：已经安装 MySQL 和新建测试数据库

### 下载 mysql-connector 并解压

[mysql-connector-java-5.1.6.zip](https://dev.mysql.com/downloads/connector/j)

### 添加扩展 jar 文件到项目库

1. 在项目上右键->`Build Path`->`Configure Build Path`->`Libraries`->`Add External JARS`
2. 选择`mysql-connector-java`安装目录，并选中`mysql-connector-java-5.1.46-bin.jar`

### 编写java代码来测试连接数据库

- 在`Java Resources`->`src`下新建类`test\SQLTest.Java`,填入如下代码，并修改数据库名、端口、用户名、密码、表名
```
package test;

import java.sql.*;
public class SQLTest {
  public static void main(String args[]) {
    try {
      Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
      //Class.forName("org.gjt.mm.mysql.Driver");
     System.out.println("Success loading Mysql Driver!");
    }
    catch (Exception e) {
      System.out.print("Error loading Mysql Driver!");
      e.printStackTrace();
    }
    try {
      Connection connect = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/CampusDate","root","");
           //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

      System.out.println("Success connect Mysql server!");
      Statement stmt = connect.createStatement();
      ResultSet rs = stmt.executeQuery("select * from User");
                                                              //user 为你表的名称
while (rs.next()) {
        System.out.println(rs.getString("UserID"));
      }
    }
    catch (Exception e) {
      System.out.print("get data error!");
      e.printStackTrace();
    }
  }
}
```
- 编译并执行`SQLTest.Java`
  方法一：在`SQLTest.Java`上右键`Run As`->`Java Application`
  方法二：。。。

---
## 方法二：用 Spring-Data-JPA 操作 MySQL

[Jpa、ORM、JDBC、Hibernate 的关系](https://blog.csdn.net/u010837612/article/details/47610823)

> JPA（Java Persistence API）Java持久化API，是 Java 持久化的标准规范，Hibernate是持久化规范的技术实现，而 Spring Data JPA 是在 Hibernate 基础上封装的一款~~框架~~接口。

### 配置 pom.xml

> 添加 Spring Data JPA 和 MySQL Connector jar 包依赖

```
	<!-- Spring Data JPA -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- MySQL -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
    </dependency>
```

### 设置配置文件

> 在 application.properties 中配置数据源，连接池

```
## 数据库连接
# useUnicode=true&characterEncoding=UTF-8 表示使用Unicode字符集，指定字符从数据库取出后和存入前的编码、解码格式
# useSSL=false 表示在高版本禁用SSL
# autoReconnect 和 failOverReadOnly 用来配置数据源连接池？控制重连特性
# 其中，autoReconnect=true 表示当数据库连接异常中断时自动重连
# failOverReadOnly=false 表示自动重连成功后，连接不设置为只读
spring.datasource.url=jdbc:mysql://localhost:3306/campusdate?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

## 自动建表（实体类维护数据表）方式
# create 表示每次加载  hibernate 时根据实体类重新生成新表
# create-drop 表示每次加载  hibernate 时根据实体类生成新表，当 sessionFactory 关闭时自动删除表
# update 表示第一次加载 hibernate 时根据实体类会自动建表；以后加载 hibernate 时，表结构随实体类自动更新
# validate 表示每次加载  hibernate 时只验证实体类和数据表是否一致
# none 表示啥都不做
spring.jpa.properties.hibernate.hbm2ddl.auto=update
# hibernate在操作的时候在控制台打印真实的sql语句，方便调试
spring.jpa.show-sql=true
# 表示格式化输出的json字符串（方言？）
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```

### [方法一：用 JPA Tools 根据数据表自动创建实体](https://blog.csdn.net/EightSwords/article/details/79022305)

因数据库表中一字段用的保留字`group`，用spring-boot-jpa新增记录时一直失败，报错InvalidDataAccessResourceUsageException。
[InvalidDataAccessResourceUsageException：mysql保留字引发的血案](https://www.shuzhiduo.com/A/QW5Y4ebNJm/)
[Mysql中的关键字和保留字](https://blog.csdn.net/hfut_wowo/article/details/81011952)

### 方法二：创建实体类自动新建数据表

> ==为了解决<bean>标签下<property>标签过多的问题，在实体类中使用 @Autowired  注解实现自动装配注入。== 

### 注解

[Spring Data JPA 常用注解 @Query、@NamedQuery](https://blog.csdn.net/offbye/article/details/47978369)

---

## ~~方法三：用 jdbcTemplate 操作 MySQL~~

[JPA， hibernate， jdbcTemplate（建议使用）区别](https://blog.csdn.net/abcd1101/article/details/64133126)

---

## XxxRepository 接口

> 继承 JpaRepository 接口，从而可用已封装好的方法操作实体类。
>
> 可自定义查找方法，如`findBy+属性名`可实现SQL查询。

---

## XxxService

## XxxController

## 注解和自动装配

[Spring Boot 注解的意义以及作用](https://blog.csdn.net/m0_37995707/article/details/77447764)

[JPA 注解（一） id table entity ](http://conkeyn.iteye.com/blog/602463)

[Spring@Autowired注解与自动装配](https://blog.csdn.net/heyutao007/article/details/5981555)

[SpringBoot中常用注解@Controller/@RestController/@RequestMapping](https://blog.csdn.net/u010412719/article/details/69710480)

> 错误：
> ```
> Error starting ApplicationContext. To display the auto-configuration report re-run your application with 'debug' enabled.
> Description:Field demoService in com.spring.web.DemoApplication required a bean of type that could not be found.
> 
> Consider defining a bean of type in your configuration
> 
> Show required a bean of type that could not be found.
> ```
>
> 分析：`Spring Boot`项目的Bean装配默认规则是根据`Application`类所在的包位置从上往下扫描，只会扫描根目录及其所有子目录。
>
> 解决一：将接口与对应的实现类放在与application启动类的同一个目录或者其子目录下。[spring boot 无法自动注入bean问题解决方案](https://blog.csdn.net/ztx114/article/details/77934454)
>
> 解决二：使用注解`@ComponentScan(value="com.campus")`

---
## AJAX 和 Spring Boot 通信

### bootstraps 教程

[Bootstraps 快速入门](https://bootstrap.ninghao.net/getting-started.html)

[Bootstrap 教程1](http://www.shouce.ren/api/view/a/779)

[Bootstrap 教程2](http://www.ibootstrap.cn/)

[Bootstrap 教程3](http://www.runoob.com/bootstrap/bootstrap-forms.html)

2. [jQuery AJAX中\$.get、\$.post、\$.getJSON、$.ajax 方法详解](https://blog.csdn.net/huileiforever/article/details/12163385)

3. [HTML中id、name、class区别](https://blog.csdn.net/ithomer/article/details/8080912)

4. [FormData Ajax表单提交](https://blog.csdn.net/csdn2193714269/article/details/76269656)

[Spring Boot干货系列：（四）开发Web应用之用Thymeleaf将userlist加载到表格](http://tengj.top/2017/03/13/springboot4/)

[spring boot+前端ajax请求通讯](https://blog.csdn.net/yiwait/article/details/55288814)

 [HTML5日期和时间选择输入](https://www.imooc.com/article/11915)

## ModelMap

[SpringMVC Model、ModelMap 和ModelAndView 的区别和用法](https://blog.csdn.net/qq_20282263/article/details/52831398)

[ModelAndView 构造函数](https://www.cnblogs.com/xuange306/p/6627388.html)

---
## 其它

~~[使用 Timestamp向MySQL插入当前格式化时间日期](http://hovertree.com/h/bjaf/p36d25hy.htm)~~

[SpringBoot中发送QQ邮件](https://blog.csdn.net/u012702547/article/details/79494474)

---

## 错误解决

[Maven 项目 jar 包出现: invalid LOC header (bad signature)](https://blog.csdn.net/m0_37681914/article/details/76064054)

[解决Perhaps you are running on a JRE rather than a JDK?问题](https://blog.csdn.net/hjwang1/article/details/50085839)

[搭建spring-boot项目报错Error parsing lifecycle processing instructions](https://blog.csdn.net/u012810317/article/details/53302592)

~~[spring-boot 热部署 devtools 更新时自动重启](https://www.jianshu.com/p/03a094641bc4)~~

[Could not create connection to database server. Attempted reconnect 3 times. Giving up.](https://blog.csdn.net/u012228009/article/details/54095421)

[解决方案：java.lang.ClassNotFoundException: javax.xml.bind.JAXBException](https://blog.csdn.net/hadues/article/details/79188793)





## 错误

### [找不到或无法加载主类](https://my.oschina.net/u/3771868/blog/2088393)

解决：新建项目时 spring boot 版本改为 1.5.19

## 日志

[用 JPA Tools 自动生成实体](<https://blog.csdn.net/BestandW1shEs_lsy/article/details/77893685>) <https://blog.csdn.net/EightSwords/article/details/79022305>

[JPA 2.1 requires Java 1.5 or newer](<https://www.jianshu.com/p/92386734775c>)

<https://blog.csdn.net/weixin_39220472/article/details/81429241>

<https://www.cnblogs.com/panie2015/p/8330900.html>

<https://segmentfault.com/a/1190000002797195>

自动注入 A component required a bean of typethat could not be found. 解决

<https://segmentfault.com/q/1010000014888748>

<https://cloud.tencent.com/developer/article/1099877>

<https://blog.csdn.net/Julycaka/article/details/80622754>

端口占用

<https://blog.csdn.net/LQM1991/article/details/77446777>

杀掉该进程  taskkill /f /t /im javaw.exe 再次启动就OK

Parameter value [10001] did not match expected type [java.lang.Integer (n/a)]

mysql 改为 bigint

ResponseEntity 用法 SpringBoot进阶之统一接口返回信息格式 <https://blog.csdn.net/tiegenZ/article/details/78231993>

静态方法 <https://blog.csdn.net/wang1051477875/article/details/42422879>

postman

<https://segmentfault.com/q/1010000008308601>

<https://www.520mwx.com/view/10842>

<https://www.jianshu.com/p/5422811407e3>