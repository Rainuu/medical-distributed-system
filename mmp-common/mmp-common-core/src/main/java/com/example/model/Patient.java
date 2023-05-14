package com.example.model;

public class Patient {
    private Long id;

    private Long createdTime;

    private Long creator;

    private Boolean isRemoved;

    private String address;

    private String addressPostCode;

    private String addressStreet;

    private Integer age;

    private Long birthday;

    private String checkNo;

    private Integer checkTimes;

    private String community;

    private String contactName;

    private String contactPhone;

    private String costsPerMonth;

    private String domicile;

    private String domicilePostcode;

    private String domicileStreet;

    private String educationDegree;

    private String email;

    private String gender;

    private String height;

    private String history;

    private String identity;

    private String ifMarried;

    private String institutionName;

    private String manageLevel;

    private String name;

    private String national;

    private String payment;

    private String phone;

    private String photo;

    private String professional;

    private String qc;

    private String qcTel;

    private String relationship;

    private String screeningState;

    private String screeninger;

    private String screeningerTel;

    private String telephone;

    private Long updateTime;

    private String weight;

    private Long instId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAddressPostCode() {
        return addressPostCode;
    }

    public void setAddressPostCode(String addressPostCode) {
        this.addressPostCode = addressPostCode == null ? null : addressPostCode.trim();
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet == null ? null : addressStreet.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo == null ? null : checkNo.trim();
    }

    public Integer getCheckTimes() {
        return checkTimes;
    }

    public void setCheckTimes(Integer checkTimes) {
        this.checkTimes = checkTimes;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community == null ? null : community.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getCostsPerMonth() {
        return costsPerMonth;
    }

    public void setCostsPerMonth(String costsPerMonth) {
        this.costsPerMonth = costsPerMonth == null ? null : costsPerMonth.trim();
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile == null ? null : domicile.trim();
    }

    public String getDomicilePostcode() {
        return domicilePostcode;
    }

    public void setDomicilePostcode(String domicilePostcode) {
        this.domicilePostcode = domicilePostcode == null ? null : domicilePostcode.trim();
    }

    public String getDomicileStreet() {
        return domicileStreet;
    }

    public void setDomicileStreet(String domicileStreet) {
        this.domicileStreet = domicileStreet == null ? null : domicileStreet.trim();
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree == null ? null : educationDegree.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history == null ? null : history.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getIfMarried() {
        return ifMarried;
    }

    public void setIfMarried(String ifMarried) {
        this.ifMarried = ifMarried == null ? null : ifMarried.trim();
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName == null ? null : institutionName.trim();
    }

    public String getManageLevel() {
        return manageLevel;
    }

    public void setManageLevel(String manageLevel) {
        this.manageLevel = manageLevel == null ? null : manageLevel.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national == null ? null : national.trim();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional == null ? null : professional.trim();
    }

    public String getQc() {
        return qc;
    }

    public void setQc(String qc) {
        this.qc = qc == null ? null : qc.trim();
    }

    public String getQcTel() {
        return qcTel;
    }

    public void setQcTel(String qcTel) {
        this.qcTel = qcTel == null ? null : qcTel.trim();
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }

    public String getScreeningState() {
        return screeningState;
    }

    public void setScreeningState(String screeningState) {
        this.screeningState = screeningState == null ? null : screeningState.trim();
    }

    public String getScreeninger() {
        return screeninger;
    }

    public void setScreeninger(String screeninger) {
        this.screeninger = screeninger == null ? null : screeninger.trim();
    }

    public String getScreeningerTel() {
        return screeningerTel;
    }

    public void setScreeningerTel(String screeningerTel) {
        this.screeningerTel = screeningerTel == null ? null : screeningerTel.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }
}