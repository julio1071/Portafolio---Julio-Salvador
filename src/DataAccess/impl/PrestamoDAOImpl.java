package com.biblioteca.dao.impl;

import com.biblioteca.dao.PrestamoDAO;
import com.biblioteca.excepciones.PersistenciaException;
import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.persistencia.ConexionBD;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO {

    @Override
    public void guardar(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (isbn, usuario_id, fecha, activo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)) {
            ps.setString(1, prestamo.getLibro().getIsbn());
            ps.setString(2, prestamo.getUsuario().getId());
            ps.setDate(3, Date.valueOf(prestamo.getFecha()));
            ps.setBoolean(4, true);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al guardar el prestamo", e);
        }
    }

    @Override
    public List<Prestamo> listarActivos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT p.fecha, l.isbn, l.titulo, l.autor, l.disponible, "
                + "u.id AS usuario_id, u.nombre AS usuario_nombre "
                + "FROM prestamos p "
                + "JOIN libros l ON p.isbn = l.isbn "
                + "JOIN usuarios u ON p.usuario_id = u.id "
                + "WHERE p.activo = TRUE";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Libro libro = new Libro(rs.getString("titulo"), rs.getString("autor"), rs.getString("isbn"));
                libro.setDisponible(rs.getBoolean("disponible"));
                Usuario usuario = new Usuario(rs.getString("usuario_nombre"), rs.getString("usuario_id"));
                prestamos.add(new Prestamo(libro, usuario, rs.getDate("fecha").toString()));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al listar los prestamos", e);
        }
        return prestamos;
    }

    @Override
    public void finalizar(String isbn) {
        String sql = "UPDATE prestamos SET activo = FALSE WHERE isbn = ? AND activo = TRUE";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)) {
            ps.setString(1, isbn);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al finalizar el prestamo", e);
        }
    }
}
