package com.dql.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpClientLogin {
    public static void main(String[] args) {

        HttpClientLogin httpClientLogin = new HttpClientLogin();
        CloseableHttpClient httpClient = HttpClients.createDefault();

//        BasicCookieStore cookieStore = new BasicCookieStore();
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

        String xsrfValue = httpClientLogin.getXsrf(httpClient);
        httpClientLogin.getCaptcha(httpClient);
        httpClientLogin.login(httpClient,xsrfValue);



//        String urlStr = "https://www.zhihu.com";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(urlStr);
//        try {
//            CloseableHttpResponse response = httpClient.execute(httpGet);
//            System.out.println(response.getStatusLine());
//            String responseHtml = EntityUtils.toString(response.getEntity());
//            String xsrfValue = responseHtml.split("<input type=\"hidden\" name=\"_xsrf\" value=\"")[1].split("\"/>")[0];
//            System.out.println(xsrfValue);
//            response.close();
//            HttpGet httpGet1 = new HttpGet("https://www.zhihu.com/captcha.gif?type=login");
//            CloseableHttpResponse response1 = httpClient.execute(httpGet1);
//            HttpEntity httpEntity = response1.getEntity();
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpEntity.getContent());
//            File file = new File("test.gif");
//            OutputStream outputStream = new FileOutputStream(file);
//            if (file.exists()) {
//                file.delete();
//            }
//            byte[] bytes = new byte[1024];
//            int len;
//            while ((len = bufferedInputStream.read(bytes)) != -1) {
//                outputStream.write(bytes,0,len);
//            }
//            outputStream.close();




//            List<NameValuePair> formParams = new ArrayList<>();
//            formParams.add(new BasicNameValuePair("_xsrf", xsrfValue));
//            formParams.add(new BasicNameValuePair("phone_num", "15657881323"));
//            formParams.add(new BasicNameValuePair("password", "aobama79"));
//            formParams.add(new BasicNameValuePair("captcha_type", "cn"));
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "utf-8");
//            HttpPost request = new HttpPost("https://www.zhihu.com/login/phone_num");
//            request.setHeader("Connection","keep-alive");
//            request.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
//            request.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//            request.setHeader("Host", "www.zhihu.com");
//            request.setHeader("X-Requested-With","XMLHttpRequest");
//            request.setHeader("Referer","https://www.zhihu.com/");
//            request.setHeader("Cookie","aliyungf_tc=AQAAAL/LD3oF2QIATxZyfWbzBf/I01Qn; l_n_c=1; q_c1=06ac76cde7664f2da015c9da24d356b8|1506575951000|1506575951000; _xsrf=4720e6c07f5911a30ab28429267f7fed; d_c0=\"ACDCX9XOcQyPTqmXq-O06xGXXFzQZDWFfVs=|1506575951\"; _zap=2955e9a3-429c-4b56-9724-ca247f957010; l_cap_id=\"Zjc3MzVlZTQxNDg4NDg5ZDk4MGI1YmJlYmE5MmMzMTU=|1506577968|aedfc52ed1195d111f675d096d1d913cc4db180a\"; r_cap_id=\"ODA1NDU4YzA3ZTViNGJhZWE1ZmJkYzY5MjU4YmU2ODM=|1506577968|d90a79acdd5f23a3df9c51b908563ee44a9ba53d\"; cap_id=\"YzU4MzVmYzY4Y2MxNGFhY2E0OTRkNzI0NjE2OTVmMGI=|1506577968|236bfb4412ba645a3f8316ae95725cea5c2b2528\"; __utma=51854390.2027919525.1506575952.1506575952.1506577977.2; __utmb=51854390.0.10.1506577977; __utmc=51854390; __utmz=51854390.1506575952.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=51854390.000--|3=entry_date=20170928=1\n");
//            request.addHeader("_xsrf", xsrfValue);

