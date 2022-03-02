package com.nigelxiaido.nigeliaidoperson.repository;


import com.nigelxiaido.nigeliaidoperson.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findUserByName(String name, Pageable page);
    Page<User> findUserByAge(Integer age, Pageable page);
    Page<User> findUserByNameAndAge(String name, Integer age, Pageable page);
}
