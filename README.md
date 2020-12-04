
## demo-base


## 自定义通用操作
该工程可以作为一个基础工程。

由SpringBoot + Mybatis + TK.Mybatis 组成主体结构，方便自动生成代码。

同时，包中包含一条龙式的通用操作示例

- 通用TK的Mapper

- 通用支持CURD的Service

- 通用支持CURD的Controller

如果需要自定义，在自己对应的 XxxMapper/XxxService/XxxController 里直接写自定义的方法即可。

## TK.Mybatis 代码生成

参考 `generator/generatorConfig.xml`

## 线程绑定/线程隔离学习示例

参考：ThreadLocalCache

## 自定义缓存管理器，带过期时间

参考：MyCacheManager

## 增加自定义注解的2种使用方式

1、拦截器方式

- Interceptor 拦截 完成自定义注解限制接口在时间窗口内的访问次数

2、AOP方式

- AOP 拦截 完成自定义注解限制接口在时间窗口内的访问次数

- AOP 切面 完成自定义注解日志切面

3、以独立的starter方式自定义注解与使用。
- 参考对应文件：[用starter封装自定义注解]()
- 参考对应工程：[limit-checked-starter]()

