# 基于Spring Boot的前后端分离的选课系统

本项目是一个基于Spring Boot的前后端分离项目，使用了Vue.js作为前端框架，实现了一个简单的选课系统

## 前端地址

- [选课系统前端](https://github.com/iajat/selectClass--vue--spring-boot)

## 技术栈

- 后端：Spring Boot、MyBatis、MyBatis Plus、MySQL

## 项目结构

```
├── sscm  # 后端项目
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com.lsw.sscm
│   │   │   │       ├── config  # 配置类
│   │   │   │       ├── controller  # 控制器
│   │   │   │       ├── mapper  # MyBatis Mapper
│   │   │   │       ├── pojo  # 实体类
│   │   │   │       ├── service  # 服务接口
│   │   │   │       ├── interceptor  #拦截器
│   │   │   │       ├── tool  # 工具
│   │   │   │       └── SscmApplication.java  # 启动类
│   │   │   └── resources
│   │   │       ├── application.yml  # 配置文件
│   │   │       ├── mapper  # MyBatis Mapper XML
│   │   │       └── static
```

## 快速开始

### 后端项目

1. 在MySQL中创建一个名为`sscm`的数据库
2. 修改`sscm/src/main/resources/application.yml`中的数据库连接配置
3. 运行`sscm/src/main/java/com.lsw.sscm/SscmApplication.java`启动后端服务器

## API文档

- 启动之后访问：`http://localhost:8080/swagger-ui/`

## 参考资料

- [Spring Boot官方文档](https://spring.io/projects/spring-boot)
- [MyBatis官方文档](https://mybatis.org/mybatis-3/zh/index.html)
- [MyBatis Plus官方文档](https://www.baomidou.com/)
