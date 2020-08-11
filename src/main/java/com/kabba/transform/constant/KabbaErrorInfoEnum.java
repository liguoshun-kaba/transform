package com.kabba.transform.constant;

/**
 * @projectName: kabba-platform
 * @author: user
 * @description: 错误码
 * @dateTime: 2020/6/12 9:50
 */
public enum KabbaErrorInfoEnum {

    /**
     *  错误码，数据库插入数据失败
     * */
    INSERT_DB_ERROR(-6, "数据库插入数据失败"),

    /**
     *  错误码，数据库更新数据失败
     * */
    UPDATE_DB_ERROR(-5, "数据库更新数据失败"),

    /**
     *  错误码，登陆错误
     * */
    INVALID_LOGIN(-3, "登陆错误"),

    /**
     *  错误码，参数为空或者不完整
     * */
    INVALID_PARAMS(-2, "参数为空或者不完整"),

    /**
     *  错误码，系统处理失败
     * */
    SYSTEM_ERROR(-1, "系统处理失败"),

    /**
     *  正常码，success
     * */
    DEFAULT_SUCCESS(0, "success"),

    /**
     *  错误码，用户登录状态失效
     * */
    LOGIN_SESSION_FAIL(-7, "用户登录状态失效"),

    /**
     *  错误码，数据库插入数据失败
     * */
    NO_CUSTOMER(-8, "数据库插入数据失败"),

    /**
     *  错误码，缺少用户参数
     * */
    LACK_USER_PARAM(-9, "缺少用户参数"),

    /**
     *  错误码，数据库插入数据失败
     * */
    LACK_PARAM(-10, "数据库插入数据失败"),

    /**
     *  错误码，缺少token
     * */
    NO_TOKEN(-11, "缺少token"),

    /**
     *  错误码，密码不对
     * */
    ERROR_PWD(-12, "密码不对"),

    /**
     *  错误码，添加失败
     * */
    INSERT_FAILURE(-13,"添加失败"),

    /**
     *  错误码，更新失败
     * */
    UPDATE_FAILURE(-14,"更新失败"),

    /**
     *  错误码，文件为空
     * */
    FILE_EMPTY(-15,"文件为空"),

    /**
     *  错误码，io读写异常
     * */
    IO_ERROR(-16,"io读写异常"),

    /**
     *  错误码，获取视频授权失败
     * */
    VOD_ERROR(-17,"获取视频授权失败"),

    /**
     *  错误码，已有重复数据
     * */
    INSERT_BIZ_ERROR(-18,"已有重复数据"),


    /**
     * 登录失败提示语
     * */
    LOGIN_FAILURE_TIPS (-19, "请输入正确的账号或密码"),


    ;

    private int errorCode;

    private String errorMsg;

    KabbaErrorInfoEnum(int errorCode, String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
