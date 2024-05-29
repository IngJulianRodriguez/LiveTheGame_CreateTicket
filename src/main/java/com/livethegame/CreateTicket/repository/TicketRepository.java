package com.livethegame.CreateTicket.repository;

import com.livethegame.CreateTicket.entities.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Tickets, Long> {
}
