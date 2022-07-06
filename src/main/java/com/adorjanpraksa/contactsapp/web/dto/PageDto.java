package com.adorjanpraksa.contactsapp.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageDto<T> {

    private T content;
    private int pageNumber;
    private int Size;
    private int totalElements;


}
