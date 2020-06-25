package org.fisco.bcos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class buyGoodsServiceTest {

    @Autowired
    private BuyGoodsService buyGoodsService;

    @Test
    public void testBuyGoods() {
            Integer result=buyGoodsService.buyGoods(1,2,20,0);
            System.out.println(result);


    }
}