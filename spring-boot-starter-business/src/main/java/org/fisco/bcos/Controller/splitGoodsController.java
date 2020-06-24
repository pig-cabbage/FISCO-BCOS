package org.fisco.bcos.Controller;


import org.fisco.bcos.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class splitGoodsController {

    @Autowired
    private org.fisco.bcos.Service.splitGoodsService splitGoodsService;


    @RequestMapping("/splitbyscore")
    public Map<String,Object> splitByScorePrice(@RequestParam("begin") Integer begin,@RequestParam("end") Integer end){
        Map<String,Object>res=new HashMap<>();
        List<Goods> goodsList=splitGoodsService.splitByPrice(begin,end);
        res.put("response",goodsList);
        return res;
    }

    @RequestMapping("/splitbyname")
    public Map<String,Object> splitByName(@RequestParam("name") String name){
        Map<String,Object>res=new HashMap<>();
        List<Goods> goodsList=splitGoodsService.splitByName(name);
        res.put("response",goodsList);
        return res;
    }
}
