package com.stayc.common.util;

import java.util.ArrayList;
import java.util.List;

public class UtilPagingResponse {

    private List list = new ArrayList<>();
    private UtilPaging pagination;
    
    public UtilPagingResponse(List list, UtilPaging pagination) {
        this.list.addAll(list);
        this.pagination = pagination;
    }

}