package com.library.LibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.LibraryManagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
