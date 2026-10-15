package edu.upv.streamflow.modelo;

public class SuscripcionStreamingFamiliar extends SuscripcionStreaming {

    public SuscripcionStreamingFamiliar(
            String codigoContrato,
            double tarifaBase,
            int perfilesAdicionales,
            double costoPorPerfilAdicional) {

        super(codigoContrato, tarifaBase, perfilesAdicionales, costoPorPerfilAdicional);
    }

    @Override
    public double calcularMontoFacturable() {
        int perfilesFacturables = Math.max(0, perfilesAdicionales - 1);

        return tarifaBase + (perfilesFacturables * costoPorPerfilAdicional);
    }
}