package com.kaba.transform.entity.generation;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerContracts {
    private String id;

    private String customerid;

    private String memberid;

    private String contractno;

    private String childid;

    private String childname;

    private String coursename;

    private Integer category;

    private Integer totalhours;

    private BigDecimal totalenrollmentfree;

    private BigDecimal deposit;

    private BigDecimal remainingbalance;

    private Date fromdate;

    private Date todate;

    private Short type;

    private Date signdate;

    private String remark;

    private Boolean isdeleted;

    private Date createdate;

    private String createby;

    private Date updatedate;

    private String updateby;

    private Integer periodtype;

    private Integer classtype;

    private Integer contractstatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid == null ? null : memberid.trim();
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno == null ? null : contractno.trim();
    }

    public String getChildid() {
        return childid;
    }

    public void setChildid(String childid) {
        this.childid = childid == null ? null : childid.trim();
    }

    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname == null ? null : childname.trim();
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getTotalhours() {
        return totalhours;
    }

    public void setTotalhours(Integer totalhours) {
        this.totalhours = totalhours;
    }

    public BigDecimal getTotalenrollmentfree() {
        return totalenrollmentfree;
    }

    public void setTotalenrollmentfree(BigDecimal totalenrollmentfree) {
        this.totalenrollmentfree = totalenrollmentfree;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getRemainingbalance() {
        return remainingbalance;
    }

    public void setRemainingbalance(BigDecimal remainingbalance) {
        this.remainingbalance = remainingbalance;
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getPeriodtype() {
        return periodtype;
    }

    public void setPeriodtype(Integer periodtype) {
        this.periodtype = periodtype;
    }

    public Integer getClasstype() {
        return classtype;
    }

    public void setClasstype(Integer classtype) {
        this.classtype = classtype;
    }

    public Integer getContractstatus() {
        return contractstatus;
    }

    public void setContractstatus(Integer contractstatus) {
        this.contractstatus = contractstatus;
    }
}