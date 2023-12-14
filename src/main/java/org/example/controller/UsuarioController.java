package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.UsuarioDTOInput;
import org.example.service.UsuarioService;

import static spark.Spark.*;

public class UsuarioController {
    private final ObjectMapper objMapper = new ObjectMapper();
    UsuarioService usuarioService;


    public UsuarioController() {
        this.usuarioService = new UsuarioService();

    }

    public void respostasRequisicoes() {
        get("/usuarios", (request, response) -> {
            response.type("application/json");
            response.status(200);
            String json = objMapper.writeValueAsString(usuarioService.listarUsuarios());
            return json;
        });

        get("/usuarios/:id", (request, response) -> {
            response.type("application/json");
            StringBuilder idParam = new StringBuilder(request.params("id"));
            idParam.delete(0, 1);
            int id = Integer.parseInt(idParam.toString());

            String json = objMapper.writeValueAsString(usuarioService.buscarUsuario(id));
            response.status(200);
            return json;
        });

        delete("/usuarios/:id", (request, response) -> {
            response.type("application/json");

            // arrumando id
            StringBuilder idParam = new StringBuilder(request.params("id"));
            idParam.delete(0, 1);
            int id = Integer.parseInt(idParam.toString());
            // -------------------------------------------------------- //
            String nome = usuarioService.buscarUsuario(id).getNome();
            usuarioService.deletarUsuario(id);
            response.status(200);
            return "O nome do usuário excluido com sucesso é " + nome;
        });

        post("/usuarios", (request, response) -> {
            UsuarioDTOInput usuarioInput = objMapper.readValue(request.body(), UsuarioDTOInput.class);
            usuarioService.inserirUsuario(usuarioInput);
            response.type("application/json");
            response.status(201);
            return "Usuário de id " + usuarioInput.getId() + " inserido com sucesso!";
        });

        put("/usuarios", (request, response) -> {
            UsuarioDTOInput usuarioInput = objMapper.readValue(request.body(), UsuarioDTOInput.class);
            usuarioService.alterarUsuario(usuarioInput);
            response.type("application/json");
            response.status(200);
            return "O usuário foi atualizado com sucesso!";
        });
    }


}
