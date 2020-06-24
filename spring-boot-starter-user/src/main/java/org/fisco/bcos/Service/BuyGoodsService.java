package org.fisco.bcos.Service;


import org.checkerframework.checker.units.qual.A;
import org.fisco.bcos.dao.BusinessMapper;
import org.fisco.bcos.dao.DealMapper;
import org.fisco.bcos.dao.GoodsMapper;
import org.fisco.bcos.dao.UserInformationMapper;
import org.fisco.bcos.model.Business;
import org.fisco.bcos.model.Deal;
import org.fisco.bcos.model.Goods;
import org.fisco.bcos.model.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BuyGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserInformationMapper userInformationMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private DealMapper dealMapper;



    //returnResult 1:交易成功 2：交易记录添加失败 3：商品/用户/商家信息更改失败
    public Integer buyGoods(Integer userId,Integer goodsId,Integer num,Integer flag){
        UserInformation user=userInformationMapper.selectByPrimaryKey(userId);
        Goods good=goodsMapper.selectByPrimaryKey(goodsId);
        Business business=businessMapper.selectByPrimaryKey(good.getBusinessId());
        if(flag==0){
            user.setSurplus(user.getSurplus()-good.getPriceMoney()*num);
            good.setNumber(good.getNumber()-num);
            business.setSurplus(business.getSurplus()+good.getPriceMoney()*num);
            Integer a=userInformationMapper.updateByPrimaryKey(user);
            Integer b=goodsMapper.updateByPrimaryKey(good);
            Integer c=businessMapper.updateByPrimaryKey(business);
            if(a>0 && b>0 &&c>0){
                Deal newDeal=new Deal();
                newDeal.setDealId(dealMapper.selectByUserId(userId).size()+1);
                newDeal.setDealNum(num);
                newDeal.setDealTime(new Date());
                newDeal.setGoodsId(goodsId);
                newDeal.setUserId(userId);
                newDeal.setWayOfDeal(false);
                if(dealMapper.insert(newDeal)>0)
                    return 1;
                else
                    return 2;
            }else
                return 3;



        }else
        {
            return 1;
        }
    }
}
