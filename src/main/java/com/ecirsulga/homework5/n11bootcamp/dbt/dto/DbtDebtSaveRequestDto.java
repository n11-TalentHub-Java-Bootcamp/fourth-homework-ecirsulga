package com.ecirsulga.homework5.n11bootcamp.dbt.dto;

import com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DbtDebtSaveRequestDto {

    private Long id;
    private BigDecimal mainDebt;
    private BigDecimal remainingDebt;
    private EnumDbtDebtType debtType;
    private Long mainDebtId;
    private Date expireDate;
    private Date updateDate;
    private BigDecimal lateFee;
    private Long usrUserId;
}
