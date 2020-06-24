package org.fisco.bcos.Controller;


import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.apache.commons.io.FileUtils;
import org.fisco.bcos.Service.AddGoodsService;
import org.fisco.bcos.Service.uploadService;
import org.fisco.bcos.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class addGoodsController {

    @Autowired
    private AddGoodsService addGoodsService;

    @Autowired
    private uploadService uploadService;

    @RequestMapping(value = "/addGoods",method = RequestMethod.POST)
    public Map<String,Object> addGoods(Goods goods) throws IOException {
        Map<String,Object> res=new HashMap<>();

        Boolean temp=addGoodsService.addGoods(goods.getBusinessId(),goods.getGoodsUrl(),goods.getGoodsName(),goods.getPriceMoney(),goods.getPriceScore(),goods.getNumber());

        if(temp==true)
            res.put("response","success");
        else
            res.put("response","fail");
        return res;



    }
    @RequestMapping(value = "/addGoods/pic",method = RequestMethod.POST)
    public Map<String,Object> addGoods(MultipartFile file) throws IOException {
        Map<String, Object> res = new HashMap<>();
        File newFile = new File("./test.jpg");
        FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
        Response response = uploadService.uploadFile(newFile);

        res.put("response","qc7u8b1qd.bkt.clouddn.com/"+response.jsonToMap().get("hash"));
        return res;


    }


}