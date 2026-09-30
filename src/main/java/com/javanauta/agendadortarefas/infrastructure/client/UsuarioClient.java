package com.javanauta.agendadortarefas.infrastructure.client;

import com.javanauta.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

// Cliente responsável por realizar chamadas para o serviço de usuários.
@FeignClient(name = "usuario",url="${usuario.url}")
public interface UsuarioClient {

    // Busca os dados de um usuário através do e-mail informado.
    @GetMapping("/usuario")
    UsuarioDTO buscarUsuarioPorEmail

    (@RequestParam("email") String email ,

     // Envia o token JWT para autenticar a requisição no serviço de usuários.
     @RequestHeader("Authorization") String token);

}
