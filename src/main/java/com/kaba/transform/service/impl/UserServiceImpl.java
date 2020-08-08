package com.kaba.transform.service.impl;

import com.google.common.collect.Lists;
import com.kaba.transform.dao.mysql.defined.KBUserDao;
import com.kaba.transform.dao.sqlserver.defined.MembersDao;
import com.kaba.transform.dao.sqlserver.defined.UsersDao;
import com.kaba.transform.entity.generation.KBUser;
import com.kaba.transform.entity.generation.Members;
import com.kaba.transform.entity.generation.Users;
import com.kaba.transform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UsersDao usersDao;

    @Autowired
    private MembersDao membersDao;

    @Autowired
    private KBUserDao kbUserDao;

    /**
     * 功能描述:
     * <转换User信息>
     *
     * @Param: []
     * @Return: void
     * @Author: lgs
     * @Date: 2020/8/8 9:52
     */
    @Transactional(value = "mysqlTransaction", readOnly = true)
    @Override
    public void transformUser() {
        List<Users> usersList = usersDao.findAllUsers();
        List<Members> membersList = membersDao.findAllMembers();

        batchInsert(usersList, Users.class);
        batchInsert(membersList, Members.class);

    }

    /**
     * 功能描述:
     * <批量插入>
     *
     * @Param: [数据源, 数据类型]
     * @Return: void
     * @Author: lgs
     * @Date: 2020/8/8 12:55
     */
    private <T, V> void batchInsert(List<T> list, Class<V> clazz) {
        int size = 2000;
        if (!CollectionUtils.isEmpty(list)) {
            while (list.size() > size) {
                List<T> subList = list.subList(0, size);
                list = list.subList(size, list.size());
                //users转换规则
                List<KBUser> kbUserList = transToKbUser(subList, clazz);
                // 批量入库
                kbUserDao.batchInsert(kbUserList);
            }
            if (!CollectionUtils.isEmpty(list)) {
                List<KBUser> kbUserList = transToKbUser(list, clazz);
                kbUserDao.batchInsert(kbUserList);
            }
        }
    }

    /**
     * 功能描述:
     * <数据转换>
     *
     * @Param: [数据源, 数据类型]
     * @Return: java.util.List<com.kaba.transform.entity.generation.KBUser>
     * @Author: lgs
     * @Date: 2020/8/8 12:57
     */
    private <T, V> List<KBUser> transToKbUser(List<T> list, Class<V> clazz) {
        if (clazz.equals(Users.class)) {
            List<Users> usersList = (List<Users>) list;
            return usersToKbUser(usersList);
        } else if (clazz.equals(Members.class)) {
            List<Members> membersList = (List<Members>) list;
            return membersToKbUser(membersList);
        }
        throw new RuntimeException("参数类型有误");
    }

    /**
     * 功能描述:
     * <users转换KbUser>
     *
     * @Param: usersList
     * @Return: java.util.List<com.kaba.transform.entity.generation.KBUser>
     * @Author: lgs
     * @Date: 2020/8/8 11:51
     */
    private List<KBUser> usersToKbUser(List<Users> usersList) {

        return usersList.stream().map(user -> {
            KBUser kbUser = new KBUser();
            kbUser.setCustomerId(user.getCenterid());
            kbUser.setPhone(user.getPhonenumber());
            kbUser.setUserId(user.getCode());
            kbUser.setPwd(user.getPassword());
            //随机字符
            String encryptionFactor = "";
            for (int i = 0; i < 6; i++) {
                int intVal = (int) (Math.random() * 26 + 97);
                encryptionFactor = encryptionFactor + (char) intVal;
            }
            kbUser.setEncryptionFactor(encryptionFactor);
            kbUser.setIsDeleted(0);
            kbUser.setGmtCreated(new Date());
            kbUser.setGmtModified(new Date());
            kbUser.setCreatedBy("sys");
            kbUser.setModifiedBy("sys");
            return kbUser;
        }).collect(Collectors.toList());
    }

    /**
     * 功能描述:
     * <members转换KbUser>
     *
     * @Param: usersList
     * @Return: java.util.List<com.kaba.transform.entity.generation.KBUser>
     * @Author: lgs
     * @Date: 2020/8/8 11:51
     */
    private List<KBUser> membersToKbUser(List<Members> membersList) {

        return new ArrayList<KBUser>();

//        return membersList.stream().map(member -> {
//            KBUser kbUser = new KBUser();
//            kbUser.setUserId(member.getId());
//            return kbUser;
//        }).collect(Collectors.toList());
    }

}
