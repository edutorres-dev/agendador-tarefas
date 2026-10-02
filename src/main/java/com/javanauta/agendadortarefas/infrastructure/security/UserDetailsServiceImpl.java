package com.javanauta.agendadortarefas.infrastructure.security;

import com.javanauta.agendadortarefas.business.dto.UsuarioDTO;
import com.javanauta.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/*
 * Serviço responsável por buscar os dados do usuário no serviço de usuários.
 * O e-mail é utilizado como username durante a autenticação.
 */
@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;

    public UserDetails carregaDadosUsuario(String email, String token) {

        // Busca os dados do usuário através do e-mail e do token JWT.
        UsuarioDTO usuarioDTO = client.buscarUsuarioPorEmail(email, token);

        // Converte os dados recebidos para o formato esperado pelo Spring Security.
        return User
                .withUsername(usuarioDTO.getEmail())

                // Define a senha armazenada para validação durante a autenticação.
                .password(usuarioDTO.getSenha())

                // Cria o objeto UserDetails que será utilizado pelo Spring Security.
                .build();
    }
}

