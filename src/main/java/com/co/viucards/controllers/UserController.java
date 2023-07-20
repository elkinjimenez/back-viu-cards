package com.co.viucards.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.viucards.models.User;
import com.co.viucards.services.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping("")
  public String main() {
    return "Hola, mi nombre es Elkin J. (Controlador de Usuarios)";
  }

  @GetMapping("list")
  public ResponseEntity<List<User>> getList() {
    return new ResponseEntity<>(service.list(), HttpStatus.OK);
  }

}
