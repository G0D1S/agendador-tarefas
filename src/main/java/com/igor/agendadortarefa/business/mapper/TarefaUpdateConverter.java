package com.igor.agendadortarefa.business.mapper;

import com.igor.agendadortarefa.business.dto.TarefasDTO;
import com.igor.agendadortarefa.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface TarefaUpdateConverter {

    void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity); // @mapping target é o principal caso tarefaDTO for null

}
