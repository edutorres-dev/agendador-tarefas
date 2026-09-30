
package com.javanauta.agendadortarefas.business.mapper;

import com.javanauta.agendadortarefas.business.dto.TarefasDTO;
import com.javanauta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

// Mapper responsável por converter DTO em Entity e Entity em DTO.
@Mapper(componentModel = "spring")
public interface TarefasConverter {

    // Converte os dados do DTO para uma Entity.
    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    // Converte os dados da Entity para um DTO.
    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}

