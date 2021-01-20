package org.bohr.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2020
 * Company:
 *
 * @author Evgeny Bogachev
 */

public class HttpUtils {

    public static String post(String urlStr, Map<String, String> params, Map<String, String> headers) throws Exception {
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        setTimeout(connection);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
        }

        long startTime = System.nanoTime();

        String paramStr = "";
        if(sb.length() > 0)
            paramStr = sb.substring(0, sb.length() - 1);
        connection.getOutputStream().write(paramStr.getBytes("UTF-8"));
        connection.getOutputStream().flush();

        String result = parseInputStream(connection);

        connection.disconnect();

        return result;
    }

    public static String get(String urlStr, Map<String, String> headers) throws Exception {
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        setTimeout(connection);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        long startTime = System.nanoTime();

        connection.connect();

        String result = parseInputStream(connection);

        connection.disconnect();

        return result;
    }

    private static void setTimeout(HttpURLConnection connection) {
        connection.setReadTimeout(2000);
        connection.setConnectTimeout(2000);
    }

    private static String parseInputStream(HttpURLConnection connection) throws Exception {
        System.out.println("response code:" + connection.getResponseCode());
        StringBuilder resultSb = new StringBuilder();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String temp = null;
            while ((temp = br.readLine()) != null) {
                resultSb.append(temp);
            }

            br.close();
        } else if (connection.getResponseCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
            InputStream is = connection.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String temp = null;
            while ((temp = br.readLine()) != null) {
                resultSb.append(temp);
            }

            br.close();
        }else if (connection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED){ //未认证
            throw new RuntimeException("UNAUTHORIZED");
        }
        return resultSb.toString();
    }
}
