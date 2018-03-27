package com.jfw.qms.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    private int userId;
    private String userName;
    private String userPsw;
    private String userGener;
    private String userPhone;
    private String userEmail;
    private Integer userAuthority;
    private String userAddressDetail;
    private String userHead;
    private Integer userProvince;
    private Integer userCity;
    private Integer userDistrict;
    private Integer userState;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_psw", nullable = true, length = 200)
    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    @Basic
    @Column(name = "user_gener", nullable = true, length = 10)
    public String getUserGener() {
        return userGener;
    }

    public void setUserGener(String userGener) {
        this.userGener = userGener;
    }

    @Basic
    @Column(name = "user_phone", nullable = true, length = 50)
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "user_email", nullable = true, length = 100)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "user_authority", nullable = true)
    public Integer getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(Integer userAuthority) {
        this.userAuthority = userAuthority;
    }

    @Basic
    @Column(name = "user_address_detail", nullable = true, length = 200)
    public String getUserAddressDetail() {
        return userAddressDetail;
    }

    public void setUserAddressDetail(String userAddressDetail) {
        this.userAddressDetail = userAddressDetail;
    }

    @Basic
    @Column(name = "user_head", nullable = true, length = 200)
    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    @Basic
    @Column(name = "user_province", nullable = true)
    public Integer getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(Integer userProvince) {
        this.userProvince = userProvince;
    }

    @Basic
    @Column(name = "user_city", nullable = true)
    public Integer getUserCity() {
        return userCity;
    }

    public void setUserCity(Integer userCity) {
        this.userCity = userCity;
    }

    @Basic
    @Column(name = "user_district", nullable = true)
    public Integer getUserDistrict() {
        return userDistrict;
    }

    public void setUserDistrict(Integer userDistrict) {
        this.userDistrict = userDistrict;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (userPsw != null ? !userPsw.equals(user.userPsw) : user.userPsw != null) return false;
        if (userGener != null ? !userGener.equals(user.userGener) : user.userGener != null) return false;
        if (userPhone != null ? !userPhone.equals(user.userPhone) : user.userPhone != null) return false;
        if (userEmail != null ? !userEmail.equals(user.userEmail) : user.userEmail != null) return false;
        if (userAuthority != null ? !userAuthority.equals(user.userAuthority) : user.userAuthority != null)
            return false;
        if (userAddressDetail != null ? !userAddressDetail.equals(user.userAddressDetail) : user.userAddressDetail != null)
            return false;
        if (userHead != null ? !userHead.equals(user.userHead) : user.userHead != null) return false;
        if (userProvince != null ? !userProvince.equals(user.userProvince) : user.userProvince != null) return false;
        if (userCity != null ? !userCity.equals(user.userCity) : user.userCity != null) return false;
        if (userDistrict != null ? !userDistrict.equals(user.userDistrict) : user.userDistrict != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPsw != null ? userPsw.hashCode() : 0);
        result = 31 * result + (userGener != null ? userGener.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (userAuthority != null ? userAuthority.hashCode() : 0);
        result = 31 * result + (userAddressDetail != null ? userAddressDetail.hashCode() : 0);
        result = 31 * result + (userHead != null ? userHead.hashCode() : 0);
        result = 31 * result + (userProvince != null ? userProvince.hashCode() : 0);
        result = 31 * result + (userCity != null ? userCity.hashCode() : 0);
        result = 31 * result + (userDistrict != null ? userDistrict.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "user_state", nullable = true)
    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }
}
