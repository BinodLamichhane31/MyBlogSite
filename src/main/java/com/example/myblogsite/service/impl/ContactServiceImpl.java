package com.example.myblogsite.service.impl;

import com.example.myblogsite.entity.Contact;
import com.example.myblogsite.entity.SocialLink;
import com.example.myblogsite.repository.ContactRepository;
import com.example.myblogsite.repository.SocialLinkRepository;
import com.example.myblogsite.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private SocialLinkRepository socialLinkRepository;

    @Autowired
    private ContactRepository contactRepository;

    public List<SocialLink> getAllSocialLinks() {
        return socialLinkRepository.findAll();
    }

    public Contact getContactDetails() {
        return contactRepository.findById(1L).orElse(null); // Assuming there's only one contact entry
    }
}
