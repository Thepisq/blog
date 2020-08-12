# Host: 127.0.0.1  (Version 5.7.25)
# Date: 2020-08-11 17:48:37
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "blog"
#

DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `author_id` bigint(20) NOT NULL DEFAULT '0',
  `title` varchar(30) NOT NULL,
  `content` mediumtext NOT NULL,
  `likes` int(11) NOT NULL DEFAULT '0',
  `clicks` int(11) NOT NULL DEFAULT '0',
  `collects` int(11) NOT NULL DEFAULT '0',
  `comments` int(11) NOT NULL DEFAULT '0',
  `first_push_date` datetime DEFAULT NULL,
  `last_push_date` datetime DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "blog"
#

INSERT INTO `blog` VALUES (1291375928611672064,1291372645423685632,'春季AMQP','<h2><strong>3.简介</strong></h2><p>参考文档的第一部分是Spring AMQP及其基本概念的高级概述。它包含一些代码片段，以帮助您尽快启动并运行。</p><p><br></p><h3><strong>3.1。不耐烦的快速浏览</strong></h3><h4><strong>3.1.1。介绍</strong></h4><p>这是从Spring AMQP开始的五分钟导览。</p><p><br></p><p>先决条件：安装并运行RabbitMQ代理（<a href=\"https://www.rabbitmq.com/download.html\" target=\"_blank\" style=\"color: rgb(92, 181, 248);\">https://www.rabbitmq.com/download.html</a>）。然后获取spring-rabbit JAR及其所有依赖项-最简单的方法是在构建工具中声明一个依赖项。例如，对于Maven，您可以执行类似以下操作：</p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">&lt;dependency&gt;\n  &lt;groupId&gt;org.springframework.amqp&lt;/groupId&gt;\n  &lt;artifactId&gt;spring-rabbit&lt;/artifactId&gt;\n  &lt;version&gt;2.3.0-SNAPSHOT&lt;/version&gt;\n&lt;/dependency&gt;\n</pre><p>对于Gradle，您可以执行以下操作：</p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">compile \'org.springframework.amqp:spring-rabbit:2.3.0-SNAPSHOT\'\n</pre><h5><strong>兼容性</strong></h5><p>Spring Framework的最低版本依赖性为5.2.0。</p><p><br></p><p><code style=\"background-color: rgb(240, 240, 240); color: rgb(112, 164, 255);\">amqp-client</code>Java客户端库的最低版本为5.7.0。</p><h5><strong>非常非常快</strong></h5><p>本节提供最快的介绍。</p><p><br></p><p>首先，添加以下<code style=\"background-color: rgb(240, 240, 240); color: rgb(112, 164, 255);\">import</code>语句以使本​​节后面的示例正常工作：</p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">import org.springframework.amqp.core.AmqpAdmin;\nimport org.springframework.amqp.core.AmqpTemplate;\nimport org.springframework.amqp.core.Queue;\nimport org.springframework.amqp.rabbit.connection.CachingConnectionFactory;\nimport org.springframework.amqp.rabbit.connection.ConnectionFactory;\nimport org.springframework.amqp.rabbit.core.RabbitAdmin;\nimport org.springframework.amqp.rabbit.core.RabbitTemplate;\n</pre><p>以下示例使用简单的命令式Java发送和接收消息：</p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">ConnectionFactory connectionFactory = new CachingConnectionFactory();\nAmqpAdmin admin = new RabbitAdmin(connectionFactory);\nadmin.declareQueue(new Queue(\"myqueue\"));\nAmqpTemplate template = new RabbitTemplate(connectionFactory);\ntemplate.convertAndSend(\"myqueue\", \"foo\");\nString foo = (String) template.receiveAndConvert(\"myqueue\");\n</pre><p>请注意，<code style=\"background-color: rgb(240, 240, 240); color: rgb(112, 164, 255);\">ConnectionFactory</code>本机Java Rabbit客户端中也有一个。我们在前面的代码中使用Spring抽象。它缓存通道（和可选的连接）以供重用。我们依赖于代理中的默认交换（因为在send中未指定），以及所有队列通过其名称到默认交换的默认绑定（因此，我们可以将队列名称用作send中的路由键） 。这些行为在AMQP规范中定义。</p><h5><strong>使用XML配置</strong></h5><p>以下示例与前面的示例相同，但是将资源配置外部化为XML：</p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">ApplicationContext context =\n    new GenericXmlApplicationContext(\"classpath:/rabbit-context.xml\");\nAmqpTemplate template = context.getBean(AmqpTemplate.class);\ntemplate.convertAndSend(\"myqueue\", \"foo\");\nString foo = (String) template.receiveAndConvert(\"myqueue\");\n&lt;beans xmlns=\"http://www.springframework.org/schema/beans\"\n       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n       xmlns:rabbit=\"http://www.springframework.org/schema/rabbit\"\n       xsi:schemaLocation=\"http://www.springframework.org/schema/rabbit\n           https://www.springframework.org/schema/rabbit/spring-rabbit.xsd\n           http://www.springframework.org/schema/beans\n           https://www.springframework.org/schema/beans/spring-beans.xsd\"&gt;\n\n    &lt;rabbit:connection-factory id=\"connectionFactory\"/&gt;\n\n    &lt;rabbit:template id=\"amqpTemplate\" connection-factory=\"connectionFactory\"/&gt;\n\n    &lt;rabbit:admin connection-factory=\"connectionFactory\"/&gt;\n\n    &lt;rabbit:queue name=\"myqueue\"/&gt;\n\n&lt;/beans&gt;\n</pre><p>默认情况下，<code style=\"background-color: rgb(240, 240, 240); color: rgb(112, 164, 255);\">&lt;rabbit:admin/&gt;</code>申报自动查找类型的豆类<code style=\"background-color: rgb(240, 240, 240); color: rgb(112, 164, 255);\">Queue</code>，<code style=\"background-color: rgb(240, 240, 240); color: rgb(112, 164, 255);\">Exchange</code>以及<code style=\"background-color: rgb(240, 240, 240); color: rgb(112, 164, 255);\">Binding</code>和他们宣布到代理代表用户的。结果，您无需在简单的Java驱动程序中显式使用该bean。有很多选项可以配置XML模式中组件的属性。您可以使用XML编辑器的自动完成功能来探索它们并查看其文档。</p><h5><strong>使用Java配置</strong></h5><p>下面的示例重复与前面的示例相同的示例，但是具有Java中定义的外部配置：</p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">ApplicationContext context =\n    new AnnotationConfigApplicationContext(RabbitConfiguration.class);\nAmqpTemplate template = context.getBean(AmqpTemplate.class);\ntemplate.convertAndSend(\"myqueue\", \"foo\");\nString foo = (String) template.receiveAndConvert(\"myqueue\");\n\n........\n\n@Configuration\npublic class RabbitConfiguration {\n\n    @Bean\n    public ConnectionFactory connectionFactory() {\n        return new CachingConnectionFactory(\"localhost\");\n    }\n\n    @Bean\n    public AmqpAdmin amqpAdmin() {\n        return new RabbitAdmin(connectionFactory());\n    }\n\n    @Bean\n    public RabbitTemplate rabbitTemplate() {\n        return new RabbitTemplate(connectionFactory());\n    }\n\n    @Bean\n    public Queue myQueue() {\n       return new Queue(\"myqueue\");\n    }\n}\n</pre><h5><strong>使用Spring Boot自动配置和异步POJO侦听器</strong></h5><p>Spring Boot自动配置基础结构bean，如以下示例所示：</p><p><br></p><pre class=\"ql-syntax\" spellcheck=\"false\">@SpringBootApplication\npublic class Application {\n\n    public static void main(String[] args) {\n        SpringApplication.run(Application.class, args);\n    }\n\n    @Bean\n    public ApplicationRunner runner(AmqpTemplate template) {\n        return args -&gt; template.convertAndSend(\"myqueue\", \"foo\");\n    }\n\n    @Bean\n    public Queue myQueue() {\n        return new Queue(\"myqueue\");\n    }\n\n    @RabbitListener(queues = \"myqueue\")\n    public void listen(String in) {\n        System.out.println(in);\n    }\n\n}\n</pre><p><br></p>',0,0,0,0,'2020-08-06 22:09:49','2020-08-06 22:09:49',4);

#
# Structure for table "follow"
#

DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `following` json DEFAULT NULL,
  `followers` json DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "follow"
#


#
# Structure for table "persistent_logins"
#

DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "persistent_logins"
#


#
# Structure for table "sys_role"
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "sys_role"
#

INSERT INTO `sys_role` VALUES (1,'USER','浏览blog'),(2,'BLOG','写blog'),(3,'ADMIN','修改用户');

#
# Structure for table "sys_topic"
#

DROP TABLE IF EXISTS `sys_topic`;
CREATE TABLE `sys_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branch` varchar(255) NOT NULL,
  `topic_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "sys_topic"
#

INSERT INTO `sys_topic` VALUES (1,'主版','简单交流'),(2,'主版','情感交流'),(3,'主版','技术交流'),(4,'主版','水水水');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1291372645423685632,'password_is_password','$2a$10$q7JBe1celOZjASMfcxyFq.yzv2NbGlvDvHNWPnSYS3CKoa8b3b7Ni',NULL,NULL,NULL,'USER,BLOG');
