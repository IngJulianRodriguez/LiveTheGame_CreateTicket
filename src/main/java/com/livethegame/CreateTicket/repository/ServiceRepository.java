package com.livethegame.CreateTicket.repository;

import com.livethegame.CreateTicket.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}
