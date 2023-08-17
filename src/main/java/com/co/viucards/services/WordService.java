package com.co.viucards.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.viucards.models.Word;
import com.co.viucards.repositories.WordRepository;

@Service
public class WordService {

  @Autowired
  private WordRepository repository;

  public List<Word> findByIdBank(Integer idBank) {
    return repository.findByIdBank(idBank);
  }

  public Word create(Word word) {
    word.setDate(new Date());
    return repository.save(word);
  }

  public Boolean deleteById(Integer idWord) {
    try {
      repository.deleteById(idWord);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
