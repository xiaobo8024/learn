package com.txb.app.utils;

import com.txb.app.scheduler.quartz.core.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpUtils {
    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 30000;

    // 读取超时时间
    private static final int READ_TIMEOUT = 50000;

    // 参数编码
    private static final String ENCODE_CHARSET = "utf-8";

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url)
    {
        return sendGet(url, StringUtils.EMPTY);
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param)
    {
        return sendGet(url, param, Constants.UTF8);
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param contentType 编码类型
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String contentType)
    {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try
        {
            String urlNameString = StringUtils.isNotBlank(param) ? url + "?" + param : url;
            log.info("sendGet - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            log.info("recv - {}", result);
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception ex)
            {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try
        {
            log.info("sendPost - {}", url);
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            log.info("recv - {}", result);
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    public static String sendSSLPost(String url, String param)
    {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?" + param;
        try
        {
            log.info("sendSSLPost - {}", urlNameString);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while ((ret = br.readLine()) != null)
            {
                if (ret != null && !"".equals(ret.trim()))
                {
                    result.append(new String(ret.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                }
            }
            log.info("recv - {}", result);
            conn.disconnect();
            br.close();
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, e);
        }
        return result.toString();
    }

    private static class TrustAnyTrustManager implements X509TrustManager
    {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
        {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
        {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier
    {
        @Override
        public boolean verify(String hostname, SSLSession session)
        {
            return true;
        }
    }

    /**
     * 发送HTTP_POST请求
     *
     * @param reqURL
     *            请求地址
     *            发送到远程主机的正文数据,格式JSON
     * @return String
     */
    public static String postJson(String reqURL, String json) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;
        try {

            url = new URL(reqURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        DataOutputStream out = null;
        if (url != null) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);// 设置输入流采用字节流
                urlConn.setDoOutput(true);// 设置输出流采用字节流
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false); // POST请求不能使用缓存
                urlConn.setInstanceFollowRedirects(true);
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);// 设置连接超时
                urlConn.setReadTimeout(READ_TIMEOUT); // 设置读取超时
                // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                urlConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                urlConn.setRequestProperty("Charset", ENCODE_CHARSET);//
                urlConn.setRequestProperty("Content-Length", json.getBytes().length + "");//
                // urlConn.setRequestProperty("Connection", "Keep-Alive");
                // //设置长连接
                urlConn.connect();// 连接服务器发送消息
                if (!org.apache.commons.lang3.StringUtils.isEmpty(json)) {
                    out = new DataOutputStream(urlConn.getOutputStream());
                    // 将要上传的内容写入流中
                    out.write(json.getBytes(StandardCharsets.UTF_8));
                    // 刷新、关闭
                    out.flush();
                    out.close();

                }
                in = new InputStreamReader(urlConn.getInputStream(), ENCODE_CHARSET);
                buffer = new BufferedReader(in);
                if (urlConn.getResponseCode() == 200) {
                    while ((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != out) {
                        out.close();
                    }
                    if (buffer != null) {
                        buffer.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultData.toString();
    }


    /**
     * 发送HTTP_POST请求
     *
     * @param reqURL
     *            请求地址
     *            发送到远程主机的正文数据,格式JSON
     * @return String
     */
    public static String postJson(String reqURL, String json, Map<String,String> headParam) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;
        try {

            url = new URL(reqURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        DataOutputStream out = null;
        if (url != null) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);// 设置输入流采用字节流
                urlConn.setDoOutput(true);// 设置输出流采用字节流
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false); // POST请求不能使用缓存
                urlConn.setInstanceFollowRedirects(true);
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);// 设置连接超时
                urlConn.setReadTimeout(READ_TIMEOUT); // 设置读取超时
                // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                urlConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                urlConn.setRequestProperty("Charset", ENCODE_CHARSET);//
                urlConn.setRequestProperty("Content-Length", json.getBytes().length + "");//
                for (String key : headParam.keySet()) {
                    String value = headParam.get(key);
                    urlConn.setRequestProperty(key,value);
                }
                // urlConn.setRequestProperty("Connection", "Keep-Alive");
                // //设置长连接
                urlConn.connect();// 连接服务器发送消息
                if (!org.apache.commons.lang3.StringUtils.isEmpty(json)) {
                    out = new DataOutputStream(urlConn.getOutputStream());
                    // 将要上传的内容写入流中
                    out.write(json.getBytes(StandardCharsets.UTF_8));
                    // 刷新、关闭
                    out.flush();
                    out.close();

                }
                in = new InputStreamReader(urlConn.getInputStream(), ENCODE_CHARSET);
                buffer = new BufferedReader(in);
                if (urlConn.getResponseCode() == 200) {
                    while ((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != out) {
                        out.close();
                    }
                    if (buffer != null) {
                        buffer.close();
                    }

                    if (in != null) {
                        in.close();
                    }

                    if (urlConn != null) {
                        urlConn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultData.toString();
    }
}
