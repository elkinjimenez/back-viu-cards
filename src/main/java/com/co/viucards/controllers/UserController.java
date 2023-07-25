package com.co.viucards.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.viucards.dto.UserDTO;
import com.co.viucards.models.User;
import com.co.viucards.services.UserService;
import com.co.viucards.utils.GeneralResponse;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping("list")
  public ResponseEntity<GeneralResponse<List<UserDTO>>> getList() {
    List<User> userList = service.list();
    List<UserDTO> userDTOList = service.convertToDTO(userList);
    GeneralResponse<List<UserDTO>> response = GeneralResponse.success(userDTOList);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("create")
  public ResponseEntity<GeneralResponse<UserDTO>> createUser(@RequestBody(required = false) User user) {
    try {
      if (user == null || user.getEmail() == null || user.getPassword() == null) {
        GeneralResponse<UserDTO> errorResponse = new GeneralResponse<>();
        errorResponse.setMessage("Campos incompletos. Por favor validar.");
        errorResponse.setCode(400);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
      }
      User exits = service.findByEmail(user.getEmail());
      if (exits != null) {
        GeneralResponse<UserDTO> errorResponse = new GeneralResponse<>();
        errorResponse.setMessage("El usuario ya se encuentra registrado.");
        errorResponse.setCode(400);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
      }
      User newUser = service.create(user);
      UserDTO createdUserDTO = service.convertToDTO(newUser);
      GeneralResponse<UserDTO> successResponse = GeneralResponse.success(createdUserDTO);
      return new ResponseEntity<>(successResponse, HttpStatus.OK);
    } catch (Exception e) {
      GeneralResponse<UserDTO> errorResponse = new GeneralResponse<>();
      errorResponse.setMessage("Error: " + e.getMessage());
      errorResponse.setCode(400);
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
  }

  @PutMapping("login")
  public ResponseEntity<GeneralResponse<UserDTO>> login(@RequestBody(required = false) User user) {
    try {
      if (user == null || user.getEmail() == null || user.getPassword() == null) {
        GeneralResponse<UserDTO> errorResponse = new GeneralResponse<>();
        errorResponse.setMessage("Campos incompletos. Por favor validar.");
        errorResponse.setCode(400);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
      }
      User userLogin = service.login(user);
      if (userLogin == null) {
        GeneralResponse<UserDTO> errorResponse = new GeneralResponse<>();
        errorResponse.setMessage("Usuario y/o clave incorrectos. Por favor intente de nuevo.");
        errorResponse.setCode(400);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
      }
      UserDTO createdUserDTO = service.convertToDTO(userLogin);
      GeneralResponse<UserDTO> successResponse = GeneralResponse.success(createdUserDTO);
      return new ResponseEntity<>(successResponse, HttpStatus.OK);
    } catch (Exception e) {
      GeneralResponse<UserDTO> errorResponse = new GeneralResponse<>();
      errorResponse.setMessage("Error: " + e.getMessage());
      errorResponse.setCode(400);
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
  }

}
