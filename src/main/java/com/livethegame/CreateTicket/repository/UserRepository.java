package com.livethegame.CreateTicket.repository;

import com.livethegame.CreateTicket.entities.Categories;
import com.livethegame.CreateTicket.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
