package com.example.swiftxyback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenTemplateDto {
    String requestBodyGen;
    String responseBodyGen;
}
