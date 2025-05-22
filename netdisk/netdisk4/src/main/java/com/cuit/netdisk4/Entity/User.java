package com.cuit.netdisk4.Entity;

import java.sql.Timestamp;

public class User {

    //用户id
    private int userId;
    //用户名
    private String username;
    //密码
    private String passwordHash;
    //邮箱
    private String email;
    //是否是管理员
    private boolean isAdmin;
    //是否是VIP
    private boolean isVip;
    //VIP到期时间
    private Timestamp vipExpiry;
    //是否被冻结
    private boolean freezeStatus;
    //失败登录次数
    private int failedLoginCount;
    //创建时间
    private Timestamp createTime;
    //更新时间
    private Timestamp updateTime;

    public User() {
    }

    // getter和setter方法
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public Timestamp getVipExpiry() {
        return vipExpiry;
    }

    public void setVipExpiry(Timestamp vipExpiry) {
        this.vipExpiry = vipExpiry;
    }

    public boolean isFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(boolean freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public int getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(int failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}