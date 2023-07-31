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
import com.co.viucards.models.User;
import com.co.viucards.services.BankService;
import com.co.viucards.services.UserService;
import com.co.viucards.utils.GeneralResponse;

@RestController
@RequestMapping("api/bank")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BankController {

  @Autowired
  private BankService service;

  @Autowired
  private UserService serviceUser;

  @GetMapping("findByEmailUser")
  public ResponseEntity<GeneralResponse<List<Bank>>> findByEmailUser(
      @RequestParam(name = "email", required = false) String email) {
    try {
      if (email == null) {
        GeneralResponse<List<Bank>> response = GeneralResponse.error("El campo email es obligatorio.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      User exits = serviceUser.findByEmail(email);
      if (exits == null) {
        GeneralResponse<List<Bank>> response = GeneralResponse
            .error("No se encuentra usuario con el email consultado.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      List<Bank> listFindByIdUser = service.findByIdUser(exits.getId());
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
