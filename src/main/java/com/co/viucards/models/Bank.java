package com.co.viucards.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bank")
public class Bank {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String description;

  @Column(columnDefinition = "LONGBLOB")
  private byte[] image;

  private Integer state;

  @Column(name = "id_languaje")
  private Integer idLanguaje;

  @Column(name = "id_user")
  private Integer idUser;

}
