package com.kabba.transform.dao.mysql.defined;

import com.kabba.transform.entity.generation.KBUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: transform
 * @description: 自定义KBUserDao
 * @author: lgs
 * @create: 2020/8/8 10:26
 **/

@Mapper
@Repository
public interface KBUserDao {

    void batchInsert(List<KBUser> list);
}
