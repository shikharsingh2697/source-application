package com.rudderstack.sourceapplication.controller;

import com.rudderstack.sourceapplication.domains.*;
import com.rudderstack.sourceapplication.service.SourceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class SourceController {

    private final SourceService sourceService;
    @Autowired
    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }
    @PostMapping("/source-form-templates")
    @ResponseStatus(HttpStatus.CREATED)
    public SourceFormTemplateResponse addSourceFormTemplate(@RequestHeader(value = "userId") Long userId,
                                                            @RequestBody @Valid SourceFormTemplateRequest request) {

        return sourceService.createSourceFormTemplate(request, userId);
    }

    @GetMapping("/source-form-templates")
    public SourceFormTemplateResponse getSourceFormTemplate(@RequestParam(value = "type") String type) {
        return sourceService.fetchSourceFormTemplate(type);
    }

    @GetMapping("/source-form-templates/source-types")
    public List<SourceDetails> getAllSourceTypes() {
        return sourceService.fetchAllSourceTypes();
    }

    @PostMapping("/sources")
    @ResponseStatus(HttpStatus.CREATED)
    public SourceResponse addSource(@RequestHeader(value = "userId") Long userId,
                                    @RequestBody @Valid SourceRequest request) {
        return sourceService.createSource(request, userId);
    }
}