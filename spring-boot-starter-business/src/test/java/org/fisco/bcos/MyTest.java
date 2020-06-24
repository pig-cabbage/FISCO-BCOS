package org.fisco.bcos;

import org.fisco.bcos.dao.BankBusinessMapper;
import org.fisco.bcos.model.BankBusiness;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class MyTest {
    @Autowired
    BankBusinessMapper bankBusinessMapper;
    @Test
    public void insert(){
        BankBusiness bankBusiness=new BankBusiness();
        bankBusiness.setBusinessId(444);
        bankBusiness.setScore(45);
        bankBusiness.setAccept(0);
        bankBusinessMapper.insert(bankBusiness);
    }
}
