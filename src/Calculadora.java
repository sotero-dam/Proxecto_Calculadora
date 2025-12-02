/**
 * Clase estática que ofrece funcionalidades de cálculo.
 */
public class Calculadora {

    /**
     * Procesa o cáculo que lle pidas e devolve o resultado ou un erro.
     * @param peticion A cadea de texto coa operación.
     * @return O resultado do cálculo en String ou unha mensaxe de erro.
     */
    public static String procesarPeticion(String peticion) {
        String[] anacos = peticion.trim().split("\\s+");

        if (anacos.length != 3) {
            return "Hai problemas co teu formato, asegúrate de por espazos entre numeros e simbolos (x + x)";
        }

        try {
            String op1Str = anacos[0];
            String operacion = anacos[1];
            String op2Str = anacos[2];
            if (op1Str.contains(",") || op2Str.contains(",")) {
                return "Para separar decimais usa punto non coma";
            }

            double op1 = Double.parseDouble(op1Str);
            double op2 = Double.parseDouble(op2Str);
            double resultado = 0;

            switch (operacion) {
                case "+":
                    resultado = op1 + op2;
                    break;
                case "-":
                    resultado = op1 - op2;
                    break;
                case "*":
                    resultado = op1 * op2;
                    break;
                case "/":
                    if (op2 == 0) {
                        return "Non dividas por cero, proba outro numero";
                    }
                    resultado = op1 / op2;
                    break;
                default:
                    return "Eso que escribiches non é valido";
            }

            return String.valueOf(resultado);

        } catch (NumberFormatException e) {
            return "Ei, coa calculadora só podes usar numeros";
        }
    }
}