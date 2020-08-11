package com.kabba.transform.dao.mysql.defined;

import com.kabba.transform.entity.generation.KBSubUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KBSubUserDao {

    void batchInsert(List<KBSubUser> list);

}