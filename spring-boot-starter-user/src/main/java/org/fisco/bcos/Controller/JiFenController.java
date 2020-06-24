package org.fisco.bcos.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JiFenController {

    @RequestMapping("zhuanZengJiFen")
    public String zhuanZengJiFen(){
        return "转赠成功";
    }

}
