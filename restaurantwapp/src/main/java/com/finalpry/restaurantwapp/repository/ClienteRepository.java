package com.finalpry.restaurantwapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.finalpry.restaurantwapp.model.Cliente;


@Repository
public interface ClienteRepository extends MongoRepository<Cliente,String>{

}
