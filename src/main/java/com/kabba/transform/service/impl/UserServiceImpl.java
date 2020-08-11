package com.kabba.transform.service.impl;

import com.kabba.transform.dao.mysql.defined.KBSubUserDao;
import com.kabba.transform.dao.mysql.defined.KBUserDao;
import com.kabba.transform.dao.sqlserver.defined.CustomerContractsDao;
import com.kabba.transform.dao.sqlserver.defined.CustomersDao;
import com.kabba.transform.dao.sqlserver.defined.MembersDao;
import com.kabba.transform.dao.sqlserver.generation.CentersMapper;
import com.kabba.transform.entity.generation.*;
import com.kabba.transform.service.UserService;
import com.kabba.transform.utils.EncrypDecryp;
import com.kabba.transform.utils.GenIdService;
import com.kabba.transform.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Class transCustomers = Customers.class;

    private static final Class transMembers = Members.class;

    private final static String CENTER_ID = "3EF1CBE5-852F-11EA-82A5-988826FE90B7";

    private final static Integer INSERT_SIZE = 2000;

    private List<String> kbUserIds = new LinkedList<>();

    @Autowired
    private CustomersDao customersDao;

    @Autowired
    private MembersDao membersDao;

    @Autowired
    private KBUserDao kbUserDao;

    @Autowired
    private KBSubUserDao kbSubUserDao;

    @Autowired
    private CustomerContractsDao customerContractsDao;

    @Autowired
    private CentersMapper centersMapper;

    @Autowired
    private GenIdService genIdService;



    /**
     * 功能描述:
     * <转换用户信息>
     *
     * @Param:
     * @Return: void
     * @Author: lgs
     * @Date: 2020/8/8 9:52
     */
    @Transactional(value = "mysqlTransaction")
    @Override
    public void transformUser() {
        List<Members> membersList = membersDao.findAllMembers();
        batchInsert(membersList, transMembers);

        //90w数据, 流式读取
//        List<Customers> customersList = new ArrayList<>();
//        customersDao.findAllCustomers(handle -> customersList.add(handle.getResultObject()));
//        batchInsert(customersList, transCustomers);

        //customer通过线上中心查询, 过滤掉已存在于member的数据
        List<CustomerContracts> customerContracts = customerContractsDao.findAllCustomerContracts();
        List<String> customerIds = customerContracts.stream().
                map(CustomerContracts::getCustomerid).
                collect(Collectors.toList());
        List<Customers> customerList = customersDao.selectByCenterId(CENTER_ID).
                stream().filter(customers -> !customerIds.contains(customers.getId())).
                collect(Collectors.toList());

        batchInsert(customerList, transCustomers);
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

        if (!CollectionUtils.isEmpty(list)) {
            while (list.size() > INSERT_SIZE) {
                List<T> subList = list.subList(0, INSERT_SIZE);
                list = list.subList(INSERT_SIZE, list.size());
                //转换, 批量插入
                List<KBUser> kbUserList = transToKbUser(subList, clazz);
                kbUserDao.batchInsert(kbUserList);

                List<KBSubUser> kbSubUserList = transToKbSubUser(subList, clazz);
                kbSubUserDao.batchInsert(kbSubUserList);
            }
            if (!CollectionUtils.isEmpty(list)) {
                List<KBUser> kbUserList = transToKbUser(list, clazz);
                kbUserDao.batchInsert(kbUserList);

                List<KBSubUser> kbSubUserList = transToKbSubUser(list, clazz);
                kbSubUserDao.batchInsert(kbSubUserList);
            }
        }
    }

    /**
     * 功能描述:
     * <数据转换>
     *
     * @Param: [数据源, 数据类型]
     * @Return: java.util.List<com.kabba.transform.entity.generation.KBUser>
     * @Author: lgs
     * @Date: 2020/8/8 12:57
     */
    private <T, V> List<KBUser> transToKbUser(List<T> list, Class<V> clazz) {
        if (clazz.equals(transCustomers)) {
            List<Customers> customerList = (List<Customers>) list;
            return customerToKbUser(customerList);
        } else if (clazz.equals(transMembers)) {
            List<Members> membersList = (List<Members>) list;
            return membersToKbUser(membersList);
        }
        throw new RuntimeException("参数类型有误");
    }

    private <T, V> List<KBSubUser> transToKbSubUser(List<T> list, Class<V> clazz) {
        if (clazz.equals(transCustomers)) {
            List<Customers> customerList = (List<Customers>) list;
            return customerToKbSubUser(customerList);
        } else if (clazz.equals(transMembers)) {
            List<Members> membersList = (List<Members>) list;
            return membersToKbSubUser(membersList);
        }
        throw new RuntimeException("参数类型有误");
    }

    /**
     * 功能描述:
     * <数据转换>
     *
     * @Param: customersList
     * @Return: java.util.List<com.kabba.transform.entity.generation.KBUser>
     * @Author: lgs
     * @Date: 2020/8/8 11:51
     */
    private List<KBUser> membersToKbUser(List<Members> membersList) {
        return membersList.stream().
                map(member -> getKbUser(member.getPhonenumber(), member.getParentname())).
                collect(Collectors.toList());
    }

    private List<KBSubUser> membersToKbSubUser(List<Members> membersList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<KBSubUser> kbSubUserList = new ArrayList<>();
        for (int i = 0; i < membersList.size(); i++) {
            KBSubUser kbSubUser = new KBSubUser();
            Members member = membersList.get(i);
            kbSubUser.setMemberId(member.getId());
            kbSubUser.setMemberCardNo(member.getCode());
            kbSubUser.setRealName(member.getName());
            kbSubUser.setGender(member.getGender());
            kbSubUser.setBirthday(sdf.format(member.getBirthday()));
            kbSubUser.setSubUserId(genIdService.nextStrKeyId());
            String userId = kbUserIds.get(i);
            kbSubUser.setUserId(userId);

            String centerName = centersMapper.selectByPrimaryKey(member.getCenterid()).getName();
            kbSubUser.setCenterName(centerName);
            kbSubUser.setCenterId(member.getCenterid());

            kbSubUser.setIsDeleted(0);
            kbSubUser.setGmtCreated(new Date());
            kbSubUser.setGmtModified(new Date());
            kbSubUser.setCreatedBy("sys");
            kbSubUser.setModifiedBy("sys");

            kbSubUserList.add(kbSubUser);
        }
        kbUserIds.clear();
        return kbSubUserList;
    }

    private List<KBUser> customerToKbUser(List<Customers> customerList) {

        return customerList.stream().
                map(customer -> getKbUser(customer.getPhonenumber1(), customer.getMastername1())).
                collect(Collectors.toList());
    }

    private List<KBSubUser> customerToKbSubUser(List<Customers> customerList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<KBSubUser> kbSubUserList = new ArrayList<>();
        for (int i = 0; i < customerList.size(); i++) {
            KBSubUser kbSubUser = new KBSubUser();
            String userId = kbUserIds.get(i);
            Customers customer = customerList.get(i);

            kbSubUser.setRealName(customer.getName());
            kbSubUser.setGender(customer.getGender());
            kbSubUser.setBirthday(sdf.format(customer.getBirthday()));
            kbSubUser.setCenterId(customer.getCenterid());
            kbSubUser.setSubUserId(genIdService.nextStrKeyId());
            kbSubUser.setUserId(userId);

            String centerName = centersMapper.selectByPrimaryKey(customer.getCenterid()).getName();
            kbSubUser.setCenterName(centerName);

            kbSubUser.setIsDeleted(0);
            kbSubUser.setGmtCreated(new Date());
            kbSubUser.setGmtModified(new Date());
            kbSubUser.setCreatedBy("sys");
            kbSubUser.setModifiedBy("sys");
            kbSubUserList.add(kbSubUser);
        }
        kbUserIds.clear();
        return kbSubUserList;
    }

    /**
     * 功能描述:
     * <设置KbUser属性>
     *
     * @Param: [phone, realName]
     * @Return: com.kabba.transform.entity.generation.KBUser
     * @Author: lgs
     * @Date: 2020/8/10 9:12
     */
    private KBUser getKbUser(String phone, String realName) {
        KBUser kbUser = new KBUser();
        kbUser.setPhone(phone);
        kbUser.setRealName(realName);
        String userId = genIdService.nextStrKeyId();
        kbUser.setUserId(userId);
        kbUserIds.add(userId);
        String encryptionFactor = MyStringUtils.randomStr();
        kbUser.setEncryptionFactor(encryptionFactor);
        //手机后6位和加密因子用MD5加密
        kbUser.setPwd(EncrypDecryp.md5Digest(phone.substring(phone.length() - 6) + encryptionFactor));
        kbUser.setIsLeave((byte) 0);
        kbUser.setIsDeleted(0);
        kbUser.setGmtCreated(new Date());
        kbUser.setGmtModified(new Date());
        kbUser.setCreatedBy("sys");
        kbUser.setModifiedBy("sys");
        return kbUser;
    }
}
