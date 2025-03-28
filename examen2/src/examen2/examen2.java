/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package examen2;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class examen2 {
    private PSNUser psnUsers;

    public examen2() {
        try {
            psnUsers = new PSNUser("psn.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Mostrar la interfaz grafica de gestión de usuarios
    private void mostrarGestionUsuarios() {
        JFrame frame = new JFrame("Gestion de Usuarios PSN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(7, 2));

        // Centramos la ventana
        frame.setLocationRelativeTo(null);

        JTextField campoNombreUsuario = new JTextField();
        JTextField campoJuegoTrofeo = new JTextField();
        JTextField campoNombreTrofeo = new JTextField();
        JComboBox<Trophy> comboBoxTipoTrofeo = new JComboBox<>(Trophy.values());

        JButton botonRegistrarUsuario = new JButton("Registrar Usuario");
        JButton botonDesactivarUsuario = new JButton("Desactivar Usuario");
        JButton botonAgregarTrofeo = new JButton("Agregar Trofeo");
        JButton botonInfoUsuario = new JButton("Informacion de Usuario");
        JButton botonSalir = new JButton("Salir");

        frame.add(new JLabel("Nombre de Usuario:"));
        frame.add(campoNombreUsuario);
        frame.add(new JLabel("Juego del Trofeo:"));
        frame.add(campoJuegoTrofeo);
        frame.add(new JLabel("Nombre del Trofeo:"));
        frame.add(campoNombreTrofeo);
        frame.add(new JLabel("Tipo de Trofeo:"));
        frame.add(comboBoxTipoTrofeo);
        frame.add(botonRegistrarUsuario);
        frame.add(botonDesactivarUsuario);
        frame.add(botonAgregarTrofeo);
        frame.add(botonInfoUsuario);

        JPanel panelSalir = new JPanel();
        panelSalir.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSalir.add(botonSalir);
        frame.add(panelSalir);

        // Botón para abrir la ventana de registro de usuario
        botonRegistrarUsuario.addActionListener(e -> {
            frame.dispose();
            mostrarRegistroUsuario();
        });

        // Botón para abrir la ventana de desactivar usuario
        botonDesactivarUsuario.addActionListener(e -> {
            frame.dispose();
            mostrarDesactivarUsuario();
        });

        // Validacion para agregar trofeo
        botonAgregarTrofeo.addActionListener(e -> {
            String nombreUsuario = campoNombreUsuario.getText().trim();
            String juegoTrofeo = campoJuegoTrofeo.getText().trim();
            String nombreTrofeo = campoNombreTrofeo.getText().trim();
            Trophy tipoTrofeo = (Trophy) comboBoxTipoTrofeo.getSelectedItem();

            if (nombreUsuario.isEmpty() || juegoTrofeo.isEmpty() || nombreTrofeo.isEmpty() || tipoTrofeo == null) {
                JOptionPane.showMessageDialog(frame, "Por favor ingrese toda la informacion del trofeo.");
                return;
            }
            try {
                if (!psnUsers.existeUsuario(nombreUsuario)) {
                    JOptionPane.showMessageDialog(frame, "El nombre de usuario no está registrado.");
                    return;
                }
                psnUsers.agregarTrofeoA(nombreUsuario, juegoTrofeo, nombreTrofeo, tipoTrofeo);
                JOptionPane.showMessageDialog(frame, "Trofeo agregado exitosamente.");
                // Limpiar los campos
                campoNombreUsuario.setText("");
                campoJuegoTrofeo.setText("");
                campoNombreTrofeo.setText("");
                comboBoxTipoTrofeo.setSelectedIndex(0);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error al agregar trofeo.");
            }
        });

        // Botón para abrir la ventana de informacion de usuario
        botonInfoUsuario.addActionListener(e -> {
            frame.dispose();
            mostrarInfoUsuario();
        });

        // Botón para salir del programa
        botonSalir.addActionListener(e -> {
            System.exit(0);
        });

        frame.setVisible(true);
    }

    // Mostrar la interfaz grafica para registrar usuario
    private void mostrarRegistroUsuario() {
        JFrame frame = new JFrame("Registrar Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new GridLayout(3, 2));

        // Centramos la ventana
        frame.setLocationRelativeTo(null);

        JTextField campoNombreUsuario = new JTextField();
        JButton botonRegistrar = new JButton("Registrar");
        JButton botonAtras = new JButton("Atras");

        frame.add(new JLabel("Nombre de Usuario:"));
        frame.add(campoNombreUsuario);
        frame.add(botonRegistrar);
        frame.add(botonAtras);

        botonRegistrar.addActionListener(e -> {
            String nombreUsuario = campoNombreUsuario.getText().trim();
            if (nombreUsuario.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor ingrese un nombre de usuario.");
                return;
            }
            try {
                if (psnUsers.existeUsuario(nombreUsuario)) {
                    JOptionPane.showMessageDialog(frame, "El nombre de usuario ya existe. Por favor ingrese otro nombre de usuario.");
                } else {
                    psnUsers.agregarUsuario(nombreUsuario);
                    JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error al registrar usuario.");
            }
        });

        botonAtras.addActionListener(e -> {
            frame.dispose();
            mostrarGestionUsuarios();
        });

        frame.setVisible(true);
    }

    // Mostrar la interfaz grafica para desactivar usuario
    private void mostrarDesactivarUsuario() {
        JFrame frame = new JFrame("Desactivar Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new GridLayout(3, 2));

        // Centramos la ventana
        frame.setLocationRelativeTo(null);

        JTextField campoNombreUsuario = new JTextField();
        JButton botonDesactivar = new JButton("Desactivar");
        JButton botonAtras = new JButton("Atras");

        frame.add(new JLabel("Nombre de Usuario:"));
        frame.add(campoNombreUsuario);
        frame.add(botonDesactivar);
        frame.add(botonAtras);

        botonDesactivar.addActionListener(e -> {
            String nombreUsuario = campoNombreUsuario.getText().trim();
            if (nombreUsuario.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor ingrese un nombre de usuario.");
                return;
            }
            try {
                if (!psnUsers.existeUsuario(nombreUsuario)) {
                    JOptionPane.showMessageDialog(frame, "El nombre de usuario no está registrado.");
                    return;
                }
                psnUsers.desactivarUsuario(nombreUsuario);
                JOptionPane.showMessageDialog(frame, "Usuario desactivado exitosamente.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error al desactivar usuario.");
            }
        });

        botonAtras.addActionListener(e -> {
            frame.dispose();
            mostrarGestionUsuarios();
        });

        frame.setVisible(true);
    }

    // Mostrar la interfaz grafica para informacion de usuario
    private void mostrarInfoUsuario() {
        JFrame frame = new JFrame("Informacion de Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Centramos la ventana
        frame.setLocationRelativeTo(null);

        JTextField campoNombreUsuario = new JTextField();
        JButton botonMostrar = new JButton("Mostrar");
        JButton botonAtras = new JButton("Atras");
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel panelSuperior = new JPanel(new GridLayout(3, 1));
        panelSuperior.add(new JLabel("Nombre de Usuario:"));
        panelSuperior.add(campoNombreUsuario);
        panelSuperior.add(botonMostrar);

        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(botonAtras, BorderLayout.SOUTH);

        botonMostrar.addActionListener(e -> {
            String nombreUsuario = campoNombreUsuario.getText().trim();
            if (nombreUsuario.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor ingrese un nombre de usuario.");
                return;
            }
            try {
                if (!psnUsers.existeUsuario(nombreUsuario)) {
                    JOptionPane.showMessageDialog(frame, "El nombre de usuario no está registrado.");
                    return;
                }
                String info = psnUsers.getInformacionUsuario(nombreUsuario);
                textArea.setText(info);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error al mostrar informacion del usuario.");
            }
        });

        botonAtras.addActionListener(e -> {
            frame.dispose();
            mostrarGestionUsuarios();
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new examen2().mostrarGestionUsuarios());
    }
}