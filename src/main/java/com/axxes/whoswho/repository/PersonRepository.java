package com.axxes.whoswho.repository;


import com.axxes.whoswho.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    //List<Person> findAllByIdIsNotAndId(long id);
}
