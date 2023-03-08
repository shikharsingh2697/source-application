package com.rudderstack.sourceapplication.domains.elements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SingleSelect {
    private String type;
    private boolean required;
    private String label;
    private List<SingleSelectOption> options;
}