package com.kaba.transform.dao.sqlserver.generation;

import com.kaba.transform.entity.generation.Centers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
public interface CentersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Centers record);

    int insertSelective(Centers record);

    Centers selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Centers record);

    int updateByPrimaryKey(Centers record);
}