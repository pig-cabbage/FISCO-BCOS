package org.fisco.bcos.Controller;

import org.fisco.bcos.Service.BusinessService;
import org.fisco.bcos.Service.JiFunService;
import org.fisco.bcos.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
//根据id获取企业信息
//更改企业信息
@RestController
public class BusinessController {
    @Autowired
    BusinessService businessService;
    @Autowired
    JiFunService jiFunService;

    @RequestMapping("/getBusinessByBusinessId")
    public Map<String,Object> getBusinessByBusinessId(@RequestParam int businessId) throws Exception {
        Business business = businessService.getBusinessByBusinessId(businessId);
        Map<String,Object> res=new HashMap<>();
        if (business!=null){
            res.put("businessId",business.getBusinessId());
            res.put("name",business.getName());
            res.put("Surplus",business.getSurplus());
            res.put("password",business.getPassWord());
            BigInteger score = jiFunService.searchBussinessScore(businessId);
            res.put("score",score);
            return res;
        }
        else{
            res.put("查询错误","该BusinessId不存在");
            return res;
        }

    }


    @RequestMapping("/updateBusiness")
    public String updateBusiness(@RequestParam int businessId,@RequestParam String name,@RequestParam int surplus,@RequestParam String passWord){
        Business business=new Business();
        business.setBusinessId(businessId);
        business.setName(name);
        business.setSurplus(surplus);
        business.setPassWord(passWord);
        if (businessService.getBusinessByBusinessId(businessId)!=null)
        {
            businessService.update(business);
            return "更新成功";
        }
        else return "该businessId不存在，更新失败";

    }

}
