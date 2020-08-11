package com.kabba.transform.dao.sqlserver.defined;

import com.kabba.transform.entity.generation.Customers;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersDao {

    void  findAllCustomers(ResultHandler<Customers> handler);

//    @Select("select name, gender, birthday, masterName1, phoneNumBer1, centerId from customers where centerId = #{centerId}")
    List<Customers> selectByCenterId(String centerId);

}