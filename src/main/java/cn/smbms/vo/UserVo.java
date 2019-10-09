package cn.smbms.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * description:
 * Created by Ray on 2019-09-24
 */
public class UserVo {
    private Long id;
    private String userCode;
    private String userName;
    private Integer gender;
    private Date birthday;
    private Integer age;
    private String phone;
    private String userRoleName;
    private String address;
    private Long userRole;

    public Long getUserRole() {
        return userRole;
    }

    public void setUserRole(Long userRole) {
        this.userRole = userRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        int nowYear = new Date().getYear();
        int year = this.getBirthday().getYear();
        int age=nowYear-year;
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
