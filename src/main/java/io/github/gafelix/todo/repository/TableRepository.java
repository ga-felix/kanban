package io.github.gafelix.todo.repository;

import io.github.gafelix.todo.model.Table;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends MongoRepository<Table, String>{
}