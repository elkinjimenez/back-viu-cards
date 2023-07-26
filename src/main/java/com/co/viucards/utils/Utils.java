package com.co.viucards.utils;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

public class Utils {

  public static <T, D> D convertToDTO(T source, Class<D> destinationClass) {
    D dtoInstance = null;
    try {
      dtoInstance = destinationClass.getDeclaredConstructor().newInstance();
      BeanUtils.copyProperties(source, dtoInstance);
    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return dtoInstance;
  }

}
