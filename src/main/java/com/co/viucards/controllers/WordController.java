package com.co.viucards.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.viucards.dto.WordDTO;
import com.co.viucards.models.Word;
import com.co.viucards.services.WordService;
import com.co.viucards.utils.GeneralResponse;

@RestController
@RequestMapping("api/word")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WordController {

  @Autowired
  private WordService service;

  @GetMapping("findByIdBank")
  public ResponseEntity<GeneralResponse<List<Word>>> findByIdBank(
      @RequestParam(name = "idBank", required = false) Integer idBank) {
    try {
      if (idBank == null) {
        GeneralResponse<List<Word>> response = GeneralResponse.error("El campo idBank es obligatorio.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      List<Word> listFindByIdBank = service.findByIdBank(idBank);
      GeneralResponse<List<Word>> response = GeneralResponse.success(listFindByIdBank);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      GeneralResponse<List<Word>> errorResponse = GeneralResponse.error("Error: " + e.getMessage());
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
  }

  @GetMapping("findByIdBankOnlyRetentionLevel")
  public ResponseEntity<GeneralResponse<List<WordDTO>>> findByIdBankOnlyRetentionLevel(
      @RequestParam(name = "idBank", required = false) Integer idBank) {
    try {
      if (idBank == null) {
        GeneralResponse<List<WordDTO>> response = GeneralResponse.error("El campo idBank es obligatorio.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      List<Word> listFindByIdBank = service.findByIdBank(idBank);
      List<WordDTO> listOnlyRetentionLevel = new ArrayList<>();
      for (Word word : listFindByIdBank) {
        listOnlyRetentionLevel.add(new WordDTO(word.getRetentionLevel()));
      }
      GeneralResponse<List<WordDTO>> response = GeneralResponse.success(listOnlyRetentionLevel);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      GeneralResponse<List<WordDTO>> errorResponse = GeneralResponse.error("Error: " + e.getMessage());
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
  }

  @PostMapping("create")
  public ResponseEntity<GeneralResponse<Word>> create(@RequestBody(required = false) Word word) {
    try {
      if (word == null || word.getText() == null) {
        GeneralResponse<Word> response = GeneralResponse.error("Campos incompletos. Por favor validar.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      Word newObject = service.create(word);
      GeneralResponse<Word> successResponse = GeneralResponse.success(newObject);
      return new ResponseEntity<>(successResponse, HttpStatus.OK);
    } catch (Exception e) {
      GeneralResponse<Word> errorResponse = new GeneralResponse<>();
      errorResponse.setMessage("Error: " + e.getMessage());
      errorResponse.setCode(400);
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
  }

  @PutMapping("update")
  public ResponseEntity<GeneralResponse<Word>> update(@RequestBody(required = false) Word word) {
    try {
      if (word == null || word.getRetentionLevel() == null || word.getId() == null) {
        GeneralResponse<Word> response = GeneralResponse.error("Campos incompletos. Por favor validar.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      Optional<Word> exitsWord = service.findById(word.getId());
      if (!exitsWord.isPresent()) {
        GeneralResponse<Word> response = GeneralResponse
            .error("No se encontró la palabra con el ID indicado. Por favor validar.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      Word newObject = service.update(word);
      GeneralResponse<Word> successResponse = GeneralResponse.success(newObject);
      return new ResponseEntity<>(successResponse, HttpStatus.OK);
    } catch (Exception e) {
      GeneralResponse<Word> errorResponse = new GeneralResponse<>();
      errorResponse.setMessage("Error: " + e.getMessage());
      errorResponse.setCode(400);
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
  }

  @GetMapping("delete")
  public ResponseEntity<GeneralResponse<Boolean>> delete(
      @RequestParam(name = "idWord", required = false) Integer idWord) {
    try {
      if (idWord == null) {
        GeneralResponse<Boolean> response = GeneralResponse.error("El campo idWord es obligatorio.");
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
      Boolean isDeleted = service.deleteById(idWord);
      if (isDeleted) {
        GeneralResponse<Boolean> response = GeneralResponse.success(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        GeneralResponse<Boolean> errorResponse = GeneralResponse
            .error("No se logró eliminar la tarjeta correctamente. Por favor intente de nuevo.");
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
      }
    } catch (Exception e) {
      GeneralResponse<Boolean> errorResponse = GeneralResponse.error("Error: " + e.getMessage());
      return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
  }

}
