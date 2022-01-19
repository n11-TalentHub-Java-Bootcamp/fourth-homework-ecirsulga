package com.ecirsulga.homework5.n11bootcamp.usr.converter;

import com.ecirsulga.homework5.n11bootcamp.usr.dto.UsrUserDto;
import com.ecirsulga.homework5.n11bootcamp.usr.dto.UsrUserSaveRequestDto;
import com.ecirsulga.homework5.n11bootcamp.usr.entity.UsrUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrUserMapper {

    UsrUserMapper INSTANCE = Mappers.getMapper(UsrUserMapper.class);

    UsrUserDto convertToUsrUserDtoList(UsrUser usrUser);

    List<UsrUserDto> convertToUsrUserDtoList(List<UsrUser> usrUserList);

    UsrUser convertToUsrUserSaveRequestDto(UsrUserSaveRequestDto usrUserSaveRequestDto);

    UsrUserSaveRequestDto convertToSaveRequestDtoUsrUser(UsrUser usrUser);

}
