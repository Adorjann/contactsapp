package com.adorjanpraksa.contactsapp.web.dto;

import com.adorjanpraksa.contactsapp.validations.AllContactsRequestValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Data
@AllContactsRequestValidation
public class AllContactsRequestDto {

    @NotBlank
    private LocalDateTime from;
    @NotBlank
    private LocalDateTime to;
}
