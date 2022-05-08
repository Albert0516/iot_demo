package Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnect {

    public static String token = "";
    //public static String token = new String("ecd4efb602f040472e0b02505e535cfebcc7");
    public static String appId = new String("9660557114988584965dfd41");
    public static String openId = new String("203405981035967938286530695169");
    public static String accessToken = new String("ef1f3bdd3b3160494a60861723961159");

    public static String sendRequest(String urlParam, String requestType, String requestJsonStr, boolean needToken) {

        HttpURLConnection conn = null;
        OutputStreamWriter bufferOut = null;
        BufferedReader bufferIn = null;
        StringBuffer resultBuffer = null;

        try {
            URL url = new URL(urlParam);
            //得到连接对象
            conn = (HttpURLConnection) url.openConnection();

            //允许写出
            conn.setDoOutput(true);
            //允许读入
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //设置请求类型
            conn.setRequestMethod(requestType);
            //设置请求需要返回的数据类型和字符集类型
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            conn.setRequestProperty("user-agent", "PostmanRuntime/7.29.0");//设置消息头，解决508错误
            if (needToken && !token.isBlank()) {
                //conn.setRequestProperty("Token", "05babaae0516f042b309fd609658cadebeb3");//令牌
                conn.setRequestProperty("Token", token);//令牌
            }

            // 开始连接请求
            conn.connect();
            bufferOut = new OutputStreamWriter(conn.getOutputStream(), "UTF-8"); // utf-8编码

            // 写入请求的字符串
            bufferOut.append(requestJsonStr);
            bufferOut.flush();
            bufferOut.close();
            //得到响应码
            //int responseCode = conn.getResponseCode();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                if(!needToken)
                {
                    String key = null;
                    String cookieVal = "";
                    for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++){
                        if(key.equalsIgnoreCase("set-cookie"))
                        {
                            cookieVal = conn.getHeaderField(i);
                            if(cookieVal.startsWith("Token="))
                            {
                                token = cookieVal.substring(cookieVal.indexOf("=")+1, cookieVal.indexOf(";"));
                                break;
                            }
                        }
                    }
                    System.out.println("token refresh:"+token);
                }
                //得到响应流
                InputStream inputStream = conn.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuffer();
                bufferIn = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                String line;
                while ((line = bufferIn.readLine()) != null) {
                    resultBuffer.append(line);
                }
                return resultBuffer.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
