package org.bohr.demo;

import java.math.BigDecimal;
import org.bohr.util.*;

public class WalletDemoApi {
    public static void main(String args[]) throws Exception{

        String addressBase58 = "BfHBKC9ozidaHYGwhLcLzRC4Hri2EjjWut1";
        String addressHex = BohrAddressUtil.base58ToHex(addressBase58);

        //1. getBalance
        BigDecimal bigDecimal = BohrUtils.queryBalance(BaseGlobalConstant.BASE_URL+"account?address="+addressHex);
        System.out.println(bigDecimal.floatValue());//2510.584

        //2. createAccount
        String accountJson = BohrUtils.createAccount(BaseGlobalConstant.YOUR_API_USERNAME,BaseGlobalConstant.YOUR_API_PASSWORD,BaseGlobalConstant.BASE_URL + "create-account?name=33");
        System.out.println(accountJson);//0x81c6a62d1e129e1cef9b88892b10f77ce57a969f

        //3. transferOut
        String fromAddress = "0x06fc0d2521fb0cb1f20288e6d70099100207e544";
        String toAddress = "0x8dd08499b15a0eb02cc30573f8202805bf9b8e54";
        BigDecimal amount = new BigDecimal(0.01);
        String username = BaseGlobalConstant.YOUR_API_USERNAME;
        String password = BaseGlobalConstant.YOUR_API_PASSWORD;
        String url = BaseGlobalConstant.BASE_URL + "transaction/transfer";
        Long nonce = new Long(0);
        String transferOutJson = BohrUtils.transferOut( fromAddress,  toAddress,  amount,  username,  password,  url,  nonce);
        System.out.println(transferOutJson);
        //eg: {"success":true,"message":"successful operation","result":"0xd9ee7874e6d25a24e48b2922e5f8dcdfe35c9bd5c37fa9ea1716a62fd83c43cc"}


        //4. transactionResult
        String transHash = "0xd9ee7874e6d25a24e48b2922e5f8dcdfe35c9bd5c37fa9ea1716a62fd83c43cc";
        String transactionJson = BohrUtils.getTransaction(transHash);
        System.out.println(transactionJson);
        //eg: {"success":true,"message":"successful operation","result":{"hash":"0xd9ee7874e6d25a24e48b2922e5f8dcdfe35c9bd5c37fa9ea1716a62fd83c43cc","type":"TRANSFER","from":"0x06fc0d2521fb0cb1f20288e6d70099100207e544","to":"0x8dd08499b15a0eb02cc30573f8202805bf9b8e54","value":"10000000","fee":"100000","nonce":"0","timestamp":"1611139553289","data":"0x","gas":"0","gasPrice":"0"}}

        String transactionResultJson = BohrUtils.getTransactionResult(transHash);
        System.out.println(transactionResultJson);
        //eg: {"success":true,"message":"successful operation","result":{"blockNumber":"8313369","code":"SUCCESS","logs":[],"returnData":"0x","contractAddress":null,"gas":"0","gasPrice":"0","gasUsed":"0","fee":"100000","internalTransactions":[]}}

    }




}
