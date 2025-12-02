/**
 * Xestiona peticións usando a calculadora anterior.
 */
public class ProcesadorPeticions {
    private final MemoriaSesion memoria;
    /**
     * Inicializa o procesador cunha instancia da memoria de sesión.
     * @param memoria A instancia de memoria para esta sesión de cliente.
     */
    public ProcesadorPeticions(MemoriaSesion memoria) {
        this.memoria = memoria;
    }

    /**
     * Identidica e procesa a petición, establece a lóxica e calcula.
     * @param peticion Cadea de texto coa operación.
     * @return Resultado ou unha mensaxe de erro.
     */
    public String procesar(String peticion) {
        String peticionLimpa = peticion.trim();
        String peticionUpper = peticionLimpa.toUpperCase();
        String[] anacos = peticionUpper.split("\\s+");

        if (anacos.length >= 1) {
            String comando = anacos[0];

            if ((comando.equals("ACUMULAR") || comando.equals("LAST_RES")) && anacos.length == 3) {
                return executarOperacionConMemoria(anacos[1], anacos[2]);
            } else if (comando.equals("LAST_RES") || comando.equals("ACUMULAR")) {
                return "Formato incorrecto,proba ACUMULAR OPERADOR  ou LAST_RES";
            }
        }

        String resultado = Calculadora.procesarPeticion(peticionLimpa);

        try {
            double resDoble = Double.parseDouble(resultado);
            memoria.setUltimoResultado(resDoble);
        } catch (NumberFormatException ignored) {
        }

        return resultado;
    }

    /**
     * Crea e executa a petición. Usa o ultimo resultado pra operar.
     * @param operacion O operador
     * @param op2 O segundo operando en formato String.
     * @return Resultado da operación ou un erro.
     */
    private String executarOperacionConMemoria(String operacion, String op2) {
        String op1 = String.valueOf(memoria.getUltimoResultado());
        String peticion = op1 + " " + operacion + " " + op2;
        String resultado = Calculadora.procesarPeticion(peticion);

        try {
            double resDoble = Double.parseDouble(resultado);
            memoria.setUltimoResultado(resDoble);
        } catch (NumberFormatException ignored) {
        }

        return resultado;
    }
}