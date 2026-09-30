package com.javanauta.agendadortarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javanauta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTO {

    // Identificador da tarefa.
    private String id;

    // Nome da tarefa cadastrada.
    private String nomeTarefa;

    // Descrição detalhada da tarefa.
    private String descricao;

    // Data e hora em que a tarefa foi criada.
    private LocalDateTime dataCriacao;

    // Define o formato da data ao enviar ou receber o JSON.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;

    // E-mail do usuário responsável pela tarefa.
    private String emailUsuario;

    // Data e hora da última alteração da tarefa.
    private LocalDateTime dataAlteracao;

    // Define o status atual da notificação da tarefa.
    private StatusNotificacaoEnum statusNotificacaoEnum;
}

