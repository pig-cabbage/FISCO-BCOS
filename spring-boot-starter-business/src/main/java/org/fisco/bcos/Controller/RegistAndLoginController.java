package org.fisco.bcos.Controller;

import org.fisco.bcos.Service.BusinessService;
import org.fisco.bcos.Service.JiFunService;
import org.fisco.bcos.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

//登陆和注册操作
@RestController
public class RegistAndLoginController {
    @Autowired
    BusinessService businessService;
    @Autowired
    JiFunService jiFunService;
    @RequestMapping("/registBusiness")
    public String registBusiness(@RequestParam int businessId,@RequestParam String passWord) throws Exception {
        Business business=new Business();
        business.setBusinessId(businessId);
        business.setPassWord(passWord);
        business.setSurplus(10000);
        if (businessService.getBusinessByBusinessId(businessId)==null)
        {
            businessService.insert(business);
            jiFunService.bussinessRegister(businessId);
            return "注册成功";
        }
        else return "该businessId已存在，注册失败";

    }
    @RequestMapping("loginBusiness")
    public Map<String,Object> login(@RequestParam int businessId, @RequestParam String passWord, HttpSession httpSession) throws Exception {
        Business b = new Business();
        Map<String,Object>res=new HashMap<>();
        b.setPassWord(passWord);
        b.setBusinessId(businessId);
        Business business = businessService.selectByIdAndPassword(b);
        if (business!=null){
            httpSession.setAttribute("user",businessId);
            res.put("response_message","succeed");
            res.put("response_data",business);
            BigInteger score = jiFunService.searchBussinessScore(businessId);
            res.put("score",score);
            return res;
        }
        else {
            res.put("response_message","fail");
            return res;
        }
    }

}
