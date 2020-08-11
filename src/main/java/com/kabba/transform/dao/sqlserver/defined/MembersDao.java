package com.kabba.transform.dao.sqlserver.defined;

import com.kabba.transform.entity.generation.Members;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lgs
 * @Date 2020/8/8 9:31
 * @Version 1.0
 */
@Repository
public interface MembersDao {

    List<Members> findAllMembers();

}
