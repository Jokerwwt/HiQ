package com.hiqgroup.hiq.service.impl;

import com.hiqgroup.hiq.dao.HqtStatisticDao;
import com.hiqgroup.hiq.entity.SmtParamsset;
import com.hiqgroup.hiq.entity.SmtSyslog;
import com.hiqgroup.hiq.form.HqtCheckerStatisticForm;
import com.hiqgroup.hiq.form.HqtCustStatisticForm;
import com.hiqgroup.hiq.form.HqtPlatformStatisticForm;
import com.hiqgroup.hiq.service.HqtStatisticService;
import com.hiqgroup.hiq.service.SmtParamssetService;
import com.hiqgroup.hiq.service.SmtSyslogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("hqtStatisticService")
public class HqtStatisticServiceImpl implements HqtStatisticService {
    @Resource
    private HqtStatisticDao hqtStatisticDao;

    @Resource
    private SmtSyslogService smtSyslogService;
    @Resource
    private SmtParamssetService smtParamssetService;

    @Override
    public List<HqtCustStatisticForm> getCustStatistic(String operator, String inDate, int statisType) {
        return this.hqtStatisticDao.getCustStatistic(operator, inDate, statisType);
    }

    @Override
    public List<HqtPlatformStatisticForm> getPlatformStatistic(String operator, String inDate, int statisType) {
        return this.hqtStatisticDao.getPlatformStatistic(operator, inDate, statisType);
    }

    @Override
    public List<HqtCheckerStatisticForm> getCheckerStatistic(String operator, String inDate, int statisType) {
        return this.hqtStatisticDao.getCheckerStatistic(operator, inDate, statisType);
    }

    @Override
    public String setSyslog(String userId, String menuId, String ip, String operate, String browserversion){
        SmtParamsset smtParamsset = this.smtParamssetService.queryAll(new SmtParamsset()).get(0);
        if (!smtParamsset.getIssyslog()){
            return "";
        }
        SmtSyslog smtSyslog = new SmtSyslog();
        smtSyslog.setId(UUID.randomUUID().toString()); smtSyslog.setUserid(userId);
        smtSyslog.setMenuid(menuId); smtSyslog.setIpaddress(ip);
        smtSyslog.setOperate(operate); smtSyslog.setOperatetime(new Date());
        smtSyslog.setBrowserversion(browserversion);
        try{
            this.smtSyslogService.insert(smtSyslog);
            return "";
        }catch (Exception ex){
            return ex.toString();
        }
    }
}
