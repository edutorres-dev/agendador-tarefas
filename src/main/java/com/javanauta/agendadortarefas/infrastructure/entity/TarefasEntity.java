package com.javanauta.agendadortarefas.infrastructure.entity;

import com.javanauta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

// Define "tarefas" como a coleção onde os documentos serão armazenados.
// é a "tabela" lá do mongo
@Document("tarefas")
public class TarefasEntity {

    // Identificador único do documento no MongoDB.
    @Id
    private String id;

    // Nome da tarefa cadastrada.
    private String nomeTarefa;

    // Descrição da tarefa.
    private String descricao;

    // Data e hora em que a tarefa foi criada.
    private LocalDateTime dataCriacao;

    // Data e hora em que a tarefa deverá acontecer.
    private LocalDateTime dataEvento;

    // E-mail do usuário responsável pela tarefa.
    private String emailUsuario;

    // Data e hora da última alteração da tarefa.
    private LocalDateTime dataAlteracao;

    // Status atual da notificação da tarefa.
    private StatusNotificacaoEnum statusNotificacaoEnum;
}


