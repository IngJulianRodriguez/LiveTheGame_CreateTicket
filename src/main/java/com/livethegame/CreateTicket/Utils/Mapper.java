package com.livethegame.CreateTicket.Utils;

import com.livethegame.CreateTicket.dto.TicketRequest;
import com.livethegame.CreateTicket.entities.*;

public  class Mapper {
    public static Tickets TicketRequestToTicket(TicketRequest source, Users user, Long idServicio, Services service, TicketsStages ticketsStages, long stagePrice){
        Tickets ticket = new Tickets();
        ticket.setUser(user);
        ticket.setService_id_value(idServicio);
        ticket.setService(service);
        ticket.setTicket_stage(ticketsStages);
        ticket.setPrice_ticket(stagePrice);
        return ticket;
    };
}
