
##  Get started
* Download the [Latest Release](https://github.com/BOHRweb/BoHr/releases) and unpack it to a desired directory.
* Prerequisites:  JDK 8 or above
* Run ,and enter a new password
```
%    java -jar bohr-core-1.0.0-shaded.jar
Please enter a new password: **********
Please repeat the new password: **********
```
## Configuration

### [https://github.com/BOHRweb/BoHr/blob/main/bohr-core/config/bohr.properties](config/bohr.properties)

* RESTful-API  
    online: [http://mainnetapi.bohrweb.org](http://mainnetapi.bohrweb.org)
     
    eg: [http://mainnetapi.bohrweb.org/v2.4.0/latest-block](http://mainnetapi.bohrweb.org/v2.4.0/latest-block)
    
    Local configuration:
```
# Be sure to set up authentication first before enabling API
api.enabled = true

# Listening address and port
api.listenIp = 127.0.0.1
api.listenPort = 5391

# Basic authentication
api.username = YOUR_API_USERNAME
api.password = YOUR_API_PASSWORD
```
#### [https://github.com/BOHRweb/BoHr/blob/main/bohr-core/config/ipfilter.json](config/ipfilter.json)

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
### Common API

* Blockchain

http://localhost:9388/v2.4.0/latest-block
     \- Get the latest block
 
http://localhost:9388/v2.4.0/latest-block-number
     \- Get the number of the latest block

http://localhost:9388/v2.4.0/block-by-hash?hash=
    \- Get block by hash  

http://localhost:9388/v2.4.0/block-by-number?number=
    \- Get block by number



* Create and query account

http://localhost:9388/v2.4.0/create-account?name=
    \- Create or import an account to wallet

http://localhost:9388/v2.4.0/account?address=
    \- Get account info

* Transaction 

Transaction fee：0.0001BoHr

http://localhost:9388/v2.4.0/transaction/transfer?from=
    \- Transfers coins to another address.

http://localhost:9388/v2.4.0/transaction?hash=
    \- Get transaction

http://localhost:9388/v2.4.0/transaction-result?hash=
    \- Get the result of the requested transaction.


### Other API

*  1、Node

  http://localhost:9388/v2.4.0/info
    \- Get node info

  http://localhost:9388/v2.4.0/peers
    \- Get all peers

  http://localhost:9388/v2.4.0/syncing
    \- Get syncing status

  http://localhost:9388/v2.4.0/pending-transactions
    \- Get pending transactions

  http://localhost:9388/v2.4.0/node?node=
    \- Add a node to connect

  http://localhost:9388/v2.4.0/blacklist?ip=
    \- Add an IP to blacklist

  http://localhost:9388/v2.4.0/whitelist?ip=
    \- Add an IP to whitelist

*  2、Account

  http://localhost:9388/v2.4.0/account?address=
    \- Get account info

  http://localhost:9388/v2.4.0/account/transactions?address=
    \- Get account transactions

  http://localhost:9388/v2.4.0/account/internal-transactions?address
    \- Get account internal transactions

  http://localhost:9388/v2.4.0/account/pending-transactions?address=&from=&to=
    \- Get pending transactions of the account

  http://localhost:9388/v2.4.0/account/votes?address=
    \- Get account votes

*  3、Wallet

  http://localhost:9388/v2.4.0/accounts
    \- List all accounts in wallet

  http://localhost:9388/v2.4.0/create-account?name=
    \- Create or import an account to wallet

  http://localhost:9388/v2.4.0/delete-account?address=
    \- Delete an account from wallet

  http://localhost:9388/v2.4.0/transaction/transfer?from=
    \- Transfer coins

http://localhost:9388/v2.4.0/transaction/unvote
    \- Unvote for a delegate

http://localhost:9388/v2.4.0/transaction/vote
    \- Vote for a delegate

http://localhost:9388/v1.0.0/transaction/delegate
    \- Register as delegate

http://localhost:9388/v2.4.0/sign-message?address=
    \- Sign a message

http://localhost:9388/v2.4.0/sign-raw-transaction
    \- Sign an unsigned raw transaction

*  4、Block

  http://localhost:9388/v2.4.0/latest-block
    \- Get the latest block

  http://localhost:9388/v2.4.0/latest-block-number
    \- Get the number of the latest block

  http://localhost:9388/v2.4.0/block-by-hash?hash=
    \- Get block by hash 

  http://localhost:9388/v2.4.0/block-by-number?number=
    \- Get block by number

  http://localhost:9388/v2.4.0/transaction?hash=
    \- Get transaction

  http://localhost:9388/v2.4.0/transaction-result?hash=
    \- Get transaction result

  http://localhost:9388/v2.4.0/transaction-limits?type=COINBASE
    \- Get transaction limits

*  5、Delegates

  http://localhost:9388/v2.4.0/delegate?address=
    \- Get delegate info

  http://localhost:9388/v2.4.0/delegates
    \- Get all delegates

  http://localhost:9388/v2.4.0/validators
    \- Get all validators

  http://localhost:9388/v2.4.0/vote?delegate=
    \- Get the vote between a delegate and a voter

  http://localhost:9388/v2.4.0/votes?delegate=
    \- Get all votes of a delegate

*  6、Tool

  http://localhost:9388/v2.4.0/estimate-gas?to=
    \- Estimate gas usage

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
