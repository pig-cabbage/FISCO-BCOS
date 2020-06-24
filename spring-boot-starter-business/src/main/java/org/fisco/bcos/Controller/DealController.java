package org.fisco.bcos.Controller;

import org.fisco.bcos.Service.DealService;
import org.fisco.bcos.model.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//获取所有买卖交易记录
@RestController
public class DealController {
    @Autowired
    DealService dealService;

    @RequestMapping("findAllDealsByBusinessId")
    public Map<String,Object> findAllDealsByBusinessId(@RequestParam int businessId) {
        List<Deal> allDeals = dealService.findByBusinessId(businessId);
        Map<String, Object> res = new HashMap<>();
        res.put("response", allDeals);
        return res;
    }
}
