package com.ecirsulga.homework5.n11bootcamp.dbt.converter;

import com.ecirsulga.homework5.n11bootcamp.dbt.dto.DbtDebtSaveRequestDto;
import com.ecirsulga.homework5.n11bootcamp.dbt.entity.DbtDebt;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DbtDebtMapper {

    DbtDebtMapper INSTANCE = Mappers.getMapper(DbtDebtMapper.class);

    DbtDebt convertToDbtDebtSaveRequestDto(DbtDebtSaveRequestDto dbtDebtSaveRequestDto);
}
