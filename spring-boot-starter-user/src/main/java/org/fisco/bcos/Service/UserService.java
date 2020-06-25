package org.fisco.bcos.Service;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.contract.Asset;
import org.fisco.bcos.dao.UserInformationMapper;
import org.fisco.bcos.model.UserInformation;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class userService {
    @Autowired
    UserInformationMapper userInformationMapper;


    public UserInformation getUserInformationById(Integer userId){

      return   userInformationMapper.selectByPrimaryKey(userId);
    }
    public Integer insertUserInformation(UserInformation userInformation) throws Exception {

            return userInformationMapper.insert(userInformation);

    }
    public Integer updateUserInformation(UserInformation userInformation){
        return userInformationMapper.updateByPrimaryKey(userInformation);
    }
}
