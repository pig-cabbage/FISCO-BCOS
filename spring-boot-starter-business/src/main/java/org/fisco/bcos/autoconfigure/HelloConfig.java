package org.fisco.bcos.autoconfigure;


import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.Contract.HelloWorld;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertTrue;

@Configuration
public class HelloConfig {

    @Bean
    public HelloWorld getHelloWorld(Web3j web3j, Credentials credentials) throws Exception{
        // deploy contract
        HelloWorld helloWorld =
                HelloWorld.deploy(
                        web3j,
                        credentials,
                        new StaticGasProvider(
                                GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT))
                        .send();

        return helloWorld;

    }


}
