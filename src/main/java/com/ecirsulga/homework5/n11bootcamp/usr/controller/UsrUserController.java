package com.ecirsulga.homework5.n11bootcamp.usr.controller;


import com.ecirsulga.homework5.n11bootcamp.usr.converter.UsrUserMapper;
import com.ecirsulga.homework5.n11bootcamp.usr.dao.UsrUserDao;
import com.ecirsulga.homework5.n11bootcamp.usr.dto.UsrUserDto;
import com.ecirsulga.homework5.n11bootcamp.usr.dto.UsrUserSaveRequestDto;
import com.ecirsulga.homework5.n11bootcamp.usr.entity.UsrUser;
import com.ecirsulga.homework5.n11bootcamp.usr.service.UsrUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usr/users")
public class UsrUserController {

    @Autowired
    private UsrUserEntityService usrUserEntityService;


    @GetMapping("")
    public ResponseEntity findAll(){

        List<UsrUser> usrUserList = usrUserEntityService.findAll();
        List<UsrUserDto> usrUserDtoList = UsrUserMapper.INSTANCE.convertToUsrUserDtoList(usrUserList);
        return ResponseEntity.ok(usrUserDtoList);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UsrUserSaveRequestDto usrUserSaveRequestDto){

        UsrUserDto usrUserDto = usrUserEntityService.save(usrUserSaveRequestDto);
        return ResponseEntity.ok(usrUserDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        usrUserEntityService.delete(id);
    }
}
