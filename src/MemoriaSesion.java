/**
 * Almacena a memoria da sesión para un cliente.
 */
public class MemoriaSesion {
    private double ultimoResultado = 0.0;

    /**
     * Obtén o último resultado almacenado na sesión.
     * @return O último resultado.
     */
    public double getUltimoResultado() {
        return ultimoResultado;
    }

    /**
     * Establece o último resultado almacenado na sesión.
     * @param resultado O novo resultado a gardar.
     */
    public void setUltimoResultado(double resultado) {
        this.ultimoResultado = resultado;
    }
}
