package com.sun.bean;


import java.io.Serializable;
import java.util.List;

/**
 * 分页模型，泛型为存储页面的单个信息
 * @author sun
 */
public class Page<T> implements Serializable {

    /**
     * 当前是第几页 通过前台传入
     */
    private Integer pageNo;

    /**
     * 总页数 通过数据计算得到，不能手动设置
     */
    private Integer totalPage;

    /**
     * 总记录数 通过数据库查询得到
     */
    private Integer totalCount;

    /**
     * 每页显示的条数，告诉数据库一次查四条记录
     */
    private Integer pageSize = 4;

    /**
     * 索引，从哪个索引开始查，通过计算得到
     */
    private Integer index;

    /**
     * 是否有下一页 判断得到
     */
    private boolean hasNext;

    /**
     * 是否有上一页 判断得到
     */
    private boolean hasPre;

    /**
     * 封装查询出来的分页数据 查询设置记录
     */
    private List<T> pageData;


    public Page() {
    }

    public Page(Integer pageNo, Integer totalPage, Integer totalCount, Integer pageSize, Integer index, boolean hasNext, boolean hasPre, List<T> pageDate) {
        this.pageNo = pageNo;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.index = index;
        this.hasNext = hasNext;
        this.hasPre = hasPre;
        this.pageData = pageDate;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * 设定当前页数，页数不能为小于1，不能大于totalCount
     * @param pageNo 页数最大不超过最大值
     */
    public void setPageNo(Integer pageNo) {
        //页码最小为1
        pageNo=pageNo>0?pageNo:1;
        //页码最大不超过最大值
        pageNo=pageNo<getTotalPage()?pageNo:getTotalPage();
        this.pageNo = pageNo;
    }

    /**
     * 获取总页数
     * @return int 总页数
     */
    public Integer getTotalPage() {
        //计算当前实际的totalpage
        int t=getTotalCount()/getPageSize();
        if(getTotalCount()%getPageSize()!=0){
            t=t+1;
        }
        return t;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 计算得出索引值
     * @return int 索引值
     */
    public Integer getIndex() {
        int i=(getPageNo()-1)*getPageSize();
        //totalpage 可能为0 ，因此设置的pageNo也可能为0，优越index不能为负数，因此加if判断
        if(i<0){
            i=0;
        }
        return i;
    }

    /**
     * 判断是否有下一页
     * @return boolean
     */
    public boolean isHasNext() {

        return getPageNo()<getTotalPage();
    }


    /**
     * 判断是否有上一页
     * @return boolean
     */
    public boolean isHasPre() {
        return getPageNo()>1;
    }



    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", index=" + index +
                ", hasNext=" + hasNext +
                ", haspPre=" + hasPre +
                ", pageDate=" + pageData +
                '}';
    }
}
