package com.kaba.transform.dao.sqlserver.defined;

import com.kaba.transform.entity.generation.Users;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDao {

    List<Users> findAllUsers();



}
