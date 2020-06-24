package org.fisco.bcos.autoconfigure;

import org.fisco.bcos.Contract.Asset;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractConfig {
    @Autowired private Web3j web3j;
    @Autowired private Credentials credentials;

    @Bean
   public Asset getAsset(){
        Asset asset = Asset.load("0x0dbffbd2546c31f5dcf05bce9a69e58a2600dea9", web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        return asset;
    }
}
