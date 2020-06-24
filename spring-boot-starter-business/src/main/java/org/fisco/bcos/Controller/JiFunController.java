package org.fisco.bcos.Controller;

import org.fisco.bcos.Service.JiFunService;
import org.fisco.bcos.model.BankBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//和积分有关的操作
@RestController
public class JiFunController {
    @Autowired
    JiFunService jiFunService;
    //申请或承兑积分
    @RequestMapping("shenQingOrChengDuiJiFen")
    public String shenQingOrChengDuiJiFen(HttpServletRequest httpServletRequest, @RequestParam int score){
        if (httpServletRequest.getSession().getAttribute("user")!=null)
        {
            BankBusiness bankBusiness=new BankBusiness();
            bankBusiness.setScore(score);
            bankBusiness.setBusinessId((Integer) httpServletRequest.getSession().getAttribute("user"));
            bankBusiness.setAccept(0);
            jiFunService.saveRecord(bankBusiness);
            return "已提交申请，等待银行方同意";
        }
        else return "请先登陆";
    }

    //根据id查询积分授信和承兑记录

    @RequestMapping("selectByBusinessId")
    public Map<String, Object> selectByidbankBusinessId(@RequestParam int businessId){
        List<BankBusiness> bankBusinesses = jiFunService.selectByBusinessId(businessId);
        Map<String,Object> res=new HashMap<>();
        res.put("response",bankBusinesses);
        return res;
    }
    //查询积分接口
    @RequestMapping("findCurrentBusinessScore")
    public Map<String,Object> findCurrentBusinessScore(HttpServletRequest httpServletRequest) throws Exception {
        Object user = httpServletRequest.getSession().getAttribute("user");
        Map<String,Object> res=new HashMap<>();
        if (user==null) {
            res.put("response","请先登陆");
            return res;
        }
        else
        {
           int id= (Integer)user;
        //此处调用合约查询积分 参数为id 即公司账号
            BigInteger score = jiFunService.searchBussinessScore(id);
            res.put("response",score);
            return res;
            }
    }

}
