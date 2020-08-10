package com.kaba.transform.dao.sqlserver.defined;

import com.kaba.transform.entity.generation.CustomerContracts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerContractsDao {

    List<CustomerContracts> findAllCustomerContracts();

}