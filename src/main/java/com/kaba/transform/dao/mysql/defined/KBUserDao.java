package com.kaba.transform.dao.mysql.defined;

import com.kaba.transform.entity.generation.KBUser;
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

    List<KBUser> findAllKBUser();

    void batchInsert(List<KBUser> list);
}
