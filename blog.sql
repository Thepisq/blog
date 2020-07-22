# Host: 127.0.0.1  (Version 5.7.25)
# Date: 2020-07-21 14:36:06
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
  `first_push_date` date DEFAULT NULL,
  `last_push_date` date DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "blog"
#

INSERT INTO `blog` VALUES (1,1,'1','1',0,0,0,0,NULL,NULL,NULL),(2,16,'hello my friends!!','<p>hello my friends!!</p>',0,0,0,0,'2020-07-19','2020-07-19',1);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "sys_topic"
#


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

INSERT INTO `user` VALUES (1,'user1','$2a$10$u7bwoOUuIHp4DYlsHWMjjO8bwOy0a.HPg0/DRqOOfUtyyoMSOp3ge',NULL,NULL,NULL,'USER'),(2,'admin1','$2a$10$ou4wOJ.VY5dToAHY0pCzLOmN5iihQ73n2oeKxkJMc0OGFB0jG0Jia',NULL,NULL,NULL,'USER,ADMIN'),(4,'user2','$2a$10$QJMv7.Cu8hl0U5YjmvwfE.mOziZ9ZLHJICpeAy2KYc34PP5AjARsi',NULL,NULL,NULL,'BLOG'),(12,'user1a','$2a$10$AUJdH6pg5Ygm3BVaL8QXPeHMi7r900z57sVmXp0/jTu5WlL1N1r2.',NULL,NULL,NULL,'USER'),(13,'user2a','$2a$10$fmxe.ozANlQ7P63OvE3leu2UBz3LcYgHQ91uBEwX9gl5KscZlQjLK',NULL,NULL,NULL,'USER'),(14,'apple','$2a$10$q8EeZtxezMekv9XM9dncge1qHTApfvGueoVVuwBP0qbCowVcLKxUi',NULL,NULL,NULL,'USER'),(15,'apple1','$2a$10$uxtkfBcvTChCLPuTPapTgeDIn1uHzt82iESPvKceIvJ7Wp4QzREaW',NULL,NULL,NULL,'USER'),(16,'myuser','$2a$10$8jn3r8uVQtFM6AREfBsAverbU6TveP2xYrSRmdFE/d5.GUgLR.C9W',NULL,NULL,NULL,'USER,BLOG');
