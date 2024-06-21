package com.project.web_library.repository;

import com.project.web_library.model.data.user.AuthUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AuthUserRepository extends MongoRepository<AuthUser, String> {



    Optional<AuthUser> findAuthUserByEmail(String email);
    Optional<AuthUser> findAuthUserByUsername(String username);

    Optional<List<AuthUser>> findAllById(String id);

    Optional<AuthUser> findAuthUserById(String id);







}