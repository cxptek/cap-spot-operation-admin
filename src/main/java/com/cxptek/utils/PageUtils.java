package com.cxptek.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static com.cxptek.constant.Constant.PAGE_DEFAULT;

public class PageUtils {

    public static PageRequest getPageRequest(int page, int size, String sortBy, Sort.Direction sortDirection) {
        return PageRequest.of(page - Integer.parseInt(PAGE_DEFAULT), size, Sort.by(sortDirection, sortBy));
    }


    public static PageRequest getPageRequestMultipleSort(int page, int size, Sort sortBy) {
        return PageRequest.of(page - Integer.parseInt(PAGE_DEFAULT), size, sortBy);
    }
}
