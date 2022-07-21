package com.adorjanpraksa.contactsapp.web.dto;

import lombok.Builder;

@Builder
public record PageDto<T>(T content, int pageNumber, int pageSize, Long totalElements) {

}
