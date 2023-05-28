package com.example.swiftxyback.controllers;

import com.example.swiftxyback.dto.DeepLinkInputDto;
import com.example.swiftxyback.model.DeepLink;
import com.example.swiftxyback.repositories.DeeplinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class FormController {

    @Autowired
    DeeplinkRepository deeplinkRepository;
    @RequestMapping("/")
    public String formPage(Model model) {
        model.addAttribute("appName", "Swiftxy");
        return "home";
    }

    @RequestMapping("/deeplink")
    public String postData(Model model, DeepLinkInputDto deepLinkInputDto) {
        UUID uuid = UUID.randomUUID();
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
        DeepLink deepLink = DeepLink.builder()
                .id(uuid)
                .scheme(scheme)
                .content(deeplink)
                .build();
        deeplinkRepository.save(deepLink);
        model.addAttribute("sharingUuid", uuid.toString());
        model.addAttribute("scheme", scheme);
        model.addAttribute("deeplink", deeplink);
        return "deeplink";
    }

    @GetMapping("/deeplink/{uuid}")
    public String getDeeplinkPage(@PathVariable(value="uuid") String uuid, Model model) {
        DeepLink deepLink = deeplinkRepository.findById(UUID.fromString(uuid)).get();
        model.addAttribute("sharingUuid", uuid);
        model.addAttribute("scheme", deepLink.getScheme());
        model.addAttribute("deeplink", deepLink.getContent());
        return "deeplink";
    }
}
