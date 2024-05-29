package com.livethegame.CreateTicket.repository;

import com.livethegame.CreateTicket.entities.PriceStages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PriceStagesRepository extends JpaRepository<PriceStages, Long> {
 //   List<PriceStages> findByServiceIdValue(Long serviceIdValue);
}
