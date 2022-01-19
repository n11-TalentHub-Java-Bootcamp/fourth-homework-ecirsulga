package com.ecirsulga.homework5.n11bootcamp.dbt.service;


import com.ecirsulga.homework5.n11bootcamp.dbt.converter.DbtDebtMapper;
import com.ecirsulga.homework5.n11bootcamp.dbt.dao.DbtDebtDao;
import com.ecirsulga.homework5.n11bootcamp.dbt.dto.DbtDebtSaveRequestDto;
import com.ecirsulga.homework5.n11bootcamp.dbt.entity.DbtDebt;
import com.ecirsulga.homework5.n11bootcamp.dbt.enums.EnumDbtDebtType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Transactional
@Service
public class DbtDebtEntityService {

    @Autowired
    private DbtDebtDao dbtDebtDao;


    public List<DbtDebt> findAll(){
        return (List<DbtDebt>) dbtDebtDao.findAll();
    }

    public DbtDebt save(DbtDebtSaveRequestDto dbtDebtSaveRequestDto) {

        if(dbtDebtSaveRequestDto.getRemainingDebt() == null)
        {
            dbtDebtSaveRequestDto.setRemainingDebt(dbtDebtSaveRequestDto.getMainDebt());
        }
        DbtDebt dbtDebt = DbtDebtMapper.INSTANCE.convertToDbtDebtSaveRequestDto(dbtDebtSaveRequestDto);
        dbtDebt.setUpdateDate(Date.from(Instant.now()));
        dbtDebt = (DbtDebt) dbtDebtDao.save(dbtDebt);
        return dbtDebt;
    }

    public List<DbtDebt> findAllDebtsByUserId(Long id) {
        List<DbtDebt> dbtDebtList = dbtDebtDao.findAllDebtbyUsrUserId(id);
        return dbtDebtList;
    }
    public List<DbtDebt> findAllExpiredDebtsByUserId(Long id) {
        List<DbtDebt> dbtDebtList = dbtDebtDao.findAllExpiredDebtbyUsrUserId(id);
        return dbtDebtList;
    }

    public BigDecimal sumAllDebts(Long id)
    {
        BigDecimal allDebtsSum = dbtDebtDao.sumAllDebtbyUsrUserIdTypeMain(id);
        return allDebtsSum;
    }

    public BigDecimal sumAllExpiredDebts(Long id)
    {
        BigDecimal allExpiredDebtsSum = dbtDebtDao.sumAllExpiredDebtbyUsrUserIdTypeMain(id);
        return allExpiredDebtsSum;
    }

    public BigDecimal sumAllLateFees(Long id)
    {
        BigDecimal allExpiredDebtsSum = dbtDebtDao.sumAllDebtbyUsrUserIdTypeLate(id);
        return allExpiredDebtsSum;
    }

    public BigDecimal calculateAndSaveLateFees(Long userId)
    {
        List<DbtDebt> allExpiredDebtbyUsrUserIdTypeMain = dbtDebtDao.findAllExpiredDebtbyUsrUserIdTypeMain(userId);
        double sumOfAllLateFees = 0;
        for (DbtDebt debt: allExpiredDebtbyUsrUserIdTypeMain)
        {
            Long dayCount = -1L*TimeUnit.DAYS.convert(debt.getExpireDate().getTime() - Date.from(Instant.now()).getTime() , TimeUnit.MILLISECONDS);
            BigDecimal calculatedLateFee = debt.getMainDebt().multiply((BigDecimal.valueOf(2).divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(dayCount)));
            DbtDebt lateFeeDebt = dbtDebtDao.findLateDebtByDebtAndUserId(userId,debt.getId());
            debt.setLateFee(calculatedLateFee);
            if(lateFeeDebt == null)
            {
                DbtDebt newLateFeeDebt = new DbtDebt();
                newLateFeeDebt.setMainDebtId(debt.getId());
                newLateFeeDebt.setDebtType(EnumDbtDebtType.LATEDEBT);
                newLateFeeDebt.setMainDebt(calculatedLateFee);
                newLateFeeDebt.setRemainingDebt(calculatedLateFee);
                newLateFeeDebt.setUpdateDate(Date.from(Instant.now()));
                newLateFeeDebt.setExpireDate(null);
                newLateFeeDebt.setUsrUserId(debt.getUsrUserId());
                dbtDebtDao.save(newLateFeeDebt);
            }
            else {
                lateFeeDebt.setMainDebt(calculatedLateFee);
                lateFeeDebt.setRemainingDebt(calculatedLateFee);
                lateFeeDebt.setUpdateDate(Date.from(Instant.now()));
                dbtDebtDao.save(lateFeeDebt);
            }
            sumOfAllLateFees += Double.parseDouble(calculatedLateFee.toString());
        }

        return BigDecimal.valueOf(sumOfAllLateFees);
    }



}
