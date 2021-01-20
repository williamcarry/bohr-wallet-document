package org.bohr.demo;

import org.bohr.util.Amount;
import org.bohr.util.BohrAddressUtil;
import org.bohr.util.BohrUtils;
import org.bohr.util.Unit;
import org.bohr.util.sign.Bytes;
import org.bohr.util.sign.Network;
import org.bohr.util.sign.Transaction;
import org.bohr.util.sign.TransactionType;
import org.bohr.util.sign.crypto.Hex;
import org.bohr.util.sign.crypto.Key;

public class WalletDemoLocal {

    public static void main(String args[]) throws Exception{

        //1.create new Account
        Key newKey = new Key();

        String address = Hex.encode0x(newKey.toAddress());
        String addressBase58 = BohrAddressUtil.hexToBase58(address);
        String privateKey = Hex.encode0x(newKey.getPrivateKey());
        System.out.println(address); //eg: 0x5947de0b929697ccc7cc7a27a394877094afe30f
        System.out.println(addressBase58); //eg: BfCHZdJ82XY7ZHUyLk9rEXXQFgVukKsaUjz
        System.out.println(privateKey); //eg: 0x302e020100300506032b657004220420cecd91798df5df276be375a0eb52605d8d6db39aedbe8c1c436c2a590b09620b



        //2. Account from privateKey
        String privateKeyStr = "0x302e020100300506032b657004220420df01326771e648be5aa609d1f890c8aa76417e8fe952b29cbed1ae1811cbc1b8";
        Key fromKey = new Key(Hex.decode0x(privateKeyStr));



        //3. transferOut
        Amount amount =  Amount.of(1, Unit.bohr);
        long nonce = 2;
        String toAddress = "0x8dd08499b15a0eb02cc30573f8202805bf9b8e54";
        byte[] to = Hex.decode0x(toAddress);

        Transaction transaction = new Transaction(Network.MAINNET, TransactionType.TRANSFER, to, amount, Amount.of(100, Unit.MICRO_bohr), nonce,
                System.currentTimeMillis(), Bytes.EMPTY_BYTES, 0L, Amount.ZERO);
        transaction.sign(fromKey);

        String txHashStr = BohrUtils.broadcastRawTransaction(Hex.encode(transaction.toBytes()));
        System.out.println(txHashStr);//eg: {"success":true,"message":"successful operation","result":"0x0c48a4354cfb5b38969ec891cf4d44732c918f7eb95556fe5dff0e6021d9744f"}



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
