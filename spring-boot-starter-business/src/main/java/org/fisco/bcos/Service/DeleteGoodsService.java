package org.fisco.bcos.Service;


import org.fisco.bcos.dao.GoodsMapper;
import org.fisco.bcos.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DeleteGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;





    public Boolean deleteGoods(Integer goodsId){
        Goods newGoods=goodsMapper.selectByPrimaryKey(goodsId);
        newGoods.setTimeOfUndergrounding(new Date());
        if(goodsMapper.updateByPrimaryKey(newGoods)>0)
            return true;
        else
            return false;
    }
}
