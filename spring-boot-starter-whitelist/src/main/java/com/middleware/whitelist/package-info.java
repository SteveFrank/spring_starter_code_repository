/**
 * 白名单模块
 *
 * DoWhiteList 自定义注解，作用就是在需要使用到白名单的接口上进行注解，接口入参提取字段的属性名称、拦截后返回的信息
 * WhiteListAutoConfigure 配置下是对 SpringBoot yml 文件的使用，这样就可以把配置到yml文件中的白名单提取到中间件中（文件读取亦可）
 * DoJoinPoint 负责了对所有添加自定义注解的方法进行拦截和逻辑的处理
 *
 * @author yangqian
 * @date 2021/4/6
 */
package com.middleware.whitelist;