package org.fisco.bcos.model;

import java.math.BigInteger;

public class BusinessScore {
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }



    String businessId;

    public BigInteger getScore() {
        return score;
    }

    public void setScore(BigInteger score) {
        this.score = score;
    }

    BigInteger score;
}
