# video
springcloud在线视频

### 11/08
初始化项目结构，创建eureka,system,gateway模块

### 11/09
创建server模块

### 11/11
1.集成mybatis-generator插件  
步骤1:添加idea插件  
步骤2:添加server\src\main\resources\generator\generatorConfig.xml  
步骤3:创建maven启动命令mybaits-generator:generate -e  

### 11/18
使用ace模板创建了一个后台登录页面

### 11/19
1.使用npm install --save vue-router下载插件  
2.把app里的登录部分的代码迁移到views下面的login.vue,注意template下只能有一个div标签！template下面不允许出现script标签  
3.使用@click在按钮上绑定登录方法，使用this.$router().push()实现跳转  

### 11/23
1.使用mouted方法修改样式问题
2.完成admin.vue基本模板的修改
3.创建business模块

### 11/25
1.创建了chapter.vue页面，对应大章列表，使用假数据填充列表
2.使用watch监听路由变化情况，并相应的修改激活样式

### 11/26
前后端交互
1.安装插件:npm install axios --save

2.以vue的内置属性使用axios
```
import axios from 'axios'

Vue.prototype.$ajax = axios
```

3.在vue中使用axios发起请求
```
list() {
                let _this = this;
                _this.$ajax.get('http://127.0.0.1:9002/business/chapter/list').then((response)=>{
                    console.log("查询大章列表结果:", response);
                })
            }
```
4.解决跨域问题

### 11/27
1.使用data定义组件内的变量，可用于做双向数据绑定，双向数据绑定是vue的核心功能之一。

### 11/28
1.修改跨域配置，在gateway使用CorsWebFilter解决跨域问题，如果是单个springboot应用可以使用CorsConfig解决跨域问题

### 04/24
1.实现删除功能  
2.使用sweetalert2封装消息提示框，在删除，新增，编辑时进行提示  
3.Chapter实体类新增gmt_created,gmt_modified字段，查询按照创建时间倒序  

### 04/25
1.新增ValidateUtil（后端校验参数格式）,ValidateException和全局异常处理类ControllerExceptionHandler,对异常进行统一处理  
2.前段校验参数格式  
3.新增section表和对应的类  
4.代码生成器实现，用于生成service,controller的代码  
5.通过代码生成器生成Section的service和controller  

### 04/26
1.新增DTO代码生成器的工具类

### 05/06  
1.删除chapter.vue中多余的注释
2.前端vue生成器开发，生成section对应的vue.js代码  
3.前端参数传入时丢失title，修改SectionDto让参数能正常序列化  

### 05/09  
1.枚举字段，表格显示中文，表单显示下拉框  
2.增加枚举常量  
3.增加收费枚举类型  
4.修改和html关键字冲突的部分  
5.生成course相关的代码，新增对应的枚举并且新增vue中枚举对应的下拉框  

### 05/10
1.优化课程管理界面  
2.课程管理和大章管理相互跳转 
3.大章管理保存和查询功能关联courseId   
4.大章管理和小节管理，关联关系和互相跳转  
5.修改或新增小节，更新课程总时间    
6.增加事务控制  

### 05/11
1.分类表设计和基本代码生成  

### 05/15
1.增加一个测试页面  
2.引入element ui做树形菜单  
3.课程管理新增分类关联  

### 05/16
1.课程内容表和基本代码生成  
2.引入wangeditor富文本编辑器，增加课程内容管理  