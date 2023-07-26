package com.co.viucards.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.viucards.models.Bank;
import com.co.viucards.services.BankService;
import com.co.viucards.utils.GeneralResponse;

@RestController
@RequestMapping("api/bank")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BankController {

  @Autowired
  private BankService service;

  @GetMapping("findByIdUser")
  public ResponseEntity<GeneralResponse<List<Bank>>> getList(@RequestParam("idUser") Integer idUser) {
    try {
      List<Bank> listFindByIdUser = service.findByIdUser(idUser);
      GeneralResponse<List<Bank>> response = GeneralResponse.success(listFindByIdUser);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      GeneralResponse<List<Bank>> errorResponse = new GeneralResponse<>();
      errorResponse.setMessage("Error: " + e.getMessage());
      errorResponse.setCode(400);
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

  }

}
