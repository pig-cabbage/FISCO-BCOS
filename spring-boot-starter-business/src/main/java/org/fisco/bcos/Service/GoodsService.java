package org.fisco.bcos.Service;

import org.fisco.bcos.dao.GoodsMapper;
import org.fisco.bcos.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    public List<Goods> getGoodsByBusinessId(Integer businessId){
        return goodsMapper.selectByBusinessId(businessId);
    }
}
