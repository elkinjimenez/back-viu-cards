package com.co.viucards.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  private String firstName;
  private String lastName;
  private String image;
  private String email;
  private Integer state;
  private Date date;
}
