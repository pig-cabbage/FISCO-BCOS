package org.fisco.bcos.Service;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contract.Asset;
import org.checkerframework.checker.units.qual.A;
import org.fisco.bcos.dao.BusinessMapper;
import org.fisco.bcos.dao.DealMapper;
import org.fisco.bcos.dao.GoodsMapper;
import org.fisco.bcos.dao.UserInformationMapper;
import org.fisco.bcos.model.Business;
import org.fisco.bcos.model.Deal;
import org.fisco.bcos.model.Goods;
import org.fisco.bcos.model.UserInformation;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;

@Service
public class buyGoodsService {



    @Autowired
    private Asset asset;

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserInformationMapper userInformationMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private DealMapper dealMapper;






    //returnResult 1:交易成功 2：交易记录添加失败 3：商品/用户/商家信息更改失败 4:执行合约失败
    public Integer buyGoodsByMoney(Integer userId,Integer goodsId,Integer num) throws Exception {
        UserInformation user=userInformationMapper.selectByPrimaryKey(userId);
        Goods good=goodsMapper.selectByPrimaryKey(goodsId);
        Business business=businessMapper.selectByPrimaryKey(good.getBusinessId());
        //调用合约接口
        TransactionReceipt send =asset.buyScore(BigInteger.valueOf(userId),BigInteger.valueOf(good.getBusinessId()), BigInteger.valueOf(num*good.getPriceScore())).send();
        BigInteger temp=asset.getBuyScoreOutput(send).getValue1();
        if(temp.intValue()==1) {


            user.setSurplus(user.getSurplus() - good.getPriceMoney() * num);
            good.setNumber(good.getNumber() - num);
            business.setSurplus(business.getSurplus() + good.getPriceMoney() * num);
            Integer a = userInformationMapper.updateByPrimaryKey(user);
            Integer b = goodsMapper.updateByPrimaryKey(good);
            Integer c = businessMapper.updateByPrimaryKey(business);
            if (a > 0 && b > 0 && c > 0) {
                Deal newDeal = new Deal();
                newDeal.setDealId(dealMapper.selectByUserId(userId).size() + 1);
                newDeal.setDealNum(num);
                newDeal.setDealTime(new Date());
                newDeal.setGoodsId(goodsId);
                newDeal.setUserId(userId);
                newDeal.setWayOfDeal(false);
                if (dealMapper.insert(newDeal) > 0)
                    return 1;
                else
                    return 2;
            } else
                return 3;
        }else
            return 4;



        }

    //returnResult 1:交易成功 2：交易记录添加失败 3：商品/用户/商家信息更改失败 4:合约执行失败
    public Integer buyGoodsByScore(Integer userId,Integer goodsId,Integer num) throws Exception {
        UserInformation user = userInformationMapper.selectByPrimaryKey(userId);
        Goods good = goodsMapper.selectByPrimaryKey(goodsId);
        Business business = businessMapper.selectByPrimaryKey(good.getBusinessId());
        //调用合约接口
        TransactionReceipt send = asset.buyScore(BigInteger.valueOf(userId), BigInteger.valueOf(good.getBusinessId()), BigInteger.valueOf(num * good.getPriceScore() * (-1))).send();
        BigInteger temp = asset.getBuyScoreOutput(send).getValue1();
        if (temp.intValue()==1) {
            good.setNumber(good.getNumber() - num);
            Integer b = goodsMapper.updateByPrimaryKey(good);
            if (b > 0) {
                Deal newDeal = new Deal();
                newDeal.setDealId(dealMapper.selectByUserId(userId).size() + 1);
                newDeal.setDealNum(num);
                newDeal.setDealTime(new Date());
                newDeal.setGoodsId(goodsId);
                newDeal.setUserId(userId);
                newDeal.setWayOfDeal(true);
                if (dealMapper.insert(newDeal) > 0)
                    return 1;
                else
                    return 2;
            } else
                return 3;
        }else
            return 4;
    }


}
