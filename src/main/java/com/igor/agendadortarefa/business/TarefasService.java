package com.igor.agendadortarefa.business;

import com.igor.agendadortarefa.business.dto.TarefasDTO;
import com.igor.agendadortarefa.business.mapper.TarefasConverter;
import com.igor.agendadortarefa.infrastructure.entity.TarefasEntity;
import com.igor.agendadortarefa.infrastructure.enums.StatusNotificacaoEnum;
import com.igor.agendadortarefa.infrastructure.repository.TarefasRepository;
import com.igor.agendadortarefa.infrastructure.security.JwtUtil;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository; //isso é a injecao de depencia, recebe os objetos da TarefaRepo.
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefasEntity(dto);

        return tarefaConverter.paraTarefasDTO(
                tarefasRepository.save(entity));
    }
}
