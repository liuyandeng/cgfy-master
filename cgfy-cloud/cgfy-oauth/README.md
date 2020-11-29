# oauth

##介绍
鉴权与授权
请求:
http://localhost:8840/oauth/token?grant_type=password&client_id=WEB&client_secret=37906086F9EB4B58B315D97B65C3DBFC&username=admin&password=123456
API:http://localhost:8842/swagger-ui.html


## [oauth_client_detail表说明](https://www.jianshu.com/p/c1c6c966c3a7)
spring-cloud-starter-oauth2----oauth_client_details

- **1.client_id:**	
主键,必须唯一,不能为空	用于唯一标识每一个客户端(client)；注册时必须填写(也可以服务端自动生成),这个字段是必须的,实际应用也有叫app_key,例如:OaH1heR2E4eGnBr87Br8FHaUFrA2Q0kE8HqZgpdg8Sw

- **2.resource_ids:**
不能为空,用逗号分隔,客户端能访问的资源id集合,注册客户端时,根据实际需要可选择资源id,也可以根据不同的额注册流程,赋予对应的额资源id,例如:order-resource,pay-resource

- **3.client_secret:**
必须填写注册填写或者服务端自动生成,实际应用也有叫app_secret, 必须要有前缀代表加密方式,例如:{bcrypt}10gY/Hauph1tqvVWiH4atxteSH8sRX03IDXRIQi03DVTFGzKfz8ZtGi

- **4.scope:**
不能为空,用逗号分隔,指定client的权限范围,比如读写权限,比如移动端还是web端权限,例如:read,write / web,mobile

- **5.authorized_grant_types:**
不能为空,可选值,授权码模式:authorization_code,密码模式:password,刷新token: refresh_token, 隐式模式: implicit: 客户端模式: client_credentials。支持多个用逗号分隔,例如:password,refresh_token

- **6.web_server_redirect_uri:**
可为空,客户端重定向uri,authorization_code和implicit需要该值进行校验,注册时填写,,例如:httt://baidu.com

- **7.authorities:**
可为空,指定用户的权限范围,如果授权的过程需要用户登陆,该字段不生效,implicit和client_credentials需要,例如:ROLE_ADMIN,ROLE_USER

- **8.access_token_validity:**
可空,设置access_token的有效时间(秒),默认(606012,12小时),例如:3600

- **9.refresh_token_validity:**
可空,设置refresh_token有效期(秒),默认(606024*30, 30填),例如:7200

- **10.additional_information:**
可空,值必须是json格式,例如:{"key", "value"}

- **11.autoapprove:**
false/true/read/write,默认false,适用于authorization_code模式,设置用户是否自动approval操作,设置true跳过用户确认授权操作页面,直接跳到redirect_uri,例如:false
