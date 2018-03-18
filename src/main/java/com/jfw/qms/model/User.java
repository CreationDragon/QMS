package com.jfw.qms.model;

public class User {
    private String userName;
    private String userPsw;
    private String surepassword;
    private String file;
    private String province;
    private String city;
    private String district;
    private String userGener;
    private String userPhone;
    private String userEmail;
    private String userAddressDetail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public String getSurepassword() {
        return surepassword;
    }

    public void setSurepassword(String surepassword) {
        this.surepassword = surepassword;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUserGener() {
        return userGener;
    }

    public void setUserGener(String userGener) {
        this.userGener = userGener;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddressDetail() {
        return userAddressDetail;
    }

    public void setUserAddressDetail(String userAddressDetail) {
        this.userAddressDetail = userAddressDetail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPsw='" + userPsw + '\'' +
                ", surepassword='" + surepassword + '\'' +
                ", file='" + file + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", userGener='" + userGener + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAddressDetail='" + userAddressDetail + '\'' +
                '}';
    }
}
