package org.fisco.bcos.model;

import java.util.Date;

public class UserUserScore {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_user_score.send_id
     *
     * @mbg.generated
     */
    private Integer sendId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_user_score.get_id
     *
     * @mbg.generated
     */
    private Integer getId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_user_score.score
     *
     * @mbg.generated
     */
    private Integer score;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_user_score.time
     *
     * @mbg.generated
     */
    private Date time;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_user_score.send_id
     *
     * @return the value of user_user_score.send_id
     *
     * @mbg.generated
     */
    public Integer getSendId() {
        return sendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_user_score.send_id
     *
     * @param sendId the value for user_user_score.send_id
     *
     * @mbg.generated
     */
    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_user_score.get_id
     *
     * @return the value of user_user_score.get_id
     *
     * @mbg.generated
     */
    public Integer getGetId() {
        return getId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_user_score.get_id
     *
     * @param getId the value for user_user_score.get_id
     *
     * @mbg.generated
     */
    public void setGetId(Integer getId) {
        this.getId = getId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_user_score.score
     *
     * @return the value of user_user_score.score
     *
     * @mbg.generated
     */
    public Integer getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_user_score.score
     *
     * @param score the value for user_user_score.score
     *
     * @mbg.generated
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_user_score.time
     *
     * @return the value of user_user_score.time
     *
     * @mbg.generated
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_user_score.time
     *
     * @param time the value for user_user_score.time
     *
     * @mbg.generated
     */
    public void setTime(Date time) {
        this.time = time;
    }
}