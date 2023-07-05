package com.example.temporal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author - lakshya.jain <br>
 * Date - 04/07/2023
 */

@NoArgsConstructor
@Data
public class BureauReport {
    private String name;// Temporal was throwing in case we have empty dto (Doesn't contain any attribute)
}
