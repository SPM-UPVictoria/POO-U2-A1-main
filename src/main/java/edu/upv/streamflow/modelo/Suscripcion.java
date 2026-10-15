package edu.upv.streamflow.modelo;

import edu.upv.streamflow.excepciones.SuscripcionInactivaException;
import edu.upv.streamflow.excepciones.TransaccionRechazadaException;
import edu.upv.streamflow.interfaces.Cancelable;
import edu.upv.streamflow.interfaces.Facturable;

public abstract class Suscripcion implements Facturable, Cancelable {

    protected String codigoContrato;
    protected double tarifaBase;
    protected boolean activo;

    public Suscripcion(String codigoContrato, double tarifaBase) {
        if (codigoContrato == null || codigoContrato.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El código del contrato no puede ser nulo ni vacío."
            );
        }

        if (tarifaBase < 0) {
            throw new IllegalArgumentException(
                    "La tarifa base no puede ser negativa."
            );
        }

        this.codigoContrato = codigoContrato;
        this.tarifaBase = tarifaBase;
        this.activo = true;
    }

    public String getCodigoContrato() {
        return codigoContrato;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    @Override
    public void cancelarServicio() {
        this.activo = false;
    }

    @Override
    public boolean estaActivo() {
        return activo;
    }

    @Override
    public void procesarCobro(Cliente cliente)
            throws TransaccionRechazadaException {

        if (!activo) {
            throw new SuscripcionInactivaException(
                    "La suscripción está inactiva."
            );
        }

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El cliente no puede ser nulo."
            );
        }

        double monto = calcularMontoFacturable();
        cliente.debitarSaldo(monto);
    }

    @Override
    public abstract double calcularMontoFacturable();
}