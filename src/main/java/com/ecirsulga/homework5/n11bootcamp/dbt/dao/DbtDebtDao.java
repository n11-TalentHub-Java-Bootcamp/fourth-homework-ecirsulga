package com.ecirsulga.homework5.n11bootcamp.dbt.dao;

import com.ecirsulga.homework5.n11bootcamp.dbt.entity.DbtDebt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface DbtDebtDao extends JpaRepository<DbtDebt, Long> {

    @Query("select dbtdebt from DbtDebt dbtdebt where dbtdebt.usrUserId = ?1 and dbtdebt.remainingDebt > 0")
    List<DbtDebt> findAllDebtbyUsrUserId(Long id);

    @Query("select dbtdebt from DbtDebt dbtdebt where dbtdebt.usrUserId = ?1 and dbtdebt.remainingDebt > 0 and dbtdebt.expireDate < now()")
    List<DbtDebt> findAllExpiredDebtbyUsrUserId(Long id);

    @Query("select dbtdebt from DbtDebt dbtdebt where dbtdebt.usrUserId = ?1 and dbtdebt.remainingDebt > 0 and dbtdebt.debtType = com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType.LATEDEBT")
    List<DbtDebt> findAllDebtbyUsrUserIdTypeLate(Long id);

    @Query("select Sum(dbtdebt.remainingDebt) from DbtDebt dbtdebt where dbtdebt.usrUserId = ?1 and dbtdebt.remainingDebt > 0 and dbtdebt.debtType = com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType.MAINDEBT")
    BigDecimal sumAllDebtbyUsrUserIdTypeMain(Long id);

    @Query("select Sum(dbtdebt.remainingDebt) from DbtDebt dbtdebt where dbtdebt.usrUserId = ?1 and dbtdebt.remainingDebt > 0 and dbtdebt.debtType = com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType.MAINDEBT and dbtdebt.expireDate < now()")
    BigDecimal sumAllExpiredDebtbyUsrUserIdTypeMain(Long id);

    @Query("select dbtdebt from DbtDebt dbtdebt where dbtdebt.usrUserId = ?1 and dbtdebt.remainingDebt > 0 and dbtdebt.debtType = com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType.MAINDEBT and dbtdebt.expireDate < now()")
    List<DbtDebt> findAllExpiredDebtbyUsrUserIdTypeMain(Long id);

    @Query("select Sum(dbtdebt.remainingDebt) from DbtDebt dbtdebt where dbtdebt.usrUserId = ?1 and dbtdebt.remainingDebt > 0 and dbtdebt.debtType = com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType.LATEDEBT")
    BigDecimal sumAllDebtbyUsrUserIdTypeLate(Long id);



    @Query("select dbtdebt from DbtDebt dbtdebt where dbtdebt.usrUserId = ?1 and dbtdebt.remainingDebt > 0 and dbtdebt.debtType = com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType.MAINDEBT")
    List<DbtDebt> findAllDebtbyUsrUserIdTypeMain(Long id);

    @Query("select dbtdebt from DbtDebt dbtdebt where dbtdebt.usrUserId = ?1 and dbtdebt.mainDebtId = ?2 and dbtdebt.debtType = com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType.LATEDEBT")
    DbtDebt findLateDebtByDebtAndUserId(Long userId,Long mainDebtId);

}
