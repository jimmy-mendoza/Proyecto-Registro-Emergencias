package com.emergencias.database;

import com.emergencias.model.Paciente;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // Insertar nuevo paciente
    public void insertar(Paciente paciente) {
        String sql = "INSERT INTO pacientes (nombre_completo, edad, dpi, sintomas, nivel_prioridad, hora_ingreso, atendido) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombreCompleto());
            ps.setInt(2, paciente.getEdad());
            ps.setString(3, paciente.getDpi());
            ps.setString(4, paciente.getSintomas());
            ps.setString(5, paciente.getNivelPrioridad());
            ps.setTimestamp(6, Timestamp.valueOf(paciente.getHoraIngreso()));
            ps.setBoolean(7, false);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener todos los pacientes en espera (no atendidos)
    public List<Paciente> obtenerEnEspera() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE atendido = FALSE ORDER BY nivel_prioridad, hora_ingreso";

        try (Connection con = DatabaseConnection.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente(
                        rs.getString("nombre_completo"),
                        rs.getInt("edad"),
                        rs.getString("dpi"),
                        rs.getString("sintomas"),
                        rs.getString("nivel_prioridad")
                );
                p.setHoraIngreso(rs.getTimestamp("hora_ingreso").toLocalDateTime());
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Marcar paciente como atendido
    public void marcarAtendido(String dpi) {
        String sql = "UPDATE pacientes SET atendido = TRUE WHERE dpi = ?";

        try (Connection con = DatabaseConnection.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dpi);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}