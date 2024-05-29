package com.livethegame.CreateTicket.repository;

import com.livethegame.CreateTicket.entities.Tournaments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournaments, Long> {
}
