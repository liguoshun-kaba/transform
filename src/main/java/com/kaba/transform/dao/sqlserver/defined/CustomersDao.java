package com.kaba.transform.dao.sqlserver.defined;

import com.kaba.transform.entity.generation.Customers;
import com.kaba.transform.entity.generation.Members;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersDao {

    void  findAllCustomers(ResultHandler<Customers> handler);

//    @Select("select name, gender, birthday, masterName1, phoneNumBer1, centerId from customers where centerId = #{centerId}")
    List<Customers> selectByCenterId(String centerId);

}