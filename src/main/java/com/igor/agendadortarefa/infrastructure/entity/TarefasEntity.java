package com.igor.agendadortarefa.infrastructure.entity;

import com.igor.agendadortarefa.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("tarefa")
public class TarefasEntity {

    @Id
    private String id;
    private String nomeTrefa;
    private String descricao;
    private LocalDateTime dataCriacao; // data e hora // localDate é apenas data
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum; //isso aqui nunca poderar ser mudado, entao ele precisar ser imutavel

}
