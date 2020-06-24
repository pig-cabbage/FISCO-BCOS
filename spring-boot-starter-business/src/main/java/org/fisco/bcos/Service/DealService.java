package org.fisco.bcos.Service;

import org.fisco.bcos.dao.DealMapper;
import org.fisco.bcos.model.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealService {
    @Autowired
    DealMapper dealMapper;
   public List<Deal> findAllDeals(){
        return dealMapper.findAll();
    }
    public List<Deal> findByBusinessId(Integer businessId){
       return dealMapper.findByBusinessId(businessId);
    }
}
