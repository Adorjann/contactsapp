package com.adorjanpraksa.contactsapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {

    private T content;
    private int pageNumber;
    private int pageSize;
    private Long totalElements;


}
