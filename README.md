# blog

#### 不定期更新的update一览  

>8/03

+统一设置serialVersionUID

>8/02

+添加redis缓存，来自阿里云服务器

>7/28

+修改config文件夹结构，优化注册体验

>7/27

+更新数据库结构，优化首页数据显示

>7/23

+添加验证码验证模块

>7/22

+登录注册采用`ajax`提交而不是`form`  
+数据库格式id采用`Snowflake`算法生成  
+使用`Quill`作为富文本编辑器    

>7/17  

+注册功能与相应前端页面  

>7/16  

+可以通过数据库user表登录

---
### **tips**  
+ [项目中mybatis的用法](http://mybatis.org/generator/generatedobjects/dynamicSqlV2.html)
+ [Spring Security教程（一）](https://juejin.im/post/5cb33ec05188251ad1351af5) 
+ [课程源码之 Spring Security](https://github.com/imooc-java/security)
_________________________  

#### ***Bug记录***

1. 编译时报错找不到符号  
  在IDEA Plugin界面搜索安装 `Lombok` 插件  
  点击 `Setting` -> `Build,Execution,Deployment` -> `Compiler` -> `Annotation Processors` 
  选择项目  
  勾选 `Enable annotation processing`、`Obtain processors from project classpath`  
    
    
@7/16/2020
