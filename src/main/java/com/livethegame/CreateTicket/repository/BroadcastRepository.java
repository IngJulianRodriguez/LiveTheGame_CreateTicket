package com.livethegame.CreateTicket.repository;

import com.livethegame.CreateTicket.entities.Broadcasts;
import com.livethegame.CreateTicket.entities.Tournaments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BroadcastRepository extends JpaRepository<Broadcasts, Long> {
}
