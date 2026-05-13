package com.emergencias.model;

import java.util.PriorityQueue;

public class ColaPrioridad {

    private final PriorityQueue<Paciente> cola;

    public ColaPrioridad() {
        this.cola = new PriorityQueue<>();
    }

    public void agregarPaciente(Paciente paciente) {
        cola.offer(paciente);
    }

    public Paciente atenderSiguiente() {
        return cola.poll(); // saca el de mayor prioridad
    }

    public Paciente verSiguiente() {
        return cola.peek(); // solo mira sin sacar
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int tamanio() {
        return cola.size();
    }

    public PriorityQueue<Paciente> getCola() {
        return cola;
    }
}