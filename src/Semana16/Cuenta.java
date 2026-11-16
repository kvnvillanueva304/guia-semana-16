package Semana16;

public class Cuenta {
    private String titular;
    private int saldo;

    public Cuenta(String titular, int saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public void depositar(int monto) {
        if (monto <= 0) {
            throw new MontoInvalidoException("Monto: " + monto);
        }
        saldo += monto;
    }

    public void retirar(int monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new MontoInvalidoException("Monto: " + monto);
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente " + monto, monto - saldo);
        }
        saldo -= monto;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}
