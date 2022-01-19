package com.ecirsulga.homework5.n11bootcamp.clt.converter;

import com.ecirsulga.homework5.n11bootcamp.clt.dto.CltCollectionSaveRequestDto;
import com.ecirsulga.homework5.n11bootcamp.clt.entity.CltCollection;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CltCollectionMapper {

    CltCollectionMapper INSTANCE = Mappers.getMapper(CltCollectionMapper.class);

    CltCollection convertToCltCollectionSaveRequestDto(CltCollectionSaveRequestDto cltCollectionSaveRequestDto);

}
