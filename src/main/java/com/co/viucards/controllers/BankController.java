package com.co.viucards.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping("findByEmailUserAndName")
  public ResponseEntity<GeneralResponse<List<Bank>>> findByEmailUserAndName(
      @RequestParam(name = "email", required = false) String email,
      @RequestParam(name = "name", required = false) String name) {
    try {
      if (email == null) {
        GeneralResponse<List<Bank>> response = GeneralResponse.error("El campo email es obligatorio.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      if (name == null || name == "") {
        GeneralResponse<List<Bank>> response = GeneralResponse.error("El campo name es obligatorio.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      User exits = serviceUser.findByEmail(email);
      if (exits == null) {
        GeneralResponse<List<Bank>> response = GeneralResponse
            .error("No se encuentra usuario con el email proporcionado.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      List<Bank> listFindByIdUser = service.findByIdUserAndName(exits.getId(), name);
      GeneralResponse<List<Bank>> response = GeneralResponse.success(listFindByIdUser);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      GeneralResponse<List<Bank>> errorResponse = new GeneralResponse<>();
      errorResponse.setMessage("Error: " + e.getMessage());
      errorResponse.setCode(400);
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
  }

  @PostMapping("create")
  public ResponseEntity<GeneralResponse<Bank>> createUser(@RequestBody(required = false) Bank bank,
      @RequestParam(name = "email", required = false) String email) {
    try {
      if (email == null) {
        GeneralResponse<Bank> response = GeneralResponse.error("El campo email del usuario es obligatorio.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      User exits = serviceUser.findByEmail(email);
      if (exits == null) {
        GeneralResponse<Bank> response = GeneralResponse.error("No se encuentra usuario con el email proporcionado.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      if (bank == null || bank.getName() == null) {
        GeneralResponse<Bank> response = GeneralResponse.error("Campos incompletos. Por favor validar.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      bank.setIdUser(exits.getId());
      Bank newUser = service.create(bank);
      GeneralResponse<Bank> successResponse = GeneralResponse.success(newUser);
      return new ResponseEntity<>(successResponse, HttpStatus.OK);
    } catch (Exception e) {
      GeneralResponse<Bank> errorResponse = new GeneralResponse<>();
      errorResponse.setMessage("Error: " + e.getMessage());
      errorResponse.setCode(400);
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
  }

  @GetMapping("delete")
  public ResponseEntity<GeneralResponse<Boolean>> delete(
      @RequestParam(name = "idBank", required = false) Integer idBank) {
    try {
      if (idBank == null) {
        GeneralResponse<Boolean> response = GeneralResponse.error("El campo idBank es obligatorio.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      Boolean isDeleted = service.deleteById(idBank);
      if (isDeleted) {
        GeneralResponse<Boolean> response = GeneralResponse.success(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        GeneralResponse<Boolean> errorResponse = GeneralResponse
            .error("No se logró eliminar el banco correctamente. Por favor intente de nuevo.");
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
      }
    } catch (Exception e) {
      GeneralResponse<Boolean> errorResponse = GeneralResponse.error("Error: " + e.getMessage());
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
  }

}
