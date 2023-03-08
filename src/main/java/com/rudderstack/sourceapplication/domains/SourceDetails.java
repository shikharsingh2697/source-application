package com.rudderstack.sourceapplication.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceDetails {
    private String value;
    private String label;
}
