package com.kabba.transform.entity.generation;

import java.util.Date;

public class Centers {
    private String id;

    private String code;

    private String name;

    private String address;

    private String region;

    private String phonenumber;

    private String faxnumber;

    private String email;

    private String company;

    private String master;

    private String description;

    private Date startdate;

    private Date enddate;

    private Boolean isdeleted;

    private Date createdate;

    private String createby;

    private Date updatedate;

    private String updateby;

    private String districtid;

    private Integer brandtype;

    private Integer managementmodel;

    private Boolean isshow;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getFaxnumber() {
        return faxnumber;
    }

    public void setFaxnumber(String faxnumber) {
        this.faxnumber = faxnumber == null ? null : faxnumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master == null ? null : master.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid == null ? null : districtid.trim();
    }

    public Integer getBrandtype() {
        return brandtype;
    }

    public void setBrandtype(Integer brandtype) {
        this.brandtype = brandtype;
    }

    public Integer getManagementmodel() {
        return managementmodel;
    }

    public void setManagementmodel(Integer managementmodel) {
        this.managementmodel = managementmodel;
    }

    public Boolean getIsshow() {
        return isshow;
    }

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }
}