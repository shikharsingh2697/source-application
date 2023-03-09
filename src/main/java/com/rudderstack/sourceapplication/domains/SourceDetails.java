package com.rudderstack.sourceapplication.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceDetails implements Serializable {
    private String value;
    private String label;
}
