package org.fisco.bcos.Controller;


import org.fisco.bcos.Service.addMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class addMoneyController {

    @Autowired
    private addMoneyService addMoneyService;


    @RequestMapping(value="/api/addMoney",method = RequestMethod.GET)
    public Map<String,Object> addMoney(@RequestParam("userId") Integer userId,@RequestParam("money") Integer money){
        Map<String,Object>res=new HashMap<>();
        if(addMoneyService.addMoney(userId,money)==true)
            res.put("response","success");
        else
            res.put("response","fail");
        return res;
    }
}
