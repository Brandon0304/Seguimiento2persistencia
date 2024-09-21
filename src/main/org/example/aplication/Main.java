package org.example.aplication;

import org.example.aplication.service.PacienteService;
import org.example.aplication.service.PacienteServiceImpl;
import org.example.domain.Paciente;
import org.example.infraestructure.repository.PacienteRepositoryImpl;
import org.example.interfaces.PacienteRepository;

import java.util.List;
import javax.swing.JOptionPane;

public class Main {
    private static final PacienteService pacienteService;

    static {
        PacienteRepository pacienteRepository = new PacienteRepositoryImpl();
        pacienteService = new PacienteServiceImpl(pacienteRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            String menu = "MENÚ DE PACIENTES COSMITET:\n"
                    + "1. Registrar un nuevo paciente\n"
                    + "2. Eliminar paciente\n"
                    + "3. Actualizar datos del paciente\n"
                    + "4. Mostrar lista de pacientes registrados\n"
                    + "5. Salir\n"
                    + "Seleccione una opción";

            String opcion = JOptionPane.showInputDialog(menu);

            if (opcion != null) {
                switch (opcion) {
                    case "1":
                        crearPaciente();
                        break;
                    case "2":
                        eliminarPaciente();
                        break;
                    case "3":
                        actualizarPaciente();
                        break;
                    case "4":
                        listarPacientes();
                        break;
                    case "5":
                        salir = true;
                        JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            } else {
                salir = true;
            }
        }
    }

    private static void listarPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
        } else {
            StringBuilder sb = new StringBuilder("Listado de pacientes:\n");
            for (Paciente paciente : pacientes) {
                sb.append(paciente).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static void crearPaciente() {
        try {
            String codStr = JOptionPane.showInputDialog("Ingrese el ID del paciente:");
            int cod = Integer.parseInt(codStr);

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
            String edadStr = JOptionPane.showInputDialog("Ingrese la edad del paciente:");
            int edad = Integer.parseInt(edadStr);

            Paciente paciente = new Paciente();
            paciente.setId(cod);
            paciente.setNombre(nombre);
            paciente.setEdad(edad);

            pacienteService.save(paciente);
            JOptionPane.showMessageDialog(null, "Paciente creado exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Entrada inválida.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static void actualizarPaciente() {
        try {
            String idStr = JOptionPane.showInputDialog("Ingrese el ID del paciente a actualizar:");
            int id = Integer.parseInt(idStr);

            Paciente paciente = pacienteService.findById(id);
            if (paciente == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el paciente con ID " + id);
                return;
            }

            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del paciente (dejar en blanco para no cambiar):");
            if (!nombre.isEmpty()) {
                paciente.setNombre(nombre);
            }

            String edadStr = JOptionPane.showInputDialog("Ingrese la nueva edad del paciente (0 para no cambiar):");
            int edad = Integer.parseInt(edadStr);
            if (edad > 0) {
                paciente.setEdad(edad);
            }

            pacienteService.update(paciente);
            JOptionPane.showMessageDialog(null, "Paciente actualizado exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Entrada inválida.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static void eliminarPaciente() {
        try {
            String idStr = JOptionPane.showInputDialog("Ingrese el ID del paciente a eliminar:");
            int id = Integer.parseInt(idStr);

            Paciente paciente = pacienteService.findById(id);
            if (paciente == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el paciente con ID " + id);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar al paciente con ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                pacienteService.delete(id);
                JOptionPane.showMessageDialog(null, "Paciente eliminado exitosamente.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Entrada inválida.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}


