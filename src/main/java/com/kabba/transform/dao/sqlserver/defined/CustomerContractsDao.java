package com.kabba.transform.dao.sqlserver.defined;

import com.kabba.transform.entity.generation.CustomerContracts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerContractsDao {

    List<CustomerContracts> findAllCustomerContracts();

}