package com.maximalus.repository;

import com.maximalus.model.Credential;
import com.maximalus.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByCredential(Credential credential);
}
