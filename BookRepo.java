package com.java.Phonebook.repo;

import com.java.Phonebook.model.Phonebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRepo extends JpaRepository<Phonebook, Long>{
}
