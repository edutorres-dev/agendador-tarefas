
package com.javanauta.agendadortarefas.business.mapper;

import com.javanauta.agendadortarefas.business.dto.TarefasDTO;
import com.javanauta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// Mapper responsável por converter DTO em Entity e Entity em DTO.
@Mapper(componentModel = "spring")
public interface TarefasConverter {

    // Converte os dados do DTO para uma Entity.
    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    // Converte os dados da Entity para um DTO.
    TarefasDTO paraTarefaDTO(TarefasEntity entity);

    // Converte lista de tarefas DTO para uma de Entities.
    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dto);

    // Converte uma lista de tarefa Entities para uma de DTOs.
    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);


}

