package com.ecirsulga.homework5.n11bootcamp.usr.dao;

import com.ecirsulga.homework5.n11bootcamp.usr.entity.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsrUserDao extends JpaRepository<UsrUser,Long> {

    UsrUser findByUsername(String username);

}
