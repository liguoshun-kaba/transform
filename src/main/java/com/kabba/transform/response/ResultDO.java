package com.kabba.transform.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kabba.transform.constant.KabbaErrorInfoEnum;
import com.kabba.transform.utils.GenIdService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @projectName: kabba-platform
 * @author: user
 * @description: API统一返回包装类
 * @dateTime: 2020/6/12 10:00
 */
@Data
@ToString
@ApiModel
@NoArgsConstructor
public class ResultDO<T> implements Serializable {

    private static final long serialVersionUID = -172911448358060649L;

    @JsonIgnore
    private transient GenIdService genIdService = new GenIdService();

    /**
     * 接口请求id
     */
    @ApiModelProperty(value = "接口请求id")
    private Long requestId;

    /**
     * 状态(true:成功,false:失败)
     */
    @ApiModelProperty(value = "状态(true:成功,false:失败)")
    private Boolean status = false;

    /**
     * 错误提示
     */
    @ApiModelProperty(value = "错误提示")
    private String message;

    /**
     * 异常信息（不提示给用户） 便于抓包排查问题，特别是未知系统异常，一般在debug模式下启用
     */
    @ApiModelProperty(value = "异常信息")
    private String exception = "";

    /**
     * 响应码
     */
    @ApiModelProperty(value = "响应码")

    private Integer responseCode;

    /**
     *  记录总（分页时有用）
     */
    @ApiModelProperty(value = "记录总数")
    private Long totalRecordSize;


    /**
     *  是否还有下一页
     */
    @ApiModelProperty(value = "是否还有下一页")
    private Boolean hasNext;

    /**
     * 响应时间
     */
    @ApiModelProperty(value = "响应时间")
    private Long timestamp = System.currentTimeMillis();

    /**
     * 当前排序规则, 针对分页 排序的数据
     * */
    @ApiModelProperty(value = "当前排序规则, 针对分页 排序的数据")
    private String orderBy = "";

    /**
     * 业务数据
     */
    @ApiModelProperty(value = "业务数据")
    private T data;

    public ResultDO(Integer responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
        this.requestId = genIdService.nextLongKeyId();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public long getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultDO failure(KabbaErrorInfoEnum errorEnum) {
        if (null == errorEnum) {
            errorEnum = KabbaErrorInfoEnum.SYSTEM_ERROR;
        }
        this.status = false;
        this.responseCode = errorEnum.getErrorCode();
        this.message = errorEnum.getErrorMsg();
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }

    public ResultDO failure(String errorMsg) {
        this.status = false;
        this.responseCode = KabbaErrorInfoEnum.SYSTEM_ERROR.getErrorCode();
        this.message = errorMsg;
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }

    public ResultDO failure(String errorMsg, Exception e) {
        this.status = false;
        this.responseCode = KabbaErrorInfoEnum.SYSTEM_ERROR.getErrorCode();
        this.message = errorMsg;
        this.exception = (e!=null?e.toString():"");
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }

    public ResultDO failure(String errorMsg, int responseCode) {
        this.status = false;
        this.responseCode = responseCode;
        this.message = errorMsg;
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }

    public ResultDO failure(KabbaErrorInfoEnum errorEnum, Exception e) {
        this.responseCode = errorEnum.getErrorCode();
        this.status = false;
        this.message = errorEnum.getErrorMsg();
        this.exception = (e!=null?e.toString():"");
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }

    public ResultDO failure(String errorMsg, int responseCode, Exception e) {
        this.status = false;
        this.responseCode = responseCode;
        this.message = errorMsg;
        this.exception = (e!=null?e.toString():"");
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }


    public ResultDO success() {
        this.status = true;
        this.responseCode = KabbaErrorInfoEnum.DEFAULT_SUCCESS.getErrorCode();
        this.message = KabbaErrorInfoEnum.DEFAULT_SUCCESS.getErrorMsg();
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }

    public ResultDO success(T entry, long totalRecordSize) {
        this.status = true;
        this.responseCode = KabbaErrorInfoEnum.DEFAULT_SUCCESS.getErrorCode();
        this.message = KabbaErrorInfoEnum.DEFAULT_SUCCESS.getErrorMsg();
        this.data = entry;
        this.totalRecordSize = totalRecordSize;
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }

    public ResultDO success(T entry) {
        this.status = true;
        this.responseCode = KabbaErrorInfoEnum.DEFAULT_SUCCESS.getErrorCode();
        this.message = KabbaErrorInfoEnum.DEFAULT_SUCCESS.getErrorMsg();
        this.data = entry;
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }

    public ResultDO success(int responseCode, String message) {
        this.status = true;
        this.responseCode = responseCode;
        this.message = message;
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }

    public ResultDO success(int responseCode, String message, T entry) {
        this.status = true;
        this.responseCode = responseCode;
        this.message = message;
        this.data = entry;
        this.requestId = genIdService.nextLongKeyId();
        return this;
    }


}
