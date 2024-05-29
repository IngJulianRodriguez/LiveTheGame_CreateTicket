package com.livethegame.CreateTicket.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel()
public class TicketRequest {


    @ApiModelProperty(name = "id del usuario", required = true, example = "", value = "")
    private long user_id;
    @ApiModelProperty(name = "id del torneo o transmision", required = true, example = "", value = "")
    private long service_id_value;
    @ApiModelProperty(name = "id del servicio", required = true, example = "", value = "")
    private long service_id;


    public TicketRequest() {
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getService_id_value() {
        return service_id_value;
    }

    public void setService_id_value(long service_id_value) {
        this.service_id_value = service_id_value;
    }

    public long getService_id() {
        return service_id;
    }

    public void setService_id(long service_id) {
        this.service_id = service_id;
    }
}
