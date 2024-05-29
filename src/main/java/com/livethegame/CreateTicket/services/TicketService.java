package com.livethegame.CreateTicket.services;

import com.livethegame.CreateTicket.Utils.Mapper;
import com.livethegame.CreateTicket.common.TicketResponseMapper;
import com.livethegame.CreateTicket.dto.TicketRequest;
import com.livethegame.CreateTicket.dto.TicketResponse;
import com.livethegame.CreateTicket.entities.*;
import com.livethegame.CreateTicket.exception.*;
import com.livethegame.CreateTicket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    BroadcastRepository broadcastRepository;
    @Autowired
    PriceStagesRepository priceStagesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    TicketsStagesRepository ticketsStageRepository;
    @Autowired
    ParamsRepository paramsRepository;
    @Autowired
    TicketResponseMapper ticketResponseMapper;


    public TicketResponse createTicket(TicketRequest ticketRequest){
        Optional<Users> optionalUser = userRepository.findById(ticketRequest.getUser_id());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Usuario no encontrado con ID: " + ticketRequest.getUser_id());
        }
        Optional<Services> optionalService = serviceRepository.findById(ticketRequest.getService_id());
        if (optionalService.isEmpty()) {
            throw new ServiceNotFoundException("Servicio no encontrado con ID: " + ticketRequest.getService_id());
        }
        Optional<Params> optionalParamsPlayTournament = paramsRepository.findByName("services.playTournament.id");
        if (optionalParamsPlayTournament.isEmpty()) {
            throw new ParamsNotFoundException("Parametro services.playTournament.id no encontrado");
        }
        Optional<Params> optionalParamsWatchTournament = paramsRepository.findByName("services.watchTournament.id");
        if (optionalParamsWatchTournament.isEmpty()) {
            throw new ParamsNotFoundException("Parametro services.watchTournament.id no encontrado");
        }
        if (ticketRequest.getService_id() == optionalParamsPlayTournament.get().getValueAsLong()) {
            Optional<Tournaments> optionalTournament = tournamentRepository.findById(ticketRequest.getService_id_value());
            if (optionalTournament.isEmpty()) {
                throw new TournamentNotFoundException("Torneo no encontrado con ID: " + ticketRequest.getService_id_value());
            }
        } else if (ticketRequest.getService_id() == optionalParamsWatchTournament.get().getValueAsLong()) {
            Optional<Broadcasts> optionalBroadcast = broadcastRepository.findById(ticketRequest.getService_id_value());
            if (optionalBroadcast.isEmpty()) {
                throw new BroadcastNotFoundException("Transmision no encontrada con ID: " + ticketRequest.getService_id_value());
            }
        } else {
            throw new IllegalArgumentException("Servicio no valido con ID: " + ticketRequest.getService_id());
        }
        Optional<TicketsStages> optionalTicketStage = ticketsStageRepository.findById(1L);
        if (optionalTicketStage.isEmpty()) {
            throw new TicketStageNotFoundException("Etapa de servicio no encontrado con ID: 1");
        }
        Long stagePrice = getStagePrice(ticketRequest.getService_id(),ticketRequest.getService_id_value());
        Tickets ticketRequestToTicket = Mapper.TicketRequestToTicket(ticketRequest,
                optionalUser.get(),
                ticketRequest.getService_id_value(),
                optionalService.get(),
                optionalTicketStage.get(),
                stagePrice);
        Tickets savedTicket = ticketRepository.save(ticketRequestToTicket);
        return ticketResponseMapper.ticketToTicketResponse(savedTicket);

    }

    public long getStagePrice(Long idServicio, Long id) {

        List<PriceStages> priceStagesList = priceStagesRepository.findAll();
        LocalDate today = LocalDate.now();
        for (PriceStages stage : priceStagesList) {
            if (stage.getService().getId() == idServicio
                    && stage.getService_id_value() == id
                    && today.isAfter(stage.getStarts_at())
                    && today.isBefore(stage.getFinish_at())) {
                return stage.getPrice();
            }
        }
        return 0L;
    }

}
