package com.example.mgtserver.dto.util;

/**
 * @author Hexrt
 * @description
 * @create 2022-04-01 16:20
 */
public class PagedQueryDTO<T> {
    /**
     * 页面编号
     */
    private Integer pageNum;
    /**
     * 页面大小
     */
    private Integer pageSize;
    /**
     * 查询dto
     */
    private T query;

    public PagedQueryDTO() {}

    public PagedQueryDTO(int pageNum, int pageSize, T query) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.query = query;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "PagedQueryDTO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", query=" + query +
                '}';
    }
}
