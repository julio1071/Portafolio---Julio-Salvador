package com.biblioteca.dao.impl;

import com.biblioteca.dao.LibroDAO;
import com.biblioteca.excepciones.PersistenciaException;
import com.biblioteca.modelo.Libro;
import com.biblioteca.persistencia.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {

    @Override
    public void guardar(Libro libro) {
        String sql = "INSERT INTO libros (isbn, titulo, autor, disponible) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)) {
            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.setBoolean(4, libro.isDisponible());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al guardar el libro", e);
        }
    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        String sql = "SELECT * FROM libros WHERE isbn = ?";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)) {
            ps.setString(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al buscar el libro", e);
        }
        return null;
    }

    @Override
    public List<Libro> listarTodos() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                libros.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al listar los libros", e);
        }
        return libros;
    }

    @Override
    public void actualizarDisponibilidad(Libro libro) {
        String sql = "UPDATE libros SET disponible = ? WHERE isbn = ?";
        try (PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql)) {
            ps.setBoolean(1, libro.isDisponible());
            ps.setString(2, libro.getIsbn());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al actualizar el libro", e);
        }
    }

    private Libro mapear(ResultSet rs) throws SQLException {
        Libro libro = new Libro(rs.getString("titulo"), rs.getString("autor"), rs.getString("isbn"));
        libro.setDisponible(rs.getBoolean("disponible"));
        return libro;
    }
}
