package com.example.temporal.activities.impl;

import com.example.temporal.activities.BureauReportActivity;
import com.example.temporal.dto.BureauReport;
import com.example.temporal.dto.UserData;
import com.example.temporal.external.BureauService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Author - lakshya.jain <br>
 * Date - 04/07/2023
 */

@Service
@AllArgsConstructor
public class BureauReportActivityImpl implements BureauReportActivity {

    private final BureauService bureauService;

    @Override
    public BureauReport fetchBureauReport(UserData userData) {
        return bureauService.getBureauReport(userData);
    }
}
