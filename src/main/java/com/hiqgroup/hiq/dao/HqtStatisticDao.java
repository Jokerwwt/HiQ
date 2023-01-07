package com.hiqgroup.hiq.dao;

import com.hiqgroup.hiq.form.HqtCheckerStatisticForm;
import com.hiqgroup.hiq.form.HqtCustStatisticForm;
import com.hiqgroup.hiq.form.HqtPlatformStatisticForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HqtStatisticDao {

    List<HqtCustStatisticForm> getCustStatistic(@Param("operator") String operator, @Param("inDate") String inDate, @Param("statisType") int statisType);

    List<HqtPlatformStatisticForm> getPlatformStatistic(@Param("operator") String operator, @Param("inDate") String inDate, @Param("statisType") int statisType);

    List<HqtCheckerStatisticForm> getCheckerStatistic(@Param("operator") String operator, @Param("inDate") String inDate, @Param("statisType") int statisType);

}
