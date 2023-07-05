package com.example.temporal.activities;

import com.example.temporal.dto.BureauReport;
import com.example.temporal.dto.UserData;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface BureauReportActivity {

    @ActivityMethod
    BureauReport fetchBureauReport(UserData userData);

}
