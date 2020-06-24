package org.fisco.bcos.Controller;


import org.fisco.bcos.Service.DeleteGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class deleteGoodsController {

    @Autowired
    private DeleteGoodsService deleteGoodsService;


    @RequestMapping("/deleteGoods")
    public Map<String,Object> deleteGods(@RequestParam("goodsId") Integer goodsId){
        Map<String,Object> res=new HashMap<>();
        if(deleteGoodsService.deleteGoods(goodsId)==true)
            res.put("response","success");
        else
            res.put("response","fail");
        return res;
    }

}
