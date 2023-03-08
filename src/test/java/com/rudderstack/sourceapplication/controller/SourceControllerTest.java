package com.rudderstack.sourceapplication.controller;

import com.rudderstack.sourceapplication.domains.SourceFormTemplateRequest;
import com.rudderstack.sourceapplication.domains.SourceFormTemplateResponse;
import com.rudderstack.sourceapplication.domains.SourceRequest;
import com.rudderstack.sourceapplication.domains.SourceResponse;
import com.rudderstack.sourceapplication.service.SourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.rudderstack.sourceapplication.util.SourceServiceUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SourceControllerTest {
    private SourceController sourceController;
    private SourceService sourceService;

    @BeforeEach
    void setup() {
        sourceService = mock(SourceService.class);
        sourceController = new SourceController(sourceService);
    }
    @Test
    public void testAddSourceFormTemplate() {
        SourceFormTemplateResponse expectedResponse = createSourceFormTemplateResponse();
        SourceFormTemplateRequest request = createSourceFormTemplateRequest();
        when(sourceService.createSourceFormTemplate(request, 1L)).thenReturn(expectedResponse);
        SourceFormTemplateResponse actualResponse = sourceController.addSourceFormTemplate(1L, request);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testAddSource(){
        SourceResponse expectedResponse = createSourceResponse();
        SourceRequest request = createSourceRequest();
        when(sourceService.createSource(request, 1L)).thenReturn(expectedResponse);
        SourceResponse actualResponse = sourceController.addSource(1L, request);
        assertEquals(expectedResponse, actualResponse);
    }

}
