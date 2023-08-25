package com.co.viucards.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

  public Optional<Word> findById(Integer id) {
    return repository.findById(id);
  }

  public Word create(Word word) {
    word.setDate(new Date());
    return repository.saveAndFlush(word);
  }

  public Word update(Word word) {
    return this.repository.saveAndFlush(word);
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
