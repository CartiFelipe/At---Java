package org.example.service;

import org.example.dto.UsuarioDTOInput;
import org.example.dto.UsuarioDTOOutput;
import org.example.model.Usuario;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsuarioService {
    private final ModelMapper modelMapper = new ModelMapper();
    private final List<Usuario> listaUsuarios = new ArrayList<Usuario>();

    public Collection<UsuarioDTOOutput> listarUsuarios() {
        Collection listaUsuariosDTOOutput = modelMapper.map(listaUsuarios, Collection.class);
        return listaUsuariosDTOOutput;
    }

    public void inserirUsuario(UsuarioDTOInput usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        this.listaUsuarios.add(usuario);
    }

    public void deletarUsuario(int id) {
        this.listaUsuarios.removeIf(usuario -> usuario.getId() == id);
    }

    public void alterarUsuario(UsuarioDTOInput usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        int dtoIndex = usuarioDTO.getId();
        this.listaUsuarios.removeIf(it -> it.getId() == dtoIndex);
        this.listaUsuarios.add(usuario);
    }

    public UsuarioDTOOutput buscarUsuario(int id) {
        Usuario usuario = this.listaUsuarios.stream().filter(it -> it.getId() == id).toList().get(0);
        UsuarioDTOOutput retorno = modelMapper.map(usuario, UsuarioDTOOutput.class);
        return retorno;
    }
}
