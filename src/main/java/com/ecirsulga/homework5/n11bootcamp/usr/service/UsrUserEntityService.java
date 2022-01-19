package com.ecirsulga.homework5.n11bootcamp.usr.service;


import com.ecirsulga.homework5.n11bootcamp.usr.converter.UsrUserMapper;
import com.ecirsulga.homework5.n11bootcamp.usr.dao.UsrUserDao;
import com.ecirsulga.homework5.n11bootcamp.usr.dto.UsrUserDto;
import com.ecirsulga.homework5.n11bootcamp.usr.dto.UsrUserSaveRequestDto;
import com.ecirsulga.homework5.n11bootcamp.usr.entity.UsrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UsrUserEntityService {
    @Autowired
    private UsrUserDao usrUserDao;


    public List<UsrUser> findAll(){
        return (List<UsrUser>) usrUserDao.findAll();
    }

    public UsrUser findById(Long id){
        Optional<UsrUser> optionalUsrUser = usrUserDao.findById(id);

        UsrUser UsrUser = null;
        if (optionalUsrUser.isPresent()){
            UsrUser = optionalUsrUser.get();
        }

        return UsrUser;
    }

    public UsrUser findByUserName(String userName){
        UsrUser UsrUser = usrUserDao.findByUsername(userName);

        return UsrUser;
    }


    public UsrUserDto save(UsrUserSaveRequestDto usrUserSaveRequestDto) {

        validateUserRequest(usrUserSaveRequestDto.getUsername());

        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUserSaveRequestDto(usrUserSaveRequestDto);

        usrUser = usrUserDao.save(usrUser);

        UsrUserDto usrUserDto = UsrUserMapper.INSTANCE.convertToUsrUserDtoList(usrUser);

        return usrUserDto;
    }

    private void validateUserRequest(String username) {

        UsrUser user = findByUserName(username);

        if (user != null){
            throw new RuntimeException("Username already in use");
        }
    }


    public void delete(Long id){
        UsrUser usrUser= findById(id);
        usrUserDao.delete(usrUser);
    }

    public void deleteById(Long id){
        usrUserDao.deleteById(id);
    }

    public long count(){
        return usrUserDao.count();
    }


}
