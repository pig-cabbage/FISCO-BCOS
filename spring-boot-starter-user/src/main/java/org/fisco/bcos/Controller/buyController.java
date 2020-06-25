package org.fisco.bcos.Controller;



import org.fisco.bcos.Service.buyGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
public class buyController {

    @Autowired
    private buyGoodsService buyGoodsService;


    @RequestMapping(value = "/api/buy/money",method = RequestMethod.GET)
    public Map<String,Object> buyByMoney(@RequestParam("userId") Integer userId,@RequestParam("goodsId") Integer goodsId,@RequestParam("num") Integer num) throws Exception {
        Map<String,Object> res=new HashMap<>();

        Integer result=buyGoodsService.buyGoodsByMoney(userId,goodsId,num);
        if(result==1)
            res.put("response","success");
        else{
            if(result==2)
                res.put("response","交易记录添加失败 ");
            else
                if(result==3) {
                    res.put("response", "商品/用户/商家信息更改失败");

                }else
                    res.put("response", "合约执行失败");
        }
        return res;



    }

    @RequestMapping(value = "/api/buy/score",method = RequestMethod.GET)
    public Map<String,Object> buyByScore(@RequestParam("userId") Integer userId,@RequestParam("goodsId") Integer goodsId,@RequestParam("num") Integer num) throws Exception {
        Map<String,Object> res=new HashMap<>();
        Integer result=buyGoodsService.buyGoodsByScore(userId,goodsId,num);
        if(result==1)
            res.put("response","success");
        else{
            if(result==2)
                res.put("response","交易记录添加失败 ");
            else
            if(result==3) {
                res.put("response", "商品/用户/商家信息更改失败");

            }else
                res.put("response", "合约执行失败");

        }
        return res;



    }

}
