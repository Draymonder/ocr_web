/*
 * 文件名：Pagenation.java 版权： 描述： 修改人：luoyb 修改时间：2017-4-14 跟踪单号： 修改单号： 修改内容：
 */
package com.eqy.web.pojo;
import java.util.List;

/**
 * @ClassName: Pagenation
 * @Description: 分页公共使用类
 * @author luoyb
 * @date 2017-8-2 下午5:08:20
 * 
 */
public class Pagenation
{
    /* 指定的参数 */
    private int pageNum; // 当前页号
    private int size; // 页面大小：每页显示多少条数据
    /* DB中查找的数据 */
    private long rowCount; // 数据总条数：共有多少条数据
    private List<?> list; // 数据内容
    /* 由以上属性计算得到的属性 */
    private int pageCount; // 页面总数
    private int startRow; // 当前页面开始行
    private int endRow; // 当前页面开始行
    private int first = 1; // 第一页 页号
    private int last; // 最后一页 页号
    private int prev; // 前一页 页号
    private int next; // 后一页 页号
    /**
     * 构造器：初始化基本的三个参数(pageNum, size, rowCount)，其他参数由计算得到
     * 
     * @param pageNum
     * @param size
     * @param rowCount
     */
    public Pagenation(int pageNum, int size, long rowCount)
    {
        // 初始化基本参数
        this.pageNum = pageNum;
        this.size = size;
        this.rowCount = rowCount;
        // 计算得到其他参数
        this.pageCount = (int) Math.ceil(this.rowCount / (double) size);
        this.last = pageCount;
        // 一般情况下pageNum会等于传入的pageNum,但当传入的pageNum如果大于页面总数，则pageNum就等于最大页面数，即最后一页的页数
        this.pageNum = Math.min(pageNum, pageCount);
        // 一般情况下pageNum会等于传入的pageNum,但当传入的pageNum如果小于1，则pageNum就等于1，即第一页的页数
        this.pageNum = Math.max(1, this.pageNum);
        this.startRow = (pageNum - 1)*size;
        this.endRow = pageNum * size;
        // 如果<前一页>为第一页，则显示1,否则是{本页页数-1}
        this.prev = (this.pageNum - 1 > 1) ? (this.pageNum - 1) : 1;
        // 如果<后一页>为最后一页，则返回{最后一页页数}，否则返回{本页数+1}
        this.next = (this.pageNum + 1 < this.pageCount) ? (this.pageNum + 1) : this.pageCount;
    }
    public int getPageNum()
    {
        return pageNum;
    }
    public void setPageNum(int pageNum)
    {
        this.pageNum = pageNum;
    }
    public int getSize()
    {
        return size;
    }
    public void setSize(int size)
    {
        this.size = size;
    }
    public long getRowCount()
    {
        return rowCount;
    }
    public void setRowCount(long rowCount)
    {
        this.rowCount = rowCount;
    }
    public List<?> getList()
    {
        return list;
    }
    public void setList(List<?> list)
    {
        this.list = list;
    }
    public int getPageCount()
    {
        return pageCount;
    }
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }
    public int getStartRow()
    {
        return startRow;
    }
    public void setStartRow(int startRow)
    {
        this.startRow = startRow;
    }
    public int getEndRow()
    {
        return endRow;
    }
    public void setEndRow(int endRow)
    {
        this.endRow = endRow;
    }
    public int getFirst()
    {
        return first;
    }
    public void setFirst(int first)
    {
        this.first = first;
    }
    public int getLast()
    {
        return last;
    }
    public void setLast(int last)
    {
        this.last = last;
    }
    public int getPrev()
    {
        return prev;
    }
    public void setPrev(int prev)
    {
        this.prev = prev;
    }
    public int getNext()
    {
        return next;
    }
    public void setNext(int next)
    {
        this.next = next;
    }
}