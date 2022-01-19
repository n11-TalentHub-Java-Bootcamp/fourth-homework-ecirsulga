package com.ecirsulga.homework5.n11bootcamp.dbt.controller;

import com.ecirsulga.homework5.n11bootcamp.dbt.dto.DbtDebtSaveRequestDto;
import com.ecirsulga.homework5.n11bootcamp.dbt.entity.DbtDebt;
import com.ecirsulga.homework5.n11bootcamp.dbt.service.DbtDebtEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
@RequiredArgsConstructor
public class DbtDebtController {

    @Autowired
    private final DbtDebtEntityService dbtDebtEntityService;

    @GetMapping("")
    public ResponseEntity findAll(){
        List<DbtDebt> dbtDebts = dbtDebtEntityService.findAll();
        return ResponseEntity.ok(dbtDebts);
    }

    @GetMapping("/user/Debt/{id}")
    public ResponseEntity allDebtsByUserId(@PathVariable Long id){
        List<DbtDebt> dbtDebts = dbtDebtEntityService.findAllDebtsByUserId(id);
        return ResponseEntity.ok(dbtDebts);
    }

    @GetMapping("/user/expiredDebt/{id}")
    public ResponseEntity allExpiredDebtsByUserId(@PathVariable Long id){
        List<DbtDebt> dbtDebts = dbtDebtEntityService.findAllExpiredDebtsByUserId(id);
        return ResponseEntity.ok(dbtDebts);
    }

    @GetMapping("/user/sumDebts/{id}")
    public ResponseEntity sumAllDebtsByUserId(@PathVariable Long id){

        BigDecimal allSum = dbtDebtEntityService.sumAllDebts(id);
        return ResponseEntity.ok(allSum);
    }

    @GetMapping("/user/sumExpiredDebts/{id}")
    public ResponseEntity sumAllExpiredDebtsByUserId(@PathVariable Long id){

        BigDecimal allSum = dbtDebtEntityService.sumAllExpiredDebts(id);
        return ResponseEntity.ok(allSum);
    }

    @GetMapping("/user/sumLateFee/{id}")
    public ResponseEntity sumLateFeeByUserId(@PathVariable Long id){
        BigDecimal allSum = dbtDebtEntityService.sumAllLateFees(id);
        return ResponseEntity.ok(allSum);
    }

    @GetMapping("/user/calculateLateFees/{id}")
    public ResponseEntity calculateLateFeesByUserId(@PathVariable Long id){
        BigDecimal allSum = dbtDebtEntityService.calculateAndSaveLateFees(id);
        return ResponseEntity.ok(allSum);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody DbtDebtSaveRequestDto dbtDebtSaveRequestDto){
        DbtDebt dbtDebt = dbtDebtEntityService.save(dbtDebtSaveRequestDto);
        return ResponseEntity.ok(dbtDebt);
    }



}
