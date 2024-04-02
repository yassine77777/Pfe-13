package com.pfe13.dao;

import com.pfe13.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {

    @Query("select case when count(u)>0 then true else false END from User u where u.email=?1")
    Boolean checkIfEmailExists(String email);

    @Query(value = "select * from users where email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String name);

    Optional<User> findOneByEmailAndPassword(String email, String password);
    User searchByEmail(String email);




}
