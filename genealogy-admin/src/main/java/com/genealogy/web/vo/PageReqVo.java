package com.genealogy.web.vo;

/**
 * 分页请求参数
 * @author G2Y
 * @version [版本号, 2018/7/29 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PageReqVo extends VO{


    /**
     * 开始位置
     */
    private int offset;

    /**
     * 每页条数
     */
    private int limit;


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageReqVo{" +
                "offset=" + offset +
                ", limit=" + limit +
                "} " + super.toString();
    }
}
