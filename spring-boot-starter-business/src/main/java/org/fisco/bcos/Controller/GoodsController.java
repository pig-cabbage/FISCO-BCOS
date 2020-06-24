package org.fisco.bcos.Controller;

import org.fisco.bcos.Service.GoodsService;
import org.fisco.bcos.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @RequestMapping("findGoodsByBusinessId")
    public Map<String,Object> findGoodsByBusinessId(@RequestParam Integer businessId){
        List<Goods> goods = goodsService.getGoodsByBusinessId(businessId);
        Map<String,Object>res=new HashMap<>();
        res.put("response",goods);
        return res;
    }
}
