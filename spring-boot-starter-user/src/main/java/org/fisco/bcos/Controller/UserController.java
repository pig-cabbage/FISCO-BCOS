package org.fisco.bcos.Controller;

import org.fisco.bcos.Service.UserService;
import org.fisco.bcos.model.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("getUserById")
    public Map<String,Object> getUserById(@RequestParam int userId){
        UserInformation userInformation = userService.getUserInformationById(userId);
        Map<String,Object> res=new HashMap<>();
        if (userInformation==null){
            res.put("查询错误","该ID不存在");
            return res;
        }
        else {
            res.put("UserId",userInformation.getUserId());
            res.put("UserAccount",userInformation.getUserAccount());
            res.put("UserName",userInformation.getUserName());
            res.put("UserSecret",userInformation.getUserSecret());
            res.put("Surplus",userInformation.getSurplus());
            return res;
        }
    }

    @RequestMapping("insertUserInformation")
    public String insertUserInformation( @RequestParam int surplus,@RequestParam String userAccount,
                                        @RequestParam String userName,@RequestParam String userSecret){
        UserInformation userInformation=new UserInformation();
        userInformation.setSurplus(surplus);
        userInformation.setUserAccount(userAccount);
        userInformation.setUserName(userName);
        userInformation.setUserSecret(userSecret);
        if(userService.insertUserInformation(userInformation)>0)
            return "插入成功";
        else
            return "插入失败";

    }
    @RequestMapping("updateUserInformation")
    public String updateUserInformation(@RequestParam int userId , @RequestParam int surplus,@RequestParam String userAccount,
                                        @RequestParam String userName,@RequestParam String userSecret){
        UserInformation userInformation=new UserInformation();
        userInformation.setUserId(userId);
        userInformation.setSurplus(surplus);
        userInformation.setUserAccount(userAccount);
        userInformation.setUserName(userName);
        userInformation.setUserSecret(userSecret);
        if(userService.getUserInformationById(userId)==null)
            return "ID不存在，更新失败";
        else{
            if(userService.updateUserInformation(userInformation)>0)
                return "更新成功";
            else
                return "更新失败";
        }
    }
}
