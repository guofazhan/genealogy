package com.genealogy.web.vo;

/**
 * 用户分页查询参数
 * @author G2Y
 * @version [版本号, 2018/7/29 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserReqVo extends PageReqVo{



    /**
     * 用户ID 自增
     */
    private int userId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 显示名
     */
    private String showName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Override
    public String toString() {
        return "UserReqVo{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", showName='" + showName + '\'' +
                "} " + super.toString();
    }
}
