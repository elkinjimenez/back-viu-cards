package com.co.viucards.utils;

import lombok.Getter;
import lombok.Setter;

public class GeneralResponse<T> {

  @Getter
  @Setter
  private String message;

  @Getter
  @Setter
  private int code;

  @Getter
  @Setter
  private T data;

  public static <T> GeneralResponse<T> success(T data) {
    GeneralResponse<T> response = new GeneralResponse<>();
    response.setMessage("OK");
    response.setCode(200);
    response.setData(data);
    return response;
  }

  public static <T> GeneralResponse<T> error(String error) {
    GeneralResponse<T> response = new GeneralResponse<>();
    response.setMessage(error);
    response.setCode(400);
    response.setData(null);
    return response;
  }

}
