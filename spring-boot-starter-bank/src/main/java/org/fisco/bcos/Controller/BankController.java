package org.fisco.bcos.Controller;

import org.fisco.bcos.Service.BankService;
import org.fisco.bcos.Service.BusinessService;
import org.fisco.bcos.model.BankBusiness;
import org.fisco.bcos.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BankController {
    @Autowired
    BankService bankService;
    @Autowired
    BusinessService businessService;
    int account= 123;
    String passWord="123";
    //根据id查询积分授信和承兑记录
    @RequestMapping("selectRecordByBusinessId")
    public Map<String,Map<String,Object>> selectRecordByBusinessId(@RequestParam int businessId){
        List<BankBusiness> bankBusinesses = bankService.selectByBusinessId(businessId);
        Map<String,Map<String,Object>> res=new HashMap<>();
        if(bankBusinesses!=null&&bankBusinesses.size()>0)
        {
            Map<String,Object> map;
            for (int i = 0; i <bankBusinesses.size() ; i++) {
                map=new HashMap<>();
                map.put("IdbankBusinessId",bankBusinesses.get(i).getIdbankBusinessId());
                map.put("BusinessId",bankBusinesses.get(i).getBusinessId());
                map.put("Score",bankBusinesses.get(i).getScore());
                map.put("Accept",bankBusinesses.get(i).getAccept());
                res.put(i+1+"",map);
            }

            return res;
        }
        else {
            Map<String,Object> map=new HashMap<>();
            map.put("无记录","该id没有记录");
            res.put("无记录",map);
            return res;
        }
    }
    @RequestMapping("loginBank")
    public String loginBank (HttpSession httpSession,@RequestParam int account, @RequestParam String passWord){
        if (account==this.account&&passWord.equals(this.passWord))
        {
            httpSession.setAttribute("user",account);
            return "登陆成功";
        }
        else return "账户或密码错误";
    }
    @RequestMapping("registBank")
    @CrossOrigin
    public  String registBank(@RequestParam int account,@RequestParam String passWord){
        this.account=account;
        this.passWord=passWord;
        return "注册成功";
    }
    //查询积分授信情况
    @RequestMapping("findCurrentBusinessScore")
    public Map<String,Object> findCurrentBusinessScore() throws Exception {
        Map<String,Object>res=new HashMap<>();
        res=bankService.searchAllBussinessScore();
        return res;
    }
    //处理申请和承兑积分
    @RequestMapping("shenQingOrChengDuiJiFenHandler")
    public String shenQingJiFen(@RequestParam int businessId,@RequestParam int score,@RequestParam int idbankBusinessId) throws Exception {
        //调用合约 参数为businessId score
        bankService.shenQingOrChengDuiJiFenHandler(businessId,score);
        BankBusiness bankBusiness=new BankBusiness();
        bankBusiness.setIdbankBusinessId(idbankBusinessId);
        bankBusiness.setAccept(1);
        bankService.updateRecord(bankBusiness);
        Business business = businessService.getBusinessByBusinessId(businessId);
        business.setSurplus(business.getSurplus()-score);
        businessService.update(business);
        return "处理成功";
    }
    @RequestMapping("findAllRecord")
    public Map<String,Object> findAllRecord(){
        Map<String,Object> res=new HashMap<>();
        res.put("response",bankService.findAllRecord());
        return res;
    }

}
