package com.cgfy.user.base.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Using IntelliJ IDEA.
 *
 * @author JIANJIAYUE at 2019/1/15 13:37
 */

@Slf4j
@Data
public class MessageBean {
    //手机号
    private String phoneNumbers;
    //短信签名
    private String signName;
    //短信模板ID，发送国际/港澳台消息时，请使用国际/港澳台短信模版
    private String templateCode;
    //短信模板变量替换JSON串,友情提示:如果JSON中需要带换行符,请参照标准的JSON协议。
    private Map<String,String> templateParam;
    //上行短信扩展码,无特殊需要此字段的用户请忽略此字段
    private String smsUpExtendCode;
    //外部流水扩展字段
    private String outId;
    //系统识别 1 oa     2 统计分析
    private String type;

}
