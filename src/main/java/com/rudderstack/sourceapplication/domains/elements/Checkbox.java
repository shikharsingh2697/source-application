package com.rudderstack.sourceapplication.domains.elements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Checkbox {
    private String type;
    private boolean required;
    private String label;
}