package com.javanauta.agendadortarefas.controller;

import com.javanauta.agendadortarefas.business.TarefasService;
import com.javanauta.agendadortarefas.business.dto.TarefasDTO;
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



}

