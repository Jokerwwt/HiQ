package com.hiqgroup.hiq.service;

import com.hiqgroup.hiq.form.HqtCheckerStatisticForm;
import com.hiqgroup.hiq.form.HqtCustStatisticForm;
import com.hiqgroup.hiq.form.HqtPlatformStatisticForm;

import java.util.List;

public interface HqtStatisticService {

    List<HqtCustStatisticForm> getCustStatistic(String operator, String inDate, int statisType);

    List<HqtPlatformStatisticForm> getPlatformStatistic(String operator, String inDate, int statisType);

    List<HqtCheckerStatisticForm> getCheckerStatistic(String operator, String inDate, int statisType);

    String setSyslog(String userId, String menuId, String ip, String operate, String browserversion);

}
