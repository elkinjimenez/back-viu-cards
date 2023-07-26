package com.co.viucards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.viucards.models.Bank;
import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Integer> {

  List<Bank> findByIdUser(Integer idUser);

}
