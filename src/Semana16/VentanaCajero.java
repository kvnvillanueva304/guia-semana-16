package Semana16;

import javax.swing.*;
import java.awt.*;

public class VentanaCajero extends JFrame {
    private Cuenta cuenta;
    private JLabel lblSaldo = new JLabel();
    private JTextField txtMonto = new JTextField();
    private JButton btnDepositar = new JButton("Deposito");
    private JButton btnRetirar = new JButton("Retirar");

    public VentanaCajero(Cuenta cuenta) {
        super("Cajero " + cuenta.getTitular());
        this.cuenta = cuenta;

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        lblSaldo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblSaldo.setForeground(new Color(0x1E7A46));

        panel.add(new JLabel("Saldo actual:"));
        panel.add(lblSaldo);
        panel.add(new JLabel("Monto:"));
        panel.add(txtMonto);
        panel.add(btnDepositar);
        panel.add(btnRetirar);

        add(panel);

        btnDepositar.addActionListener(e -> depositar());
        btnRetirar.addActionListener(e -> retirar());

        actualizarSaldo();

        setSize(340, 170);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private int leerMonto() {
        return Integer.parseInt(txtMonto.getText().trim());
    }

    private void depositar() {
        try {
            cuenta.depositar(leerMonto());
            actualizarSaldo();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite números",
                    "Digito invalido", JOptionPane.ERROR_MESSAGE);
        } catch (MontoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Cantidad Invalida ", JOptionPane.WARNING_MESSAGE);
        } finally {
            limpiar();
        }
    }

    private void retirar() {
        try {
            cuenta.retirar(leerMonto());
            actualizarSaldo();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite numeros",
                    "Digito invalido", JOptionPane.ERROR_MESSAGE);
        } catch (SaldoInsuficienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage()
                            + ". Falta $" + ex.getFaltante(),
                    "Saldo insuficiente", JOptionPane.WARNING_MESSAGE);
        } catch (MontoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Monto invalido", JOptionPane.WARNING_MESSAGE);
        } finally {
            limpiar();
        }
    }

    private void actualizarSaldo() {
        lblSaldo.setText("$" + cuenta.getSaldo());
    }

    private void limpiar() {
        txtMonto.setText("");
        txtMonto.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new VentanaCajero(new Cuenta("Kevin", 100)).setVisible(true));
    }
}
