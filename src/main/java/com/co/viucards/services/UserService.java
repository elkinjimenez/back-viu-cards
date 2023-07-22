package com.co.viucards.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.viucards.dto.UserDTO;
import com.co.viucards.models.User;
import com.co.viucards.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public List<User> list() {
    return repository.findAll();
  }

  public User create(User user) {
    user.setDate(new Date());
    user.setState(1);
    return repository.save(user);
  }

  public User login(User user) {
    return repository.findByEmailAndPassword(user.getEmail(), user.getPassword());
  }

  // DIFERENTES AL CRUD

  public User findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public UserDTO convertToDTO(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setFirstName(user.getFirstName());
    userDTO.setLastName(user.getLastName());
    userDTO.setImage(user.getImage());
    userDTO.setEmail(user.getEmail());
    userDTO.setState(user.getState());
    userDTO.setDate(user.getDate());
    return userDTO;
  }

  public List<UserDTO> convertToDTO(List<User> userList) {
    List<UserDTO> userDTOList = new ArrayList<>();
    for (User user : userList) {
      userDTOList.add(convertToDTO(user));
    }
    return userDTOList;
  }

}
