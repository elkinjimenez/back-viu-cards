package com.co.viucards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.viucards.models.Word;
import java.util.List;

public interface WordRepository extends JpaRepository<Word, Integer> {

  List<Word> findByIdBank(Integer idBank);

}
