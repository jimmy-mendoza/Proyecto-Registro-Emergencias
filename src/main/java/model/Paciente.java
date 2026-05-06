package com.emergencias.model;

import java.time.LocalDateTime;

public class Paciente implements Comparable<Paciente> {

    private String nombreCompleto;
    private int edad;
    private String dpi;
    private String sintomas;
    private String nivelPrioridad; // "CRITICAL", "HIGH", "MEDIUM", "LOW"
    private LocalDateTime horaIngreso;

    public Paciente(String nombreCompleto, int edad, String dpi,
                    String sintomas, String nivelPrioridad) {
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.dpi = dpi;
        this.sintomas = sintomas;
        this.nivelPrioridad = nivelPrioridad.toUpperCase();
        this.horaIngreso = LocalDateTime.now();
    }

    public int getValorPrioridad() {
        return switch (nivelPrioridad) {
            case "CRITICAL" -> 1;
            case "HIGH"     -> 2;
            case "MEDIUM"   -> 3;
            case "LOW"      -> 4;
            default         -> 5;
        };
    }

    @Override
    public int compareTo(Paciente otro) {
        int comparacionPrioridad = Integer.compare(
                this.getValorPrioridad(), otro.getValorPrioridad()
        );
        if (comparacionPrioridad != 0) {
            return comparacionPrioridad;
        }
        return this.horaIngreso.compareTo(otro.horaIngreso);
    }

    // Getters
    public String getNombreCompleto() { return nombreCompleto; }
    public int getEdad()              { return edad; }
    public String getDpi()            { return dpi; }
    public String getSintomas()       { return sintomas; }
    public String getNivelPrioridad() { return nivelPrioridad; }
    public LocalDateTime getHoraIngreso() { return horaIngreso; }

    // Setters
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setEdad(int edad)                        { this.edad = edad; }
    public void setDpi(String dpi)                       { this.dpi = dpi; }
    public void setSintomas(String sintomas)             { this.sintomas = sintomas; }
    public void setNivelPrioridad(String nivelPrioridad) { this.nivelPrioridad = nivelPrioridad.toUpperCase(); }
    public void setHoraIngreso(LocalDateTime horaIngreso){ this.horaIngreso = horaIngreso; }

    @Override
    public String toString() {
        return nombreCompleto + " [" + nivelPrioridad + "] - " + horaIngreso;
    }
}