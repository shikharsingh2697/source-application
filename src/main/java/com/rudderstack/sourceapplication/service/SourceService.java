package com.rudderstack.sourceapplication.service;

import com.rudderstack.sourceapplication.domains.SourceDetails;
import com.rudderstack.sourceapplication.domains.SourceFormTemplateRequest;
import com.rudderstack.sourceapplication.domains.SourceFormTemplateResponse;

import java.util.List;

public interface SourceService {
    public SourceFormTemplateResponse createSourceFormTemplate(SourceFormTemplateRequest sourceFormTemplateRequest, Long userId);

    public SourceFormTemplateResponse fetchSourceFormTemplate(String sourceType);

    public List<SourceDetails> fetchAllSourceTypes();
}