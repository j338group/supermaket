package cn.smbms.vo;

import java.util.Date;

public class UserVo {
    private long id;
    private String userCode;
    private String userName;
    private Integer gender;
    private Date birthday;
    private Integer age;
    private String phone;
    private String userRoleName;
    private String address;
    private Integer userRole;

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", userRoleName='" + userRoleName + '\'' +
                '}';
    }

    public Integer getAge() {
        int nowYear=new Date().getYear();
        int year=this.birthday.getYear();
        return nowYear-year;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
