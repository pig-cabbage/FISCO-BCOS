package org.fisco.bcos.Service;


import jnr.ffi.annotations.In;
import org.fisco.bcos.contract.HelloWorld;
import org.fisco.bcos.dao.UserUserScoreMapper;
import org.fisco.bcos.model.UserUserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class transferScoreService {


    @Autowired
    private UserUserScoreMapper userUserScoreMapper;
    @Autowired
    private HelloWorld helloWorld;


    public Boolean transferScore(Integer sendId, Integer getId, Integer score) throws Exception{
        //区块链操作
        System.out.println("HelloWorld address is: " + helloWorld.getContractAddress());
        // call set function
        helloWorld.set("Hello, World!").send();
        // call get function
        String result = helloWorld.get().send();
        System.out.println(result);
        UserUserScore newRecord=new UserUserScore();
        newRecord.setSendId(sendId);
        newRecord.setGetId(getId);
        newRecord.setScore(score);
        newRecord.setTime(new Date());
        if(userUserScoreMapper.insertSelective(newRecord)>0)
            return true;
        else
            return false;
    }
}
