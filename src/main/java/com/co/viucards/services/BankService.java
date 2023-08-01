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

  public List<Bank> findByIdUserAndName(Integer idUser, String name) {
    return this.repository.findByIdUserAndName(idUser, name);
  }

  public Bank create(Bank bank) {
    bank.setIdLanguaje(1);
    bank.setState(1);
    return repository.save(bank);
  }

}
