package org.fisco.bcos.Controller;


import org.fisco.bcos.Service.getDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class getDealController {

    @Autowired
    private getDealService getDealService;



    @RequestMapping(value = "/api/getDeal",method = RequestMethod.GET)
    public Map<String,Object> getDeal(@RequestParam("userId") Integer userId){
        Map<String,Object> res=new HashMap<>();
        res.put("response",getDealService.getDeal(userId));
        return res;
    }


}
