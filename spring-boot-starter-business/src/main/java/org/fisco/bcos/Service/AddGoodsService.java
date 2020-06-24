package org.fisco.bcos.Service;


import org.fisco.bcos.dao.GoodsMapper;
import org.fisco.bcos.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;



    public Boolean addGoods(Integer businessId, String url, String name, Integer priveOfMoney,Integer priceOfScore,Integer num){
        Goods newGoods=new Goods();

        newGoods.setBusinessId(businessId);
        newGoods.setGoodsName(name);
        newGoods.setGoodsUrl(url);
        newGoods.setNumber(num);
        newGoods.setPriceMoney(priveOfMoney);
        newGoods.setPriceScore(priceOfScore);
        newGoods.setTimeOfGrounding(new Date());
        if(goodsMapper.insert(newGoods)>0) {
            return true;
        }
        else
            return false;

    }
}
