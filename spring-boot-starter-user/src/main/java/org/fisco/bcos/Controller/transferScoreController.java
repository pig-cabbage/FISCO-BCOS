package org.fisco.bcos.Controller;


import org.fisco.bcos.Service.TransferScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class transferScoreController {

    @Autowired
    private TransferScoreService transferScoreService;


    @RequestMapping(value = "/transferScore",method = RequestMethod.GET)
    public Map<String,Object> transferScore(@RequestParam("sendId") Integer sendId, @RequestParam("getId") Integer getId, @RequestParam("score") Integer score){
        Map<String,Object> res=new HashMap<>();
        try{
            Boolean result=transferScoreService.transferScore(sendId,getId,score);
            if(result==true)
            res.put("response","success");
        else
            res.put("response","fail");
        } catch (Exception e) {
            System.out.println("controller fail");
            e.printStackTrace();
        }
        finally {
            return res;
        }

    }
}
