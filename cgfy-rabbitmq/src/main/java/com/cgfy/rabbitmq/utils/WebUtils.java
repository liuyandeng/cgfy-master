package com.cgfy.rabbitmq.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


@Component
public class WebUtils {
    private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);
    @Value("${spring.rabbitmq.host}")
    private  String host;
    @Value("${spring.rabbitmq.apiport}")
    private  String port;
    @Value("${spring.rabbitmq.username}")
    private  String username;
    @Value("${spring.rabbitmq.password}")
    private  String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private  String virtualHost;
    @Resource
    private  CloseableHttpClient httpClient;
    @Resource
    private  RequestConfig config;

    public  String getApiMessage(String mqUrl) throws IOException {
        //发送一个GET请求
        HttpURLConnection httpConn = null;
        BufferedReader in = null;

       // String urlString = "http://" + host + ":" + port + "/api/queues/" + virtualHost + "/" + queueName;
        String urlString = "http://" + host + ":" + port +mqUrl;
        URL url = new URL(urlString);
        httpConn = (HttpURLConnection) url.openConnection();
        //设置用户名密码
        String auth = username + ":" + password;
        BASE64Encoder enc = new BASE64Encoder();
        String encoding = enc.encode(auth.getBytes());
        httpConn.setDoOutput(true);
        httpConn.setRequestProperty("Authorization", "Basic " + encoding);
        // 建立实际的连接
        httpConn.connect();
        //读取响应
        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            StringBuilder content = new StringBuilder();
            String tempStr = "";
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            while ((tempStr = in.readLine()) != null) {
                content.append(tempStr);
            }
            in.close();
            httpConn.disconnect();
            return content.toString();
        } else {
            httpConn.disconnect();
            return "";
        }
    }
    /////////////////////////////////////////////////////////httpclient///////////////////////////////////////////////////////////////////////


    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回错误信息
     *
     * @param url
     * @return
     * @throws Exception
     */

    public  String doGet(String url) throws Exception {
        url = "http://" + host + ":" + port +url;
        logger.info("请求的URL:"+url);
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        // 装载配置信息
        httpGet.setConfig(config);
        // 发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return "";
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public  String doGet(String url, Map<String, Object> map) throws Exception {
        url = "http://" + host + ":" + port +url;
        URIBuilder uriBuilder = new URIBuilder(url);
        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        // 调用不带参数的get请求
        return doGet(uriBuilder.build().toString());
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */

    public  AjaxResponse doPost(String url, Map<String, Object> map) throws Exception {
        url = "http://" + host + ":" + port +url;
        logger.info("请求的URL:"+url);
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }
        // 发起请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return new AjaxResponse(true,response.getStatusLine().getStatusCode(), "",EntityUtils.toString(response.getEntity(), "UTF-8"));
    }
    /**
     * 不带参数post请求
     * @param url
     * @return
     * @throws Exception
     */
    public  AjaxResponse doPost(String url) throws Exception {
        url = "http://" + host + ":" + port +url;
        return doPost(url, null);
    }



}



