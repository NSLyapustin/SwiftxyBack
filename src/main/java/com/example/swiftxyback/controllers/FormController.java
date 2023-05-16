package com.example.swiftxyback.controllers;

import com.example.swiftxyback.dto.DeepLinkInputDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {
    @RequestMapping("/")
    public String formPage(Model model) {
        model.addAttribute("appName", "Swiftxy");
        return "home";
    }

    @RequestMapping("/deeplink")
    public String postData(Model model, DeepLinkInputDto deepLinkInputDto) {
        String scheme = deepLinkInputDto.getScheme();
        String deeplink = "?"
                + "name="
                + deepLinkInputDto.getTemplateName()
                + "&"
                + "template="
                + deepLinkInputDto.getTemplate()
                + "&"
                + "requestBody="
                + deepLinkInputDto.getRequestBody()
                + "&"
                + "responseBody="
                + deepLinkInputDto.getResponseBody()
                ;
        model.addAttribute("scheme", scheme);
        model.addAttribute("deeplink", deeplink);
        return "deeplink";
    }
}
