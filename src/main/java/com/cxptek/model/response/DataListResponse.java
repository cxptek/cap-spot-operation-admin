package com.cxptek.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

import static com.cxptek.utils.AppUtils.getCurrentPageResponseFE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataListResponse<T> {
    private long page; // 1
    private int perPage; // number items in 1 page
    private int totalPages;
    private long totalItems;
    private List<T> items;

    public static <T> DataListResponse<T> of(Page<T> page) {
        var dataResponse = new DataListResponse<T>();
        dataResponse.setPage(getCurrentPageResponseFE(page.getPageable().getPageNumber()));
        dataResponse.setPerPage(page.getPageable().getPageSize());
        dataResponse.setTotalItems(page.getTotalElements());
        dataResponse.setTotalPages(page.getTotalPages());
        dataResponse.setItems(page.getContent());
        return dataResponse;
    }

    public static <T> DataListResponse<T> ofEmptyList() {
        var dataResponse = new DataListResponse<T>();
        dataResponse.setPage(getCurrentPageResponseFE(0));
        dataResponse.setPerPage(20);
        dataResponse.setTotalItems(0);
        dataResponse.setTotalPages(0);
        dataResponse.setItems(null);
        return dataResponse;
    }
}
