import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Main {
    private JFrame frame;
    private JPanel panel;

    private JLabel nombreLabel;
    private JTextField nombreField;
    private JLabel alturaLabel;
    private JTextField alturaField;
    private JLabel pesoLabel;
    private JTextField pesoField;
    private JButton calcularButton;
    private JLabel resultadoLabel;

    public Main() {
        frame = new JFrame("Calculadora de IMC");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        frame.add(panel);

        nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(20);
        alturaLabel = new JLabel("Altura (metros):");
        alturaField = new JTextField(10);
        pesoLabel = new JLabel("Peso (kg):");
        pesoField = new JTextField(10);
        calcularButton = new JButton("Calcular IMC");
        resultadoLabel = new JLabel();

        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(alturaLabel);
        panel.add(alturaField);
        panel.add(pesoLabel);
        panel.add(pesoField);
        panel.add(calcularButton);
        panel.add(resultadoLabel);

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });

        frame.setVisible(true);
    }

    private void calcularIMC() {
        try {
            String nombre = nombreField.getText();
            double altura = Double.parseDouble(alturaField.getText());
            double peso = Double.parseDouble(pesoField.getText());

            // Calcular el IMC
            double imc = peso / (altura * altura);

            // Formatear el IMC a dos decimales
            DecimalFormat df = new DecimalFormat("#.##");
            String imcFormateado = df.format(imc);

            // Evaluar el IMC y mostrar un mensaje
            String mensaje;
            if (imc < 18.5) {
                mensaje = "Hola " + nombre + ", tu IMC es " + imcFormateado + ". Estás por debajo del peso normal.";
            } else if (imc >= 18.5 && imc < 25) {
                mensaje = "Hola " + nombre + ", tu IMC es " + imcFormateado + ". Tu peso es normal para tu altura.";
            } else if (imc >= 25 && imc < 30) {
                mensaje = "Hola " + nombre + ", tu IMC es " + imcFormateado + ". Tienes sobrepeso.";
            } else {
                mensaje = "Hola " + nombre + ", tu IMC es " + imcFormateado + ". Tienes obesidad.";
            }

            resultadoLabel.setText(mensaje);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Por favor, ingresa valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
