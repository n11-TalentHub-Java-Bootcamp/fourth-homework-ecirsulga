package com.ecirsulga.homework5.n11bootcamp.clt.dao;

import com.ecirsulga.homework5.n11bootcamp.clt.entity.CltCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CltCollectionDao extends JpaRepository<CltCollection, Long> {

    @Query("select cltcollection from CltCollection cltcollection where cltcollection.usrUserId = ?1")
    List<CltCollection> findAllCollectionByUsrUserId(Long id);

    @Query("select Sum(cltcollection.collectedAmount) from CltCollection cltcollection left join DbtDebt dbtdebt on cltcollection.dbtDebtId = dbtdebt.id where dbtdebt.usrUserId = ?1 and dbtdebt.debtType = com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType.LATEDEBT")
    BigDecimal sumAllCollectedLateDebtsByUserId(Long id);
}
