package com.ecirsulga.homework5.n11bootcamp.clt.entity;

import com.ecirsulga.homework5.n11bootcamp.usr.entity.UsrUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CLT_COLLECTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CltCollection {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal collectedAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date collectedDate;

    private Long dbtDebtId;

    private Long usrUserId;



}