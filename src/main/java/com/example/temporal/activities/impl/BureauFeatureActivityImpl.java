package com.example.temporal.activities.impl;

import com.example.temporal.activities.BureauFeatureActivity;
import com.example.temporal.dto.BureauReport;
import com.example.temporal.dto.ParsedBureauReport;
import com.example.temporal.external.BureauFeatureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Author - lakshya.jain <br>
 * Date - 04/07/2023
 */

@Service
@AllArgsConstructor
public class BureauFeatureActivityImpl implements BureauFeatureActivity {

    private final BureauFeatureService bureauFeatureService;

    @Override
    public ParsedBureauReport getParsedBureauReport(BureauReport bureauReport) {
        return bureauFeatureService.getBureauReport(bureauReport);
    }
}
