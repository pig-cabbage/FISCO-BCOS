package org.fisco.bcos.Service;


import org.fisco.bcos.dao.UserInformationMapper;
import org.fisco.bcos.model.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class addMoneyService {
    @Autowired
    private UserInformationMapper userInformationMapper;



    public Boolean addMoney(Integer userId,Integer money){
        UserInformation user=userInformationMapper.selectByPrimaryKey(userId);
        user.setSurplus(user.getSurplus()+money);
        if(userInformationMapper.updateByPrimaryKey(user)>0)
            return true;
        else
            return false;

    }
}
