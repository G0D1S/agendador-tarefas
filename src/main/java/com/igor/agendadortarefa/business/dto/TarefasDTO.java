package com.igor.agendadortarefa.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.igor.agendadortarefa.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTO {
    private String id;
    private String nomeTrefa;
    private String descricao;
    private LocalDateTime dataCriacao; // data e hora // localDate é apenas data
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
