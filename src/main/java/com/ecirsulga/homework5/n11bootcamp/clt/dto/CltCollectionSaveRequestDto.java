package com.ecirsulga.homework5.n11bootcamp.clt.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CltCollectionSaveRequestDto {

    private Long id;
    private BigDecimal collectedAmount;
    private Date collectedDate;
    private Long dbtDebtId;
    private Long usrUserId;
}
