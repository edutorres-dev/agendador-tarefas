package com.javanauta.agendadortarefas.business;

import com.javanauta.agendadortarefas.business.dto.TarefasDTO;
import com.javanauta.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.javanauta.agendadortarefas.business.mapper.TarefasConverter;
import com.javanauta.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.javanauta.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.javanauta.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto) {

        // Extrai o e-mail do usuário autenticado através do token JWT.
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        // Define no DTO o e-mail do usuário que criou a tarefa.
        dto.setEmailUsuario(email);

        // Define a data e hora atual como data de criação da tarefa.
        dto.setDataCriacao(LocalDateTime.now());

        // Define a tarefa inicialmente como pendente de notificação.
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);

        // Converte o DTO recebido para uma Entity antes de salvar no banco.
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

        // Salva a tarefa no banco e converte a Entity salva novamente para DTO.
        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }




    // Busca as tarefas agendadas dentro do período informado.
    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(
            LocalDateTime dataInicial,
            LocalDateTime dataFinal) {

        // Busca no banco e converte a lista de Entity para DTO.
        return tarefaConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal)
        );
    }




    // Busca as tarefas pertencentes ao usuário autenticado.
    public List<TarefasDTO> buscaTarefasPorEmail(String token) {

        // Extrai o e-mail do usuário através do token JWT.
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        // Busca no banco todas as tarefas vinculadas ao e-mail.
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        // Converte a lista de Entity para DTO antes de retornar.
        return tarefaConverter.paraListaTarefasDTO(listaTarefas);
    }



    public void deleteTarefaPorId(String id){
        try{
            tarefasRepository.deleteById(id);

        }catch ( ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id , id inexistente"
                    +id,e.getCause());
        }




    }





    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            //busca tarefa por id
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException
                    ("Tarefa não encontrada" + id));
            entity.setStatusNotificacaoEnum(status);
           return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

        }catch (ResourceNotFoundException e){
            throw  new ResourceNotFoundException("Erro ao alterar status da tarefa" + e.getCause());
        }
    }



    public TarefasDTO updateTarefas(TarefasDTO dto , String id){

        try{

            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException
                    ("Tarefa não encontrada" + id));
            tarefaUpdateConverter.updateTarefas(dto,entity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));

        }catch (ResourceNotFoundException e){

            throw  new ResourceNotFoundException("Erro ao alterar status da tarefa" + e.getCause());
        }

    }






}