//            request.setEntity(entity);
//            CloseableHttpResponse responsePost = httpClient.execute(request);
//            System.out.println(responsePost.getStatusLine());
//            String reusltHtml = EntityUtils.toString(responsePost.getEntity());
//            System.out.println(responseHtml);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        HttpPost httpPost = new HttpPost(urlStr);
//        List<NameValuePair> nvps = new ArrayList<>();
//        nvps.add(new BasicNameValuePair(""))


    }

//    public HttpClientLogin() {
//
//    }

    public void login(CloseableHttpClient httpClient,String xsrfValue) {
        try {
            System.out.println("请输入验证码 ： =======");
            Scanner scanner = new Scanner(System.in);
            String captcha = scanner.nextLine();
            HttpPost httpPost = new HttpPost("https://www.zhihu.com/login/phone_num");
            List<NameValuePair> formParams = new ArrayList<>();
            formParams.add(new BasicNameValuePair("_xsrf", xsrfValue));
            formParams.add(new BasicNameValuePair("phone_num", "15256007602"));
            formParams.add(new BasicNameValuePair("password", "aobama79"));
//          formParams.add(new BasicNameValuePair("captcha_type", "cn"));
            formParams.add(new BasicNameValuePair("captcha", captcha));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "utf-8");

            httpPost.setHeader("Connection","keep-alive");
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36");
            httpPost.setHeader("Accept","*/*");
            httpPost.setHeader("Host", "www.zhihu.com");
            httpPost.setHeader("X-Requested-With","XMLHttpRequest");
            httpPost.setHeader("Referer","https://www.zhihu.com/");
//            httpPost.setHeader("Cookie","aliyungf_tc=AQAAAMAjmV8yLAYATxZyfXDBZ8D0AN/x; l_n_c=1; q_c1=255a6c849dfe4cc6870a536ed4e7229b|1506650873000|1506650873000; _xsrf=7b5485ba0d55a8ef08b001846ae7ab1f; d_c0=\"AFCCPKPscgyPTmL4EUYv-oqAKWODF7oqL5U=|1506650873\"; _zap=acd6e9f8-c526-46e1-b559-eb4955bc09e3; _xsrf=7b5485ba0d55a8ef08b001846ae7ab1f; l_cap_id=\"NGYwNWVhNzczYmU1NGQzMDk0NmE0Y2M4NjA4ZTYyMmM=|1506651660|525d720339818db585ea06311e2d6e6132d92850\"; r_cap_id=\"N2RhNGE2NGM2YTBiNDZiMTk2OTE4MzkxNDM0NzM3MjM=|1506651660|1e0a5f1df81a1e75a174ecb52479a61088d45bda\"; cap_id=\"MTVmOWM0MDY0NTJkNDg5Zjk4N2VjOTYzZDEyYThjMjA=|1506651660|bbc69998b16dc20eac507bbae738b2893aeae748\"; __utma=51854390.770651726.1506650874.1506650874.1506650874.1; __utmb=51854390.0.10.1506650874; __utmc=51854390; __utmz=51854390.1506650874.1.1.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/; __utmv=51854390.010--|3=entry_date=20170929=1");
            httpPost.addHeader("_xsrf", xsrfValue);
            httpPost.setHeader("Cookie","");
            httpPost.addHeader("Origin", "https://www.zhihu.com");
            httpPost.addHeader("Referer","https://www.zhihu.com/signin?next=/");
            httpPost.addHeader("X-Xsrftoken","7b5485ba0d55a8ef08b001846ae7ab1f");

            httpPost.setEntity(entity);

            CloseableHttpResponse responsePost = httpClient.execute(httpPost);
            Header[] headers = responsePost.getAllHeaders();
            StringBuilder stringBuilder = new StringBuilder();
            for (Header header : headers) {
                if ("Set-Cookie".equals(header.toString().split(":")[0].toString())) {
                    stringBuilder.append(header.toString().split(":")[1].toString());
                }
                System.out.println(header);
            }
            System.out.println(stringBuilder.toString());
            String CookieStr = stringBuilder.toString();
//            Header[] cookieStr = responsePost.getHeaders("Set-Cookie");
//            for (Header header : cookieStr) {
//                System.out.println(header);
//            }
            Header header = responsePost.getFirstHeader("Location");
//            header.getValue();
//            System.out.println("Location===="+header.getValue());
            System.out.println(responsePost.getStatusLine());
            String reusltHtml = EntityUtils.toString(responsePost.getEntity());
//            String s = unicode2String(reusltHtml);
//            System.out.println(reusltHtml);
            String s = decode2(reusltHtml);
//            String str = URLDecoder.decode(reusltHtml, "utf-8");
            System.out.println(s);
//            JSONObject jsonObject = new JSONObject();
//            JSONObject jsonObject1 = jsonObject.getJSONObject(reusltHtml);
//            System.out.println(jsonObject1.toString());
//            System.out.println(reusltHtml);

            JSONObject jsonObject = new JSONObject(s);
            String jsonResult = jsonObject.get("r").toString();
            if ("0".equals(jsonResult)) {
                HttpGet httpGet = new HttpGet("https://www.zhihu.com/topic");
//                httpGet.setHeader("Cookie", "aliyungf_tc=AQAAALhkiV0IfggATxZyfW6Kp0DGVdQ0; q_c1=53478f6c42904ea793549cf20b373230|1506736548000|1506736548000; _xsrf=041062c7e40d69371de01868e2e7fc1f; d_c0=\"ACBCmHYzdAyPTsPbRngZ0F6Ais1QdpKE4EM=|1506736549\"; _zap=5ef47704-147f-4362-89dc-adcbdb94fcf2; r_cap_id=\"YmI2NGU0Mjg0NThkNDcwNGE5ZWVjYWJmNTZjMTMwNDQ=|1506737193|00b111c729aadbf09c69c0aee3a1bc3c01e5afab\"; cap_id=\"MzA5M2NmNjM4YmMyNGMxNmEwMzU5ZWIyODJmZTRiMDY=|1506737193|74dc2371cd66de1f58ddcfefd576d378adfb5f57\"; _xsrf=041062c7e40d69371de01868e2e7fc1f; __utma=51854390.725598517.1506736550.1506736550.1506736550.1; __utmb=51854390.0.10.1506736550; __utmc=51854390; __utmz=51854390.1506736550.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmv=51854390.010--|2=registration_date=20141007=1^3=entry_date=20170930=1; z_c0=Mi4xWjZYbEJBQUFBQUFBSUVLWWRqTjBEQmNBQUFCaEFsVk5Pb3IyV1FEMENOMklQbjZMRFZ2MGRVUFl4blloa09IakRR|1506737466|fb25798d77dc5043104c817456b07be11ff86da7");
                httpGet.setHeader("Referer", "https://www.zhihu.com/signin?next=/");
                httpGet.setHeader("Host", "www.zhihu.com");
                httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36\n");

                CloseableHttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity);
                System.out.println(html);
//                InputStream inputStream = httpEntity.getContent();
//                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//                int len;
//                Byte[] bytes = new Byte[1024];
//                while ((len = bufferedInputStream.read(bytes)) != -1) {
//
//                }

            }
            System.out.println(jsonObject.get("r").toString());




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getCaptcha(CloseableHttpClient httpClient) {

        HttpGet httpGet = new HttpGet("https://www.zhihu.com/captcha.gif?r="+System.currentTimeMillis()+"type=login");
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
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
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getXsrf(CloseableHttpClient httpClient) {
        HttpGet httpGet = new HttpGet("https://www.zhihu.com");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String html = EntityUtils.toString(response.getEntity());
            String xsrf = html.split("<input type=\"hidden\" name=\"_xsrf\" value=\"")[1].split("\"/>")[0].toString();
            System.out.println("xsrf =========" +xsrf);
            response.close();
            return xsrf;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
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
