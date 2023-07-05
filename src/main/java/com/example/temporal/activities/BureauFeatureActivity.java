package com.example.temporal.activities;

import com.example.temporal.dto.BureauReport;
import com.example.temporal.dto.ParsedBureauReport;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface BureauFeatureActivity {

    @ActivityMethod
    ParsedBureauReport getParsedBureauReport(BureauReport bureauReport);
}
