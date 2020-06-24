package org.fisco.bcos.Controller;


import jnr.ffi.annotations.In;
import org.fisco.bcos.Service.BuyGoodsService;
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
    private BuyGoodsService buyGoodsService;


    @RequestMapping(value = "/buy",method = RequestMethod.GET)
    public Map<String,Object> buy(@RequestParam("userId") Integer userId,@RequestParam("goodsId") Integer goodsId,@RequestParam("num") Integer num,@RequestParam("flag") Integer flag){
        Map<String,Object> res=new HashMap<>();
        Integer result=buyGoodsService.buyGoods(userId,goodsId,num,flag);
        if(result==1)
            res.put("response","success");
        else{
            if(result==2)
                res.put("response","交易记录添加失败 ");
            else
                res.put("response","商品/用户/商家信息更改失败");

        }
        return res;



    }
}
