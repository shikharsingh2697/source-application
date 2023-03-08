package com.rudderstack.sourceapplication.domains;

import com.rudderstack.sourceapplication.enums.ElementType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FieldDetails {
    @NotBlank
    private ElementType type;
}
