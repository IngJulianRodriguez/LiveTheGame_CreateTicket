package com.livethegame.CreateTicket.repository;

import com.livethegame.CreateTicket.entities.PriceStages;
import com.livethegame.CreateTicket.entities.TicketsStages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsStagesRepository extends JpaRepository<TicketsStages, Long> {
}
