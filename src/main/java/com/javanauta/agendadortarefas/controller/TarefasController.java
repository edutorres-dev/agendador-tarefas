package com.javanauta.agendadortarefas.controller;

import com.javanauta.agendadortarefas.business.TarefasService;
import com.javanauta.agendadortarefas.business.dto.TarefasDTO;
import com.javanauta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    // Injeta o Service responsável pelas regras de negócio das tarefas.
    private final TarefasService tarefasService;

    // Endpoint responsável por cadastrar uma nova tarefa.
    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(
            @RequestBody TarefasDTO dto,
            @RequestHeader("Authorization") String token) {

        // Envia o DTO e o token para o Service realizar o cadastro.
        return ResponseEntity.ok(
                tarefasService.gravarTarefa(token, dto)
        );
    }


    // Busca as tarefas agendadas dentro do período informado.
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefasPorPeriodo(

            // Recebe a data e hora inicial no formato ISO.
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicial,

            // Recebe a data e hora final no formato ISO.
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataFinal) {

        // Envia as datas para o Service e retorna as tarefas encontradas.
        return ResponseEntity.ok(
                tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal)
        );
    }


    // Busca todas as tarefas vinculadas ao usuário autenticado.
    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(
            @RequestHeader("Authorization") String token) {

        // Envia o token para o Service identificar o usuário e buscar suas tarefas.
        List<TarefasDTO> tarefas = tarefasService.buscaTarefasPorEmail(token);

        return ResponseEntity.ok(tarefas);
    }



    // Endpoint responsável por excluir uma tarefa pelo ID.
    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(
            @RequestParam("id") String id) {

        // Envia o ID para o Service realizar a exclusão.
        tarefasService.deleteTarefaPorId(id);

        return ResponseEntity.ok().build();
    }


    // Endpoint responsável por alterar o status da notificação.
    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id) {

        // Envia o status e o ID para o Service realizar a alteração.
        return ResponseEntity.ok(
                tarefasService.alteraStatus(status, id)
        );
    }


    // Endpoint responsável por atualizar os dados da tarefa.
    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefas(
            @RequestBody TarefasDTO dto,
            @RequestParam("id") String id) {

        // Envia o DTO e o ID para o Service atualizar a tarefa.
        return ResponseEntity.ok(
                tarefasService.updateTarefas(dto, id)
        );
    }





}

