package com.co.viucards.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordDTO {
  private Integer retentionLevel;

  public WordDTO(Integer retentionLevel) {
    this.retentionLevel = retentionLevel;
  }
}
