package com.example.telegrambot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class Pagination {
    int page;
    int size;
    String sort;
    boolean hasNext;
    boolean hasPrevious;

    public Pagination(int page, int size, String sort){
        this.page = page;
        this.size = size;
        this.sort = sort;
    }
    public String toQueryParams(){
        return String.format("page=%s&size=%s&sort=%s", page, size, sort);
    }

    public String getQueryParamsForNextPage(){
        return String.format("page=%s&size=%s&sort=%s", page+1, size, sort);
    }

    public String getQueryParamsForPreviousPage(){
        if (page <= 0){
            return String.format("page=%s&size=%s&sort=%s", 0, size, sort);
        }
        return String.format("page=%s&size=%s&sort=%s", page-1, size, sort);
    }
}
