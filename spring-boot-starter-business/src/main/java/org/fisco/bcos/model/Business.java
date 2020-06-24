package org.fisco.bcos.model;

public class Business {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.business_id
     *
     * @mbg.generated
     */
    private Integer businessId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.name
     *
     * @mbg.generated
     */
    private String name;
    private String passWord;



    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business.surplus
     *
     * @mbg.generated
     */
    private Integer surplus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.business_id
     *
     * @return the value of business.business_id
     *
     * @mbg.generated
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.business_id
     *
     * @param businessId the value for business.business_id
     *
     * @mbg.generated
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.name
     *
     * @return the value of business.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.name
     *
     * @param name the value for business.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business.surplus
     *
     * @return the value of business.surplus
     *
     * @mbg.generated
     */
    public Integer getSurplus() {
        return surplus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business.surplus
     *
     * @param surplus the value for business.surplus
     *
     * @mbg.generated
     */
    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    @Override
    public String toString() {
        return "Business{" +
                "businessId=" + businessId +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", surplus=" + surplus +
                '}';
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}