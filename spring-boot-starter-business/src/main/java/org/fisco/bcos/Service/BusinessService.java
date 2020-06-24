package org.fisco.bcos.Service;

import org.fisco.bcos.dao.BusinessMapper;
import org.fisco.bcos.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
    @Autowired
    BusinessMapper businessMapper;

    public Business getBusinessByBusinessId(int businessId){
        Business business = businessMapper.selectByPrimaryKey(businessId);
        return business;
    }
    public void insert(Business business){
        businessMapper.insert(business);
    }
    public void update(Business business){
        businessMapper.updateByPrimaryKey(business);
    }
    public Business selectByIdAndPassword(Business business){
        return businessMapper.selectByIdAndPassword(business);
    }

}
