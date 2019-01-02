package com.fc.base.entity.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagingData<T> implements Serializable {

    private static final long serialVersionUID = -6686143183860816602L;

    private List<T> rows = new ArrayList<T>();
    private Integer total = 0;

    public PagingData(List<T> rows, Integer total) {
        super();
        this.rows = rows;
        this.total = total;
    }

    public PagingData() {
        super();
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
