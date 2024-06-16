package com.library.LibraryManagement.repository;

import com.library.LibraryManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInterfaceRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
