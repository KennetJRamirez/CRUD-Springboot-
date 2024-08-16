package com.umg.primerappspringboot.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.umg.primerappspringboot.model.Persona;

@Service
public class PersonaService {
    private final String archivo = "personas.txt";

    public List<Persona> listarPersonas() {
        List<Persona> personas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Persona persona = Persona.fromString(linea);
                personas.add(persona);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personas;
    }

    public Optional<Persona> buscarPersonaPorId(Integer id) {
        return listarPersonas().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Persona> buscarPersonaPorNombre(String nombre) {
        return listarPersonas().stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }

    public void agregarPersona(Persona persona) {
        List<Persona> personas = listarPersonas();
        Integer nextId = personas.stream()
                .max(Comparator.comparingInt(Persona::getId))
                .map(p -> p.getId() + 1)
                .orElse(1);
        persona.setId(nextId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(persona.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPersona(Integer id) {
        List<Persona> personas = listarPersonas();
        personas.removeIf(p -> p.getId().equals(id));
        guardarPersonas(personas);
    }

    public void actualizarPersona(Persona persona) {
        List<Persona> personas = listarPersonas();
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getId().equals(persona.getId())) {
                personas.set(i, persona);
                guardarPersonas(personas);
                return;
            }
        }
    }

    private void guardarPersonas(List<Persona> personas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Persona persona : personas) {
                writer.write(persona.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
