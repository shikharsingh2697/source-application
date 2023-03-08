package com.rudderstack.sourceapplication.domains.elements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Input {
    private String type;
    private boolean required;
    private String label;
    private String regex;
}