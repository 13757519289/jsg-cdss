package com.jsg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsg.base.result.ResultBase;
import com.jsg.base.result.ResultUtil;
import com.jsg.dao.mysql.BaseinfoMapper;
import com.jsg.dao.mysql.QualificationsMapper;
import com.jsg.entity.Baseinfo;
import com.jsg.entity.Pageable;
import com.jsg.entity.QualificationRule;
import com.jsg.entity.Qualifications;
import com.jsg.entity.pojo.Patients;
import com.jsg.service.BaseinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author jeanson 进生
 * @date 2019/10/14 15:47
 */
@Service
public class BaseinfoServiceImpl implements BaseinfoService {

    @Autowired
    private BaseinfoMapper baseinfoMapper;
    @Autowired
    private QualificationsMapper qualificationsMapper;

    @Value("${apiStatus.failure}")
    private Integer failure;

    @Value("${apiStatus.exc}")
    private Integer exc;

    @Value("${apiStatus.success}")
    private Integer success;

    @Override
    public ResultBase list(String queryKey, Integer sex, String ksCode, Integer position, String zzName, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<Baseinfo> list = qualificationsMapper.list(queryKey, sex, ksCode, position, zzName);
        PageInfo<Baseinfo> pageInfo = new PageInfo<>(list);
        return ResultUtil.success(null, pageInfo);
    }

    @Override
    public ResultBase ediStatusQualifications(Integer staffId, Integer status) {
        int flag = qualificationsMapper.batchEdiStatusQualifications(staffId, status);
        return ResultUtil.success(null, flag);
    }


    @Override
    public ResultBase add(Qualifications qualifications) {
        @NotNull(message = "type is notnull") String catalogCode = qualifications.getCatalogCode();
/*        人资-资质-一般资质	RZ-ZZ-YBZZ
        人资-资质-特殊资质	RZ-ZZ-TSZZ
        人资-资质-医疗技术资质	RZ-ZZ-YLJSZZ
        人资-资质-其他资质	RZ-ZZ-QTZZ
        人资-资质-手术资质	RZ-ZZ-SSZZ*/
        Baseinfo info = new Baseinfo();
        info.setId(qualifications.getStaffId());
        switch (catalogCode) {
            case "RZ-ZZ-YBZZ":
                info.setHasYbZz(1);
                break;
            case "RZ-ZZ-TSZZ":
                info.setHasTsZz(1);
                break;
            case "RZ-ZZ-YLJSZZ":
                info.setHasYljsZz(1);
                break;
            case "RZ-ZZ-QTZZ":
                info.setHasQtZz(1);
                break;
            case "RZ-ZZ-SSZZ":
                info.setHasSsZz(1);
                break;


        }

        baseinfoMapper.updateByPrimaryKeySelective(info);
        int a = qualificationsMapper.add(qualifications);
        return ResultUtil.success(null, qualifications);
    }


    @Override
    public ResultBase ediStatusZz(Integer qualificationsId, Integer status) {
        Qualifications qualifications = new Qualifications();
        qualifications.setId(qualificationsId);
        qualifications.setStatus(status);
        qualificationsMapper.edi(qualifications);
        return ResultUtil.success(null, qualifications);
    }

    @Override
    public ResultBase edi(Qualifications qualifications) {
        ResultBase resultBase = ResultUtil.success(null, null);
        qualificationsMapper.edi(qualifications);
        resultBase.setData(qualifications);
        return resultBase;
    }

    @Override
    public ResultBase del(Integer qualificationId) {
        //查询 资质信息
        Qualifications qualifications = qualificationsMapper.selectByOne(qualificationId);
        @NotNull(message = "type is notnull") Integer staffId = qualifications.getStaffId();
        @NotNull(message = "type is notnull") String catalogCode = qualifications.getCatalogCode();
        int flag = qualificationsMapper.del(qualificationId);
        //用户是否多个相同的资质
        List<Qualifications> lists = qualificationsMapper.selectByStaffIdAndCatalogCode(staffId, catalogCode);

        if (lists.size() == 0) {
            Baseinfo baseinfo = new Baseinfo();
            baseinfo.setId(qualifications.getStaffId());
            switch (catalogCode) {
                case "RZ-ZZ-YBZZ":
                    baseinfo.setHasYbZz(0);
                    break;
                case "RZ-ZZ-TSZZ":
                    baseinfo.setHasTsZz(0);
                    break;
                case "RZ-ZZ-YLJSZZ":
                    baseinfo.setHasYljsZz(0);
                    break;
                case "RZ-ZZ-QTZZ":
                    baseinfo.setHasQtZz(0);
                    break;
                case "RZ-ZZ-SSZZ":
                    baseinfo.setHasSsZz(0);
                    break;
            }
            baseinfoMapper.updateByPrimaryKeySelective(baseinfo);
        }

        return ResultUtil.success(null, flag);
    }


    @Override
    public ResultBase listByassociationListQualification(String queryKey, Integer staffId, String catalogCode, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<QualificationRule> list = qualificationsMapper.listByassociationListQualification(queryKey, staffId, catalogCode);
        PageInfo<QualificationRule> pageInfo = new PageInfo<>(list);
        return ResultUtil.success(null, pageInfo);
    }

    @Override
    public ResultBase detailsByQualification(Integer staffId) {
        List<Qualifications> lists = qualificationsMapper.detailsByQualification(staffId);
        HashSet<Qualifications> qualifications = new HashSet<>(lists);
        //遍历
        HashMap<String, ArrayList<Qualifications>> hash = new HashMap<>();
        Iterator iterator = qualifications.iterator();
        while (iterator.hasNext()) {
            Qualifications next = (Qualifications) iterator.next();
            ArrayList<Qualifications> ss = new ArrayList<>();
            for (Qualifications s : lists) {
                @NotNull(message = "type is notnull") String catalogCode = s.getCatalogCode();
                @NotNull(message = "type is notnull") String catalogCode1 = next.getCatalogCode();
                if (catalogCode.equals(catalogCode1)) {
                    ss.add(s);
                }

            }
            hash.put(next.getCatalogCode(), ss);
            System.out.println();
        }
        return ResultUtil.success(null, hash);
    }

    @Override
    public List<Patients> listByYsCode(String ysCode) {
        return qualificationsMapper.listByYsCode(ysCode);
    }
}
