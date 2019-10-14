package com.jsg.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author jeanson 进生
 * @date 2019/10/14 15:31
 */
@Data
public class Baseinfo {
    @ApiModelProperty(value = "主键Id")
    private Integer id;
    @ApiModelProperty(position = 1, value = "姓名", required = true)
    private String fullName;
    @ApiModelProperty(position = 1, value = "性别：1-男；2-女；0-不详；", required = true)
    private Integer sex;
    @ApiModelProperty(position = 1, value = "年龄", required = true)
    private Integer age;
    @ApiModelProperty(position = 1, value = "医生编码", required = true)
    private String ysCode;
    @ApiModelProperty(position = 1, value = "科室编码", required = true)
    private String ksCode;
    @ApiModelProperty(position = 1, value = "科室名称", required = true)
    private String ksName;
    @ApiModelProperty(position = 1, value = "人员类型：1-医生；2-护士；3-医技；99-其他；", required = true)
    private Integer staffType;
    @ApiModelProperty(position = 1, value = "手机号", required = true)
    private String mobileNum;
    @ApiModelProperty(position = 1, value = "职务", required = true)
    private String jobTitle;
    @ApiModelProperty(position = 1, value = "职称", required = true)
    private String profTitle;
    @ApiModelProperty(position = 1, value = "入职日期", required = true)
    private Date joinDate;
    @ApiModelProperty(position = 1, value = "医学专业", required = true)
    private String yxzy;
    @ApiModelProperty(position = 1, value = "医师级别", required = true)
    private String ysjb;
    @ApiModelProperty(position = 1, value = "资格证书编号", required = true)
    private String zgzsBh;
    @ApiModelProperty(position = 1, value = "执业证书编号", required = true)
    private String zyzsBh;
    @ApiModelProperty(position = 1, value = "执业类别", required = true)
    private String zylb;
    @ApiModelProperty(position = 1, value = "执业科目", required = true)
    private String zykm;
    @ApiModelProperty(position = 1, value = "执业状态", required = true)
    private String zyzt;
    @ApiModelProperty(position = 1, value = "备注", required = true)
    private String comment;
    @ApiModelProperty(position = 1, value = "0' COMMENT '是否有一般资质：1-有；0-无；", required = true)
    private Integer hasYbZz;
    @ApiModelProperty(position = 1, value = "0' COMMENT '是否有执业医师资质：1-有；0-无；", required = true)
    private Integer hasZyysZz;
    @ApiModelProperty(position = 1, value = "0' COMMENT '是否有特殊资质：1-有；0-无；", required = true)
    private Integer hasTsZz;
    @ApiModelProperty(position = 1, value = "0' COMMENT '是否有其他资质：1-有；0-无；", required = true)
    private Integer hasQtZz;
    @ApiModelProperty(position = 1, value = "0' COMMENT '是否有医疗技术资质：1-有；0-无；", required = true)
    private Integer hasYljsZz;
    @ApiModelProperty(position = 1, value = "0' COMMENT '是否有手术资质：1-有；0-无；", required = true)
    private Integer hasSsZz;
    @ApiModelProperty(position = 1, value = "创建时间", readOnly = true)
    private Date createTime;
    @ApiModelProperty(position = 1, value = "修改时间", readOnly = true)
    private Date updateTime;
    @ApiModelProperty(position = 1, value = "创建人", required = true)
    private Integer createUserId;
    @ApiModelProperty(position = 1, value = "修改人", required = true)
    private Integer updateUserId;
}
