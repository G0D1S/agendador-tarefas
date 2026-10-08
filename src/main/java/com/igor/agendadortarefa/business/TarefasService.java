package com.igor.agendadortarefa.business;

import com.igor.agendadortarefa.business.dto.TarefasDTO;
import com.igor.agendadortarefa.business.mapper.TarefaUpdateConverter;
import com.igor.agendadortarefa.business.mapper.TarefasConverter;
import com.igor.agendadortarefa.infrastructure.entity.TarefasEntity;
import com.igor.agendadortarefa.infrastructure.enums.StatusNotificacaoEnum;
import com.igor.agendadortarefa.infrastructure.exeception.ResourceNotFoundExeception;
import com.igor.agendadortarefa.infrastructure.repository.TarefasRepository;
import com.igor.agendadortarefa.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository; //isso é a injecao de depencia, recebe os objetos da TarefaRepo.
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefasEntity(dto);

        return tarefaConverter.paraTarefasDTO(
                tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {

        String email = jwtUtil.extrairEmailToken(token.substring(7)); // esta extraindo o token do email pelo jwt
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email); // o resultado disso vai ser o "return"

        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletaTarefaPorID(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundExeception e) {
            throw new ResourceNotFoundExeception("Error ao deletar a tarefa por ID, ID nao existente" + id,
                    e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundExeception("Tarefa nao encontrada " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefasDTO(tarefasRepository.save(entity));
        }catch (ResourceNotFoundExeception e) {
            throw new ResourceNotFoundExeception("Error ao alterar o status da tarefa" + e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id){
        try{
        TarefasEntity entity = tarefasRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundExeception("Tarefa nao encontrada " + id));

        tarefaUpdateConverter.updateTarefas(dto, entity);
        return tarefaConverter.paraTarefasDTO(tarefasRepository.save(entity));
    }catch (ResourceNotFoundExeception e) {
            throw new ResourceNotFoundExeception("Error ao alterar o status da tarefa" + e.getCause());
        }

    }

}
