package org.fisco.bcos.Controller;


import org.fisco.bcos.Service.userService;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contract.Asset;
import org.fisco.bcos.model.UserInformation;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private userService userService;
    @Autowired
    private Asset asset;

    @RequestMapping(value = "/api/login",method = RequestMethod.GET)
    public Map<String,Object> getUserById(@RequestParam("account") String account,@RequestParam("password") String password) throws Exception {

            UserInformation userInformation = userService.getUserInformationById(Integer.parseInt(account));
            Map<String, Object> res = new HashMap<>();
            if (userInformation == null) {
                res.put("response", "账号不存在");

            } else {
                if(userInformation.getUserSecret().equals(password)==true) {
                    //调用合约获取用户积分
                    TransactionReceipt send=asset.searchUserScore(BigInteger.valueOf(Integer.parseInt(account))).send();
                    BigInteger temp=asset.getSearchUserScoreOutput(send).getValue1();
                    res.put("userinformation",userInformation);
                    res.put("score",temp.intValue());

                }else
                    res.put("response","密码错误");
            }
            return res;

    }

    @RequestMapping(value="/api/register",method = RequestMethod.GET)
    public Map<String,Object> insertUserInformation( @RequestParam String userAccount, @RequestParam String password) throws Exception {
        Map<String,Object>res=new HashMap<>();
        UserInformation userInformation=new UserInformation();
        userInformation.setUserId(Integer.parseInt(userAccount));

        userInformation.setUserAccount(userAccount);

        userInformation.setUserSecret(password);
        if(userService.insertUserInformation(userInformation)>0) {
            //调用合约接口
            TransactionReceipt send = asset.userRegister(BigInteger.valueOf(Integer.parseInt(userAccount))).send();
            BigInteger temp = asset.getUserRegisterOutput(send).getValue1();
            if(temp.intValue()==1) {
                res.put("response", "success");
            }else{
                res.put("response","合约注册失败");
            }
        }
        else
            res.put("response","fail");
        return res;

    }
    @RequestMapping(value="/api/update",method = RequestMethod.POST)
    public Map<String,Object> updateUserInformation(UserInformation user){
        Map<String,Object>res=new HashMap<>();
            if(userService.updateUserInformation(user)>0)
                res.put("resonse","success");
            else
                res.put("response","fail");
            return res;

    }
}
