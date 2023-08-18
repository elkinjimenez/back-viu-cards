package com.co.viucards.models;

import java.util.Date;

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
@Table(name = "progress")
public class Progress {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "id_word")
  private Integer idWord;

  @Column(name = "id_user")
  private Integer idUser;

  @Column(name = "retention_level")
  private Integer retentionLevel;

  private Date date;

}
