package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Person {
    private Long id;

    private Integer userId;

    private String userName;

    private String address;

    private String phone;

    private String idCard;

    private LocalDateTime createTime;

    private Date updateTime;

    private Integer status;

    public Person(Long id, Integer userId, String userName, String address, String phone, String idCard, LocalDateTime createTime, Date updateTime, Integer status) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.idCard = idCard;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
    }

    public Person() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}