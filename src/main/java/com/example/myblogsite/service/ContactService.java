package com.example.myblogsite.service;

import com.example.myblogsite.entity.Contact;
import com.example.myblogsite.entity.SocialLink;

import java.util.List;

public interface ContactService {
    public List<SocialLink> getAllSocialLinks();
    public Contact getContactDetails();
}
