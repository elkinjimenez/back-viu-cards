package com.co.viucards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.viucards.models.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Integer> {

}
