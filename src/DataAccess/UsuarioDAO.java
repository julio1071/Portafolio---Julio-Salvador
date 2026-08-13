package com.biblioteca.dao;

import com.biblioteca.modelo.Usuario;

public interface UsuarioDAO {

    void guardar(Usuario usuario);

    Usuario buscarPorId(String id);
}
