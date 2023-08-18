package com.co.viucards.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.viucards.repositories.ProgressRepository;

@Service
public class ProgressService {

  @Autowired
  private ProgressRepository repository;

}
