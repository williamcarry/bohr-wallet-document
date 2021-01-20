package org.bohr.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class BohrUtils {

    //transferOut
    public static String transferOut(String fromAddress, String toAddress, BigDecimal amount, String username, String password, String url, Long nonce) {
        if (StringUtils.isBlank(fromAddress)) {
            return "fromAddress is null ===>>> ";
        }
        if (StringUtils.isBlank(toAddress)) {
            return "toAddress is null ===>>> ";
        }
        if (StringUtils.isAnyBlank(username, password, url)) {
            return "username,password,url is null ===>>> ";
        }

        Map<String, String> params = new HashMap<>();
        params.put("from", fromAddress);
        params.put("to", toAddress);
        params.put("value", Amount.of(amount, Unit.bohr).toString());
        if (nonce != null) {
            params.put("nonce", nonce.toString());
        }

        Map<String, String> headers = new HashMap<>();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        headers.put("Authorization", authHeader);

        try {
            String result = HttpUtils.post(url, params, headers);
            return result;
        } catch (Exception e) {
            return "from [" + fromAddress + "]transfer to[" + toAddress + "] error:" + e.getMessage();
        }
    }

    //transfersOut
    public static String transfersOut(String froms, String tos, String values, String nonces, String username, String password, String url) {
        Map<String, String> params = new HashMap<>();
        params.put("froms", froms);
        params.put("tos", tos);
        params.put("values", values);
        params.put("nonces", nonces);

        Map<String, String> headers = new HashMap<>();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        headers.put("Authorization", authHeader);

        try {
            String result = HttpUtils.post(url, params, headers);
            return result;
        } catch (Exception e) {
            return "error:" + e.getMessage();
        }
    }

    //bohr tokenTransferOut
    public static String tokenTransferOut(String fromAddress, String contractAddress, String data, String username, String password, String url) {
        if (StringUtils.isBlank(fromAddress)) {
            return "fromAddress can not null ===>>> ";
        }
        if (StringUtils.isBlank(contractAddress)) {
            return "contractAddress can not null ===>>> ";
        }
        if (StringUtils.isBlank(data)) {
            return "data can not null ===>>> ";
        }
        if (StringUtils.isAnyBlank(username, password, url)) {
            return "username,password,url  ===>>> ";
        }

        Map<String, String> params = new HashMap<>();
        params.put("from", fromAddress);
        params.put("to", contractAddress);
        params.put("value", "0");
        params.put("data", data);
        params.put("gas", "60000");
        params.put("gasPrice", "1");

        Map<String, String> headers = new HashMap<>();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        headers.put("Authorization", authHeader);

        try {
            String result = HttpUtils.post(url, params, headers);
            return result;
        } catch (Exception e) {
            return "from[" + fromAddress + "] to [" + contractAddress + "],data[" + data + "]error:" + e.getMessage();
        }
    }

    // create-account
    public static String createAccount(String username, String password, String url) {
        if (StringUtils.isAnyBlank(username, password, url)) {
            return "username,password,url  ===>>> ";
        }

        //params
        Map<String, String> params = new HashMap<>();

        //headers
        Map<String, String> headers = new HashMap<>();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        headers.put("Authorization", authHeader);

        try {
            String result = HttpUtils.post(url, params, headers);
            //  "result": "publicKey:0xab5106a7ef96267954a863d2267cfdf2f3e38c51,privateKey:0x302e020100300506032b657004220420f8713ed033cf3c3b86d08f7bf0831ecaf3a32e1a6cdd989980e309070cd2fda7"
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.getBoolean("success")) {
                return jsonObject.getString("result");
            } else {
                return jsonObject.getString("message");
            }
        } catch (Exception e) {
            return "error:" + e.getMessage();
        }
    }


    //queryBalance
    public static BigDecimal queryBalance(String url) {
        try {

            Map<String, String> headers = new HashMap<>();
            String result = HttpUtils.get(url, headers);

            System.out.println(result);
            JSONObject jsonObject = JSONObject.parseObject(result);

            boolean success = jsonObject.getBoolean("success");
            if (success) {
                JSONObject resultJson = jsonObject.getJSONObject("result");

                String b = resultJson.getString("available");

                BigDecimal value = new BigDecimal(b);

                BigDecimal factor = BigDecimal.TEN.pow(9);   //BoHr 9

                return value.divide(factor);
            } else {
                return BigDecimal.ZERO;
            }
        } catch (Exception e) {

            return BigDecimal.ZERO;
        }
    }

    //queryNonce
    public static Long queryNonce(String address) throws Exception {

        Map<String, String> headers = new HashMap<>();
        String result = HttpUtils.get(BaseGlobalConstant.ACCOUNT_QUERY_URL + address, headers);


        JSONObject jsonObject = JSONObject.parseObject(result);

        boolean success = jsonObject.getBoolean("success");
        if (success) {
            JSONObject resultJson = jsonObject.getJSONObject("result");

            String b = resultJson.getString("nonce");

            return Long.parseLong(b);
        } else {
            throw new RuntimeException("error");
        }
    }

    //broadcastRawTransaction
    public static String broadcastRawTransaction(String raw) throws Exception {

        Map<String, String> headers = new HashMap<>();
        String result = HttpUtils.get(BaseGlobalConstant.BROADCAST_RAW_TRANSACTION_URL + raw, headers);

        return result;
    }

    //transactionResult
    public static String getTransaction(String hash) throws Exception {

        Map<String, String> headers = new HashMap<>();
        String result = HttpUtils.get(BaseGlobalConstant.TRANSACTION_URL + hash, headers);

        return result;
    }

    //transactionResult
    public static String getTransactionResult(String hash) throws Exception {

        Map<String, String> headers = new HashMap<>();
        String result = HttpUtils.get(BaseGlobalConstant.TRANSACTION_RESULT_URL + hash, headers);

        return result;
    }
}
