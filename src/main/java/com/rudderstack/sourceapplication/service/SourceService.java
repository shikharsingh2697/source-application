package com.rudderstack.sourceapplication.service;

import com.rudderstack.sourceapplication.domains.*;

import java.util.List;

public interface SourceService {
    public SourceFormTemplateResponse createSourceFormTemplate(SourceFormTemplateRequest sourceFormTemplateRequest, Long userId);

    public SourceFormTemplateResponse fetchSourceFormTemplate(String sourceType);

    public List<SourceDetails> fetchAllSourceTypes();

    public SourceResponse createSource(SourceRequest sourceRequest, Long userId);
}