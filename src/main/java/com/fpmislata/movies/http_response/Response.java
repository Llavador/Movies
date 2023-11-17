package com.fpmislata.movies.http_response;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "totalRecords", "pagination", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL) // No incluirá atributos nulos en el JSON
@Builder
public class Response {
 
    private Object data;
 
    private Map<String, Object> pagination;
 
    private Integer totalRecords;
    
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
