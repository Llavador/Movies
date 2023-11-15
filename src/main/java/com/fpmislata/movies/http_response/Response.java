package com.fpmislata.movies.http_response;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
 
    private Object data;
 
    private Map<String, Object> pagination;
 
    private Map<String, Object> metadata;
 
    public Response(Object data) {
        this.data = data;
    }
 
    public Response(Object data, int totalRecords) {
        this.data = data;
        this.metadata = new HashMap<>();
        this.metadata.put("total_records", totalRecords);
    }
 
    public Response(Object data, int totalRecords, int page, int pageSize) {
        this.data = data;
        this.metadata = new HashMap<>();
        this.metadata.put("total_records", totalRecords);
        this.paginate(page, pageSize, totalRecords);
    }
 
    public void paginate(int page, int pageSize, int totalRecords) {
        this.pagination = new HashMap<>();
        this.pagination.put("page", page);
        this.pagination.put("page size", pageSize);
        int totalPages = (int) (Math.ceil((double) totalRecords / pageSize));
        this.pagination.put("total pages", totalPages);
        if(page > 1 && totalPages > 1)
            this.pagination.put("previous", "/movies?page=" + (page - 1));
        if(page < totalPages)
            this.pagination.put("next", "/movies?page=" + (page + 1));
    }
 
}
