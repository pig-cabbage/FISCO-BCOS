package org.fisco.bcos.Service;

import org.fisco.bcos.dao.UserInformationMapper;
import org.fisco.bcos.model.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserInformationMapper userInformationMapper;

    public UserInformation getUserInformationById(Integer userId){
      return   userInformationMapper.selectByPrimaryKey(userId);
    }
    public Integer insertUserInformation(UserInformation userInformation){
        return userInformationMapper.insert(userInformation);
    }
    public Integer updateUserInformation(UserInformation userInformation){
        return userInformationMapper.updateByPrimaryKey(userInformation);
    }
}
