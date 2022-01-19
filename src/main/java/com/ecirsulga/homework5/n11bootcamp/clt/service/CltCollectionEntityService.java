package com.ecirsulga.homework5.n11bootcamp.clt.service;

import com.ecirsulga.homework5.n11bootcamp.clt.converter.CltCollectionMapper;
import com.ecirsulga.homework5.n11bootcamp.clt.dao.CltCollectionDao;
import com.ecirsulga.homework5.n11bootcamp.clt.dto.CltCollectionSaveRequestDto;
import com.ecirsulga.homework5.n11bootcamp.clt.entity.CltCollection;
import com.ecirsulga.homework5.n11bootcamp.dbt.dao.DbtDebtDao;
import com.ecirsulga.homework5.n11bootcamp.dbt.entity.DbtDebt;
import com.ecirsulga.homework5.n11bootcamp.dbt.service.DbtDebtEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CltCollectionEntityService {

    @Autowired
    CltCollectionDao cltCollectionDao;
    @Autowired
    private final DbtDebtEntityService dbtDebtEntityService;
    @Autowired
    DbtDebtDao dbtDebtDao;

    public List<CltCollection> findAll(){
        return (List<CltCollection>) cltCollectionDao.findAll();
    }

    public CltCollection makeCollection(CltCollectionSaveRequestDto cltCollectionSaveRequestDto) {
        CltCollection cltCollection = CltCollectionMapper.INSTANCE.convertToCltCollectionSaveRequestDto(cltCollectionSaveRequestDto);
        cltCollection = cltCollectionDao.save(cltCollection);
        dbtDebtEntityService.calculateAndSaveLateFees(cltCollectionSaveRequestDto.getUsrUserId());

        Optional<DbtDebt> optionalDbtDebt = dbtDebtDao.findById(cltCollectionSaveRequestDto.getDbtDebtId());
        DbtDebt dbtDebt = null;

        if(optionalDbtDebt.isPresent())
        {
            dbtDebt = optionalDbtDebt.get();
        }
        double remainingDebt = dbtDebt.getRemainingDebt().doubleValue()- cltCollectionSaveRequestDto.getCollectedAmount().doubleValue();
        dbtDebt.setRemainingDebt(BigDecimal.valueOf(remainingDebt));
        dbtDebt.setUpdateDate(Date.from(Instant.now()));
        dbtDebtDao.save(dbtDebt);
        return  cltCollection;
    }

    public List<CltCollection> findAllByUserId(Long id)
    {
        return cltCollectionDao.findAllCollectionByUsrUserId(id);
    }

    public BigDecimal sumAllCollectedLateDebtsByUserId(Long id)
    {
        return cltCollectionDao.sumAllCollectedLateDebtsByUserId(id);
    }






}
