package com.example.myblogsite.controller;

import com.example.myblogsite.entity.Contact;
import com.example.myblogsite.entity.SocialLink;
import com.example.myblogsite.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/social-links")
    public List<SocialLink> getSocialLinks() {
        return contactService.getAllSocialLinks();
    }

    @GetMapping("/contacts")
    public Contact getContactDetails() {
        return contactService.getContactDetails();
    }
//    @GetMapping("/social-links")
//    public List<SocialLink> getSocialLinks() {
//        List<SocialLink> socialLinks = new ArrayList<>();
//        socialLinks.add(new SocialLink("Facebook", "https://www.facebook.com/binod.lamichhane.5494/", "fa-facebook"));
//        socialLinks.add(new SocialLink("Instagram", "https://www.instagram.com/bee_know_the.31/", "fa-instagram"));
//        return socialLinks;
//    }
//
//    @GetMapping("/contacts")
//    public Contact getContactDetails() {
//        return new Contact("lamibinod31@gmail.com", "+977 9841128088", "Kathmandu, Nepal");
//    }
}
