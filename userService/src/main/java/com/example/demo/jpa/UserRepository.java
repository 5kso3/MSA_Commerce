package com.example.demo.jpa;

import lombok.Data;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(String userId);
}
