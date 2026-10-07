package com.igor.agendadortarefa.business.mapper;

import com.igor.agendadortarefa.business.dto.TarefasDTO;
import com.igor.agendadortarefa.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity (TarefasDTO dto);
    TarefasDTO paraTarefasDTO (TarefasEntity entity);

}
