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
