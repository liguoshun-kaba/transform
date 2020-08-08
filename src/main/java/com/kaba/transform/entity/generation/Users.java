package com.kaba.transform.entity.generation;

import java.util.Date;

public class Users {
    private String id;

    private String code;

    private String name;

    private String phonenumber;

    private String email;

    private String password;

    private String question;

    private String answer;

    private String centerid;

    private Integer post;

    private Boolean havealldata;

    private Boolean iseffective;

    private Boolean isdeleted;

    private Date createdate;

    private String createby;

    private Date updatedate;

    private String updateby;

    private Integer onlinetime;

    private Integer ischangepwd;

    private String openid;

    private Boolean phonepermission;

    private Boolean islineteacher;

    private Long classinuid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getCenterid() {
        return centerid;
    }

    public void setCenterid(String centerid) {
        this.centerid = centerid == null ? null : centerid.trim();
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Boolean getHavealldata() {
        return havealldata;
    }

    public void setHavealldata(Boolean havealldata) {
        this.havealldata = havealldata;
    }

    public Boolean getIseffective() {
        return iseffective;
    }

    public void setIseffective(Boolean iseffective) {
        this.iseffective = iseffective;
    }

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Integer getOnlinetime() {
        return onlinetime;
    }

    public void setOnlinetime(Integer onlinetime) {
        this.onlinetime = onlinetime;
    }

    public Integer getIschangepwd() {
        return ischangepwd;
    }

    public void setIschangepwd(Integer ischangepwd) {
        this.ischangepwd = ischangepwd;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Boolean getPhonepermission() {
        return phonepermission;
    }

    public void setPhonepermission(Boolean phonepermission) {
        this.phonepermission = phonepermission;
    }

    public Boolean getIslineteacher() {
        return islineteacher;
    }

    public void setIslineteacher(Boolean islineteacher) {
        this.islineteacher = islineteacher;
    }

    public Long getClassinuid() {
        return classinuid;
    }

    public void setClassinuid(Long classinuid) {
        this.classinuid = classinuid;
    }
}