package org.fisco.bcos.Service;

import org.fisco.bcos.Contract.Asset;
import org.fisco.bcos.Contract.HelloWorld;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.dao.BankBusinessMapper;
import org.fisco.bcos.model.BankBusiness;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertTrue;

@Service
public class JiFunService {
    @Autowired private Web3j web3j;
    @Autowired private Credentials credentials;
    @Autowired
    BankBusinessMapper bankBusinessMappers;
    @Autowired
    Asset asset;

    public void saveRecord(BankBusiness bankBusiness){
        bankBusinessMappers.insert(bankBusiness);
    }
    public List<BankBusiness> selectByBusinessId(int businessId){
        return bankBusinessMappers.selectByBusinessId(businessId);
    }
//    public Asset getAsset() {
//        Asset  asset = Asset.load("0x0dbffbd2546c31f5dcf05bce9a69e58a2600dea9", web3j,
//                credentials,
//                new StaticGasProvider(
//                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
//        return asset;
//    }
    public BigInteger bussinessRegister(int businessId) throws Exception {
       // Asset asset=this.getAsset();
        TransactionReceipt regist = asset.bussinessRegister(BigInteger.valueOf(businessId)).send();
        return asset.getBussinessRegisterOutput(regist).getValue1();
    }
    public BigInteger searchBussinessScore(int businessId) throws Exception {
      //  Asset asset=this.getAsset();
        BigInteger score = asset.searchBussinessScore(BigInteger.valueOf(businessId)).send();
        return score;
    }
}
