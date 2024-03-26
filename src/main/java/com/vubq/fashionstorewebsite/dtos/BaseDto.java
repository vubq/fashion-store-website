package com.vubq.fashionstorewebsite.dtos;

import com.vubq.fashionstorewebsite.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseDto {

//    private String createdBy;
    private Date createdAt;
    private Date updatedAt;
    private EStatus status;
}
