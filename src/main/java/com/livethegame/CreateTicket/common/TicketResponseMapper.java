package com.livethegame.CreateTicket.common;

import com.livethegame.CreateTicket.dto.TicketResponse;
import com.livethegame.CreateTicket.entities.Tickets;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TicketResponseMapper {
    TicketResponse ticketToTicketResponse(Tickets source);
}
