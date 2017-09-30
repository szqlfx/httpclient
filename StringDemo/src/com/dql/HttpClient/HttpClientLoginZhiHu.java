package com.dql.HttpClient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpClientLoginZhiHu {
    public static void main(String[] args) {

//        BasicCookieStore cookieStore = new BasicCookieStore();
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//
//        HttpGet httpGet = new HttpGet("https://www.zhihu.com");
//        httpGet.setHeader("Connection","keep-alive");
//        httpGet.setHeader("Host","www.zhihu.com");
//        httpGet.setHeader("User-Agent","Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36");
//
//        try {
//            CloseableHttpResponse response = httpClient.execute(httpGet);
//            List<Cookie> cookies = cookieStore.getCookies();
//            if (cookies.isEmpty()) {
//                System.out.println("None");
//            } else {
//                for (int i = 0; i < cookies.size(); i++) {
//                    System.out.println(cookies.get(i).toString());
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        CloseableHttpClient httpClient = HttpClients.createDefault();
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        HttpGet httpGet = new HttpGet("https://www.zhihu.com");
        httpGet.setHeader("Connection","keep-alive");
        httpGet.setHeader("Host","www.zhihu.com");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36");

        StringBuilder stringBuilder = new StringBuilder();
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            Header[] headers = response.getHeaders("Set-Cookie");
            for (Header header : headers) {
                stringBuilder.append(header.toString().split(":")[1].toString());
//                stringBuilder.append(header);
//                System.out.println(header);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stringBuilder.toString());

        HttpGet httpGet2 = new HttpGet("https://www.zhihu.com/captcha.gif?r="+System.currentTimeMillis()+"type=login");

        try {
            CloseableHttpResponse httpResponse2 = httpClient.execute(httpGet2);
            HttpEntity httpEntity = httpResponse2.getEntity();
            InputStream inputStream = httpEntity.getContent();
            File file = new File("captcha.gif");
            if (file.exists()) {
                file.delete();
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[1024];
            int len;
            FileOutputStream outputStreamWriter = new FileOutputStream(file);
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                outputStreamWriter.write(bytes,0,len);
            }
            outputStreamWriter.close();
            inputStream.close();
            httpResponse2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        HttpGet httpGet1 = new HttpGet("https://www.zhihu.com/signin?next=/");
        httpGet1.setHeader("Connection","keep-alive");
        httpGet1.setHeader("Host","www.zhihu.com");
        httpGet1.setHeader("User-Agent","Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36");
        httpGet1.setHeader("Cookie",stringBuilder.toString());
        String xsrfValue = null;
        String cookie = null;
        try {
            CloseableHttpResponse response1 = httpClient.execute(httpGet1);
            String html = EntityUtils.toString(response1.getEntity());
            xsrfValue = html.split("<input type=\"hidden\" name=\"_xsrf\" value=\"")[1].split("\"/>")[0].toString();
            Header[] headers = response1.getHeaders("Set-Cookie");
            StringBuilder stringBuilder1 = new StringBuilder();
            for (Header header : headers) {

                stringBuilder1.append(header.toString().split(":")[1].toString());
//                System.out.println(header);
            }
            cookie = stringBuilder1.toString();
            System.out.println(cookie);
        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println("请输入验证码 ： =======");
        Scanner scanner = new Scanner(System.in);
        String captcha = scanner.nextLine();

        HttpPost httpPost = new HttpPost("https://www.zhihu.com/login/phone_num");
        httpPost.setHeader("Connection","keep-alive");
        httpPost.setHeader("Host","www.zhihu.com");
        httpPost.setHeader("Referer", "https://www.zhihu.com/signin?next=/");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36");
        httpPost.setHeader("Cookie",stringBuilder.toString()+cookie);

        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("_xsrf", xsrfValue));
        formParams.add(new BasicNameValuePair("phone_num", "15256007602"));
        formParams.add(new BasicNameValuePair("password", "aobama79"));
//          formParams.add(new BasicNameValuePair("captcha_type", "cn"));
        formParams.add(new BasicNameValuePair("captcha", captcha));

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "utf-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse responsePost = httpClient.execute(httpPost);
            System.out.println(responsePost.getStatusLine());

            String reusltHtml = EntityUtils.toString(responsePost.getEntity());

            String s = decode2(reusltHtml);
            System.out.println(s);


            Header[] headers = responsePost.getHeaders("Set-Cookie");
            StringBuilder builder = new StringBuilder();
            for (Header header : headers) {
                builder.append(header.toString().split(":")[1].toString());

            }

            String cookieLogin = builder.toString();

            HttpGet httpGet3 = new HttpGet("https://www.zhihu.com/topic");
            httpGet3.setHeader("Cookie",cookieLogin);
            httpGet3.setHeader("Referer", "https://www.zhihu.com/signin?next=/");
            httpGet3.setHeader("Host", "www.zhihu.com");
            httpGet3.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36\n");
            CloseableHttpResponse response = httpClient.execute(httpGet3);
            System.out.println(EntityUtils.toString(response.getEntity()));


        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    public static String decode2(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '\\' && chars[i + 1] == 'u') {
                char cc = 0;
                for (int j = 0; j < 4; j++) {
                    char ch = Character.toLowerCase(chars[i + 2 + j]);
                    if ('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'f') {
                        cc |= (Character.digit(ch, 16) << (3 - j) * 4);
                    } else {
                        cc = 0;
                        break;
                    }
                }
                if (cc > 0) {
                    i += 5;
                    sb.append(cc);
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }



}
