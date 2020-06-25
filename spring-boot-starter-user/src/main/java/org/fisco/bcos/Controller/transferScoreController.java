package org.fisco.bcos.Controller;


import org.fisco.bcos.Service.transferScoreService;
import org.fisco.bcos.contract.Asset;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RestController
public class transferScoreController {

    @Autowired
    private transferScoreService transferScoreService;
    @Autowired
    private Asset asset;


    @RequestMapping(value = "/api/transferScore",method = RequestMethod.GET)
    public Map<String,Object> transferScore(@RequestParam("sendId") Integer sendId, @RequestParam("getId") Integer getId, @RequestParam("score") Integer score) throws Exception {
        Map<String,Object> res=new HashMap<>();
        TransactionReceipt send=asset.userTransfer(BigInteger.valueOf(sendId),BigInteger.valueOf(getId), BigInteger.valueOf(score)).send();
        BigInteger temp=asset.getUserTransferOutput(send).getValue1();
        if(temp.intValue()==1)
            res.put("response","success");
        else
            res.put("response","fail");
        return res;

    }
}
