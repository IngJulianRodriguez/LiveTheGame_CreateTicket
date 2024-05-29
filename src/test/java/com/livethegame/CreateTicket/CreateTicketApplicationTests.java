package com.livethegame.CreateTicket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livethegame.CreateTicket.common.TicketResponseMapper;
import com.livethegame.CreateTicket.controller.CreateTicketRestController;
import com.livethegame.CreateTicket.dto.TicketRequest;
import com.livethegame.CreateTicket.dto.TicketResponse;
import com.livethegame.CreateTicket.repository.UserRepository;
import com.livethegame.CreateTicket.repository.TicketRepository;
import com.livethegame.CreateTicket.services.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;

@WebMvcTest(CreateTicketRestController.class)
@AutoConfigureMockMvc
class CreateTicketApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketRepository TicketRepository;

    @MockBean
    private UserRepository categoryRepository;

    @MockBean
    private TicketService TicketService;
    @MockBean
    private TicketResponseMapper TicketResponseMapper;

    private static final String PASSWORD = "admin";
    private static final String Ticket = "admin";

    @Test
    public void testCreateTicket_Success() throws Exception {
        TicketRequest request = new TicketRequest();
        TicketResponse response = new TicketResponse();
        response.setId(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/Tickets/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testCreateTicket_Business_UserNotFound() throws Exception {

    }

    @Test
    public void testCreateTicket_Business_ServiceNotFound() throws Exception {

    }
    @Test
    public void testCreateTicket_Business_TicketsStageNotFound() throws Exception {

    }

    @Test
    public void testCreateTicket_Business_WithoutAuthorization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Tickets/create")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void testCreateTicket_Business_testUnauthorizedAccess() throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodingParaUsuarioSinPermiso = encoder.encodeToString(("usuario" + ":" + "password").getBytes());
        mockMvc.perform(MockMvcRequestBuilders.get("/Tickets/create")
                        .header("Authorization", "Basic " + encodingParaUsuarioSinPermiso)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
