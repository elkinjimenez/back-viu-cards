package com.co.viucards.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.viucards.models.Bank;
import com.co.viucards.repositories.BankRepository;

@Service
public class BankService {

  @Autowired
  private BankRepository repository;

  public List<Bank> findByIdUser(Integer idUser) {
    return this.repository.findByIdUser(idUser);
  }

}
