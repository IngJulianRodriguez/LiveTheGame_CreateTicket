package com.livethegame.CreateTicket.controller;

import com.livethegame.CreateTicket.dto.TicketRequest;
import com.livethegame.CreateTicket.dto.TicketResponse;
import com.livethegame.CreateTicket.exception.*;
import com.livethegame.CreateTicket.services.MonitoringService;
import com.livethegame.CreateTicket.services.TicketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Api Create Ticket")
@RestController
@RequestMapping("/tickets")
public class CreateTicketRestController {

    @Autowired
    TicketService ticketService;
    @Autowired
    MonitoringService monitoringService;

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(@RequestBody TicketRequest input) {
        try {
            TicketResponse ticketResponse = ticketService.createTicket(input);
            monitoringService.registerSuccessLog(String.valueOf(ticketResponse.getId()),"/create "+input.toString()+" "+ticketResponse);
            return ResponseEntity.ok(ticketResponse);
        } catch (UserNotFoundException
                 | ServiceNotFoundException
                 | TournamentNotFoundException
                 | BroadcastNotFoundException
                 | IllegalArgumentException
                 | IllegalStateException
                 | ParamsNotFoundException e) {
            monitoringService.registerControlledExceptionLog("","/create "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            monitoringService.registerNotControlledExceptionLog("","/create "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
    @GetMapping("/test-create")
    public ResponseEntity<?> testCreateTicket(){
        return ResponseEntity.ok().build();
    }
}
