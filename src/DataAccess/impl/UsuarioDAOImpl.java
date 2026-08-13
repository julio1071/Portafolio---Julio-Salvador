package com.biblioteca.dao.impl;

import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.excepciones.PersistenciaException;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.persistencia.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public void guardar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (id, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)) {
            ps.setString(1, usuario.getId());
            ps.setString(2, usuario.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al guardar el usuario", e);
        }
    }

    @Override
    public Usuario buscarPorId(String id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getString("nombre"), rs.getString("id"));
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al buscar el usuario", e);
        }
        return null;
    }
}
