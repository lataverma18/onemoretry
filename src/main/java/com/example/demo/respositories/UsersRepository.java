package com.example.demo.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Users;
@Repository
public interface UsersRepository extends JpaRepository<Users, String>{

}
