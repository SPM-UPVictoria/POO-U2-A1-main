package edu.upv.streamflow.modelo;

public class SuscripcionStreaming extends Suscripcion {

    protected int perfilesAdicionales;
    protected double costoPorPerfilAdicional;

    public SuscripcionStreaming(
            String codigoContrato,
            double tarifaBase,
            int perfilesAdicionales,
            double costoPorPerfilAdicional) {

        super(codigoContrato, tarifaBase);

        if (perfilesAdicionales < 0) {
            throw new IllegalArgumentException(
                    "Los perfiles adicionales no pueden ser negativos."
            );
        }

        if (costoPorPerfilAdicional < 0) {
            throw new IllegalArgumentException(
                    "El costo por perfil adicional no puede ser negativo."
            );
        }

        this.perfilesAdicionales = perfilesAdicionales;
        this.costoPorPerfilAdicional = costoPorPerfilAdicional;
    }

    public void agregarPerfil() {
        perfilesAdicionales++;
    }

    @Override
    public double calcularMontoFacturable() {
        return tarifaBase
                + (perfilesAdicionales * costoPorPerfilAdicional);
    }

    public int getPerfilesAdicionales() {
        return perfilesAdicionales;
    }

    public double getCostoPorPerfilAdicional() {
        return costoPorPerfilAdicional;
    }
}