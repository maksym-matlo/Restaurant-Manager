package com.maximalus.service.impl;

import com.maximalus.model.Credential;
import com.maximalus.repository.CredentialRepository;
import com.maximalus.service.CredentialService;
import com.maximalus.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {
    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public void save(Credential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        credential.setCreationDate(LocalDateTime.now());
        credential.setChangingDate(LocalDateTime.now());
        credentialRepository.save(credential);
    }

    @Override
    public Credential findByUsername(String username){
        return credentialRepository.findByUsername(username).orElseThrow();
    }
}
