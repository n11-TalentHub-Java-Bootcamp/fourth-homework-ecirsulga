package com.ecirsulga.homework5.n11bootcamp.dbt.entity;

import com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "DBT_DEBT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbtDebt {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal mainDebt;

    private BigDecimal remainingDebt;

    private EnumDbtDebtType debtType;

    @Nullable
    private Long mainDebtId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Transient
    private BigDecimal lateFee;

    private Long usrUserId;

}
