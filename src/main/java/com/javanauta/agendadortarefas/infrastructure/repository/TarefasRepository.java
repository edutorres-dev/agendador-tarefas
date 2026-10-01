package com.javanauta.agendadortarefas.infrastructure.repository;

import com.javanauta.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

// Repository responsável pelo acesso aos dados das tarefas no MongoDB.
// TarefasEntity → é a Entity que será armazenada no MongoDB.
// String → é o tipo do ID da Entity.
@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity,String> {


    // Busca tarefas cujo evento está dentro do período informado.
    List<TarefasEntity> findByDataEventoBetween(
            LocalDateTime dataInicial,
            LocalDateTime dataFinal
    );

    // Busca todas as tarefas vinculadas ao e-mail do usuário.
    List<TarefasEntity> findByEmailUsuario(String email);



}
