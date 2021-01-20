
##  开始
* 从这里下载 [Latest Release](https://github.com/BOHRweb/BoHr/releases) 并解压.
* 需要:  JDK 8 or above
* 通过下面命令运行，输入密码,然后同步区块
```
%    java -jar bohr-core-1.0.0-shaded.jar
Please enter a new password: **********
Please repeat the new password: **********

11:57:09.016 INFO     BohrSync         Syncing finished, took 00:00:00
11:57:09.016 INFO     BohrBft          Entered new_height: height = 325026, # validators = 6
11:57:14.061 INFO     BohrSync         Syncing started, best known block = 325026
11:57:14.101 INFO     BohrSync         Block [number = 325026, view = 0, hash = ..., # txs = 4, # votes = 6]
...
```
## 配置

### [https://github.com/BOHRweb/BoHr/blob/main/bohr-core/config/bohr.properties](https://github.com/BOHRweb/BoHr/blob/main/bohr-core/config/bohr.properties)

* RESTful-API  
    在线地址: [http://mainnetapi.bohrweb.org](http://mainnetapi.bohrweb.org)
     
    例如: [http://mainnetapi.bohrweb.org/v2.4.0/latest-block](http://mainnetapi.bohrweb.org/v2.4.0/latest-block)
    
    本地配置:
```
# 配置是否开启API，默认为否
api.enabled = true

# 接口地址和端口
api.listenIp = 127.0.0.1
api.listenPort = 5391

# 配置用户名和密码
api.username = YOUR_API_USERNAME
api.password = YOUR_API_PASSWORD
```
#### [https://github.com/BOHRweb/BoHr/blob/main/bohr-core/config/ipfilter.json](https://github.com/BOHRweb/BoHr/blob/main/bohr-core/config/ipfilter.json)
* 配置IP过滤器,默认可不填
```javascript
{
  "rules" : [ {
    "type" : "ACCEPT",	
    "address" : "192.168.1.100"
  }, {
    "type" : "REJECT",	
    "address" : "192.168.1.101"
  } ]
}
```
##  BoHr Explorer

http://explorer.bohrweb.org/

## RESTful-API
[http://mainnetapi.bohrweb.org](http://mainnetapi.bohrweb.org)
### 常用API

* 区块信息

http://localhost:5391/v2.4.0/latest-block
     \- 返回最新的区块
 
http://localhost:5391/v2.4.0/latest-block-number
     \- 返回最新区块高度

http://localhost:5391/v2.4.0/block-by-hash?hash=
    \- 返回指定hash对应的区块信息  

http://localhost:5391/v2.4.0/block-by-number?number=
    \- 返回指定高度对应的区块信息



* 创建和查询账户

http://localhost:5391/v2.4.0/create-account?name=
    \- 创建新账户或者根据私钥导入账户

http://localhost:5391/v2.4.0/account?address=
    \- 获取指定帐户的基本信息

* 发送交易 

交易费率：0.0001BoHr（默认）

http://localhost:5391/v2.4.0/transaction/transfer?from=
    \- 转账

http://localhost:5391/v2.4.0/transaction?hash=
    \- 返回指定hash对应的交易

http://localhost:5391/v2.4.0/transaction-result?hash=
    \- 返回指定hash对应的交易结果


### API分类

*  1、节点类

  http://localhost:5391/v2.4.0/info
    \- 获取节点信息

  http://localhost:5391/v2.4.0/peers
    \- 获取所有节点

  http://localhost:5391/v2.4.0/syncing
    \- 返回区块同步状态

  http://localhost:5391/v2.4.0/pending-transactions
    \- 返回所有待执行交易

  http://localhost:5391/v2.4.0/node?node=
    \- 添加节点 参数格式: host:port

  http://localhost:5391/v2.4.0/blacklist?ip=
    \- 添加ip黑名单

  http://localhost:5391/v2.4.0/whitelist?ip=
    \- 添加ip白名单

*  2、账户类

  http://localhost:5391/v2.4.0/account?address=
    \- 获取指定帐户的基本信息

  http://localhost:5391/v2.4.0/account/transactions?address=
    \- 获取指定地址指定范围的交易记录 from/to (1/10)从第一笔到第十笔交易记录

  http://localhost:5391/v2.4.0/account/internal-transactions?address
    \- 获取指定地址指定范围的内部交易记录 from/to (1/10)从第一笔到第十笔交易记录

  http://localhost:5391/v2.4.0/account/pending-transactions?address=&from=&to=
    \- 获取指定地址指定范围的待执行交易记录 from/to (1/10)从第一笔到第十笔交易记录

  http://localhost:5391/v2.4.0/account/votes?address=
    \- 返回指定账户的质押

*  3、钱包类

  http://localhost:5391/v2.4.0/accounts
    \- 返回当前节点钱包的账户

  http://localhost:5391/v2.4.0/create-account?name=
    \- 创建新账户或者根据私钥导入账户

  http://localhost:5391/v2.4.0/delete-account?address=
    \- 删除账户

  http://localhost:5391/v2.4.0/transaction/transfer?from=
    \- 转账

http://localhost:5391/v2.4.0/transaction/unvote
    \- 取消指定代理质押

http://localhost:5391/v2.4.0/transaction/vote
    \- 质押指定代理

http://localhost:5391/v1.0.0/transaction/delegate
    \- 注册成为代理

http://localhost:5391/v2.4.0/sign-message?address=
    \- 消息签名

http://localhost:5391/v2.4.0/sign-raw-transaction
    \- 对无符号原始事务进行签名，然后返回其十六进制编码的字符串

*  4、区块类

  http://localhost:5391/v2.4.0/latest-block
    \- 返回最新的区块

  http://localhost:5391/v2.4.0/latest-block-number
    \- 返回最新区块高度

  http://localhost:5391/v2.4.0/block-by-hash?hash=
    \- 返回指定hash对应的区块信息  

  http://localhost:5391/v2.4.0/block-by-number?number=
    \- 返回指定高度对应的区块信息

  http://localhost:5391/v2.4.0/transaction?hash=
    \- 返回指定hash对应的交易

  http://localhost:5391/v2.4.0/transaction-result?hash=
    \- 返回指定hash对应的交易结果

  http://localhost:5391/v2.4.0/transaction-limits?type=COINBASE
    \- 返回各类型交易限制，包括最低交易费和最大交易规模。

*  5、代理类

  http://localhost:5391/v2.4.0/delegate?address=
    \- 返回指定代理的信息

  http://localhost:5391/v2.4.0/delegates
    \- 返回所有代理信息

  http://localhost:5391/v2.4.0/validators
    \- 返回所有验证器信息

  http://localhost:5391/v2.4.0/vote?delegate=
    \- 返回从用户到代理的质押

  http://localhost:5391/v2.4.0/votes?delegate=
    \- 返回用户的所有质押作为[选民地址] => [质押]的地址返回给代表

*  6、工具类

  http://localhost:5391/v2.4.0/estimate-gas?to=
    \- 估算交易的gas使用情况。




##  Demo
#### BaseGlobalConstant
* org.bohr.util.BaseGlobalConstant
```
 public static final String BASE_URL = "http://127.0.0.1:5391/v2.4.0/";
//    public static final String BASE_URL = "http://mainnetapi.bohrweb.org/v2.4.0/";
    public static final String YOUR_API_USERNAME = "YOUR_API_USERNAME1";
    public static final String YOUR_API_PASSWORD = "YOUR_API_PASSWORD1";
```

#### WalletDemoApi
* org.bohr.demo.WalletDemoApi
```
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
   
```


#### WalletDemoLocal
* org.bohr.demo.WalletDemoLocal
```
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

```





