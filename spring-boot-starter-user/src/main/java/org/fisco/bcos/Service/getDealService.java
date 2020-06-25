package org.fisco.bcos.Service;


import jnr.ffi.annotations.In;
import org.fisco.bcos.dao.DealMapper;
import org.fisco.bcos.model.Deal;
import org.fisco.bcos.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class getDealService {

    @Autowired
    private DealMapper dealMapper;



    public List<Deal> getDeal(Integer userId){

        List<Deal> result=dealMapper.selectByUserId(userId);
        Collections.sort(result, new Comparator<Deal>() {
            @Override
            public int compare(Deal o1, Deal o2) {
                return o2.getDealTime().compareTo(o1.getDealTime());
            }
        });
        return result;
    }
}
