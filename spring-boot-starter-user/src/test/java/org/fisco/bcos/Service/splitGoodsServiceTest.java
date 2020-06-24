package org.fisco.bcos.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class splitGoodsServiceTest {

    @Autowired
    private splitGoodsService splitGoodsService;

    @Test
    public void testSplitByPrice() {
        System.out.println(splitGoodsService.splitByPrice(10,80));
    }
}