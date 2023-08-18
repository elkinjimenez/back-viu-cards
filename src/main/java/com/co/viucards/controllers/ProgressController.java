package com.co.viucards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.viucards.repositories.ProgressRepository;

@RestController
@RequestMapping("api/progress")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProgressController {

  @Autowired
  private ProgressRepository repository;

}
