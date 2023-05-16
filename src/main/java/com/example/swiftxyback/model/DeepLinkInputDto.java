package com.example.swiftxyback.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeepLinkInputDto {
    String scheme;
    String templateName;
    String template;
    String requestBody;
    String responseBody;

    @Override
    public String toString() {
        return "DeepLinkInputDto{" +
                "scheme='" + scheme + '\'' +
                ", templateName='" + templateName + '\'' +
                ", template='" + template + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}
