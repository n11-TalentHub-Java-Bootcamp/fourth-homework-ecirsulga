package com.ecirsulga.homework5.n11bootcamp.clt.controller;

import com.ecirsulga.homework5.n11bootcamp.clt.dto.CltCollectionSaveRequestDto;
import com.ecirsulga.homework5.n11bootcamp.clt.entity.CltCollection;
import com.ecirsulga.homework5.n11bootcamp.clt.service.CltCollectionEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/collections")
@RequiredArgsConstructor
public class CltCollectionController {

    @Autowired
    private final CltCollectionEntityService cltCollectionEntityService;

    @GetMapping("")
    public ResponseEntity findAll(){
        List<CltCollection> cltCollections = cltCollectionEntityService.findAll();
        return ResponseEntity.ok(cltCollections);
    }

    @GetMapping("/user/collection/{id}")
    public ResponseEntity allCollectionsByUserId(@PathVariable Long id){
        List<CltCollection> cltCollections = cltCollectionEntityService.findAllByUserId(id);
        return ResponseEntity.ok(cltCollections);
    }

    @GetMapping("/user/lateCollection/{id}")
    public ResponseEntity sumAllLateDebtCollections(@PathVariable Long id){
        BigDecimal sumAllLateDebtCollections = cltCollectionEntityService.sumAllCollectedLateDebtsByUserId(id);
        return ResponseEntity.ok(sumAllLateDebtCollections);
    }
    @PostMapping
    public ResponseEntity create(@RequestBody CltCollectionSaveRequestDto cltCollectionSaveRequestDto){
        CltCollection cltCollection = cltCollectionEntityService.makeCollection(cltCollectionSaveRequestDto);
        return ResponseEntity.ok(cltCollection);
    }
}
