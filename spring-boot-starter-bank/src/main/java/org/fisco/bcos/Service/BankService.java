package org.fisco.bcos.Service;

import org.fisco.bcos.Contract.Asset;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.dao.BankBusinessMapper;
import org.fisco.bcos.model.BankBusiness;
import org.fisco.bcos.model.Business;
import org.fisco.bcos.model.BusinessScore;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankService {
    @Autowired
    BankBusinessMapper bankBusinessMapper;
    @Autowired private Web3j web3j;
    @Autowired private Credentials credentials;
    @Autowired Asset asset;

    public List<BankBusiness> selectByBusinessId(int businessId){
        return bankBusinessMapper.selectByBusinessId(businessId);
    }
    public void insertRecord(BankBusiness record){
        bankBusinessMapper.insert(record);
    }
    public void updateRecord(BankBusiness record){
        bankBusinessMapper.updateByPrimaryKeySelective(record);
    }
    public List<BankBusiness> findAllRecord(){
        return bankBusinessMapper.findAllRecord();
    }
//    public Asset getAsset() {
//        Asset  asset = Asset.load("0x0dbffbd2546c31f5dcf05bce9a69e58a2600dea9", web3j,
//                credentials,
//                new StaticGasProvider(
//                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
//        return asset;
//    }
    public BigInteger shenQingOrChengDuiJiFenHandler(int businessId, int score) throws Exception {
       // Asset asset=this.getAsset();
        TransactionReceipt send = asset.applyScore(BigInteger.valueOf(businessId), BigInteger.valueOf(score)).send();
        return asset.getApplyScoreOutput(send).getValue1();
    }
    public Map<String,Object> searchAllBussinessScore() throws Exception {
      //  Asset asset=this.getAsset();
        Tuple2<List<byte[]>, List<BigInteger>> result = asset.searchAllBussinessScore().send();
        List<byte[]> businessIdsInByte=result.getValue1();
        String id;
        BigInteger bigInteger;
        List<BigInteger> scores=result.getValue2();
        Map<String,Object> res=new HashMap<>();
        BusinessScore businessScore;
        List<BusinessScore> businessScores=new ArrayList<>();
        for (int i = 0; i < businessIdsInByte.size(); i++) {
            businessScore=new BusinessScore();
            id=new String(businessIdsInByte.get(i));
            businessScore.setBusinessId(id.replaceAll("[\u0000]", ""));
             bigInteger = scores.get(i);
            businessScore.setScore(bigInteger);
            businessScores.add(businessScore);
        }
        res.put("response",businessScores);
        return res;
    }
}
