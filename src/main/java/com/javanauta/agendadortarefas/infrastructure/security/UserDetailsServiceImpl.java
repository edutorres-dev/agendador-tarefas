package com.javanauta.agendadortarefas.infrastructure.security;


import com.javanauta.agendadortarefas.business.dto.UsuarioDTO;
import com.javanauta.agendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/*
Service responsável por buscar o usuário no banco durante a autenticação.
Implementa UserDetailsService para informar ao Spring Security
como encontrar um usuário através do seu username.
Neste projeto, o username é o e-mail.
*/

@Service
public class UserDetailsServiceImpl {

    // vamos usar requisição sincrona aqui ( proxima aula)

    @Autowired
    private UsuarioClient client;

    public UserDetails carregaDadosUsuario( String email , String token  ){

        UsuarioDTO usuarioDTO = client.buscarUsuarioPorEmail(email,token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
