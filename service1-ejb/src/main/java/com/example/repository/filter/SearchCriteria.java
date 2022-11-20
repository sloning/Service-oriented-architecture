package com.example.repository.filter;

import lombok.Data;

import java.util.List;

@Data
public class SearchCriteria {

    private final List<String> key;
    private final SearchOperation searchOperation;
    private final Object value;

    public SearchCriteria(String key, SearchOperation searchOperation, Object value) {
        this.key = List.of(key);
        this.searchOperation = searchOperation;
        this.value = value;
    }

    public SearchCriteria(List<String> key, SearchOperation searchOperation, Object value) {
        this.key = key;
        this.searchOperation = searchOperation;
        this.value = value;
    }
}
