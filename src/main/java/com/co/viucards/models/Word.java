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
@Table(name = "word")
public class Word {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String text;

  private String meaning;

  @Column(columnDefinition = "LONGBLOB")
  private byte[] image;

  @Column(name = "retention_level")
  private Integer retentionLevel;

  private Date date;

  @Column(name = "id_bank")
  private Integer idBank;

}
