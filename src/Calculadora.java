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
        String peticionLimpa = peticion.trim();

        String simbolosOperadores = "[+\\-*/%]";
        int contaSimbolos = peticionLimpa.replaceAll("[^" + simbolosOperadores + "]", "").length();
        int contaRaiz = peticionLimpa.toUpperCase().split("RAIZ", -1).length - 1;

        if (contaSimbolos + contaRaiz > 1) {
            return "Non podes facer operacións combinadas, fai primeiro a RAIZ ou % e logo operas co resultado ";
        }

        String[] anacos = peticionLimpa.split("\\s+");

        if (anacos.length < 2 || anacos.length > 3) {
            return "Formato incorrecto. Proba: 'n1 OPERADOR n2' ou 'n1 OPERADOR 0' para RAIZ.";
        }

        if (anacos.length == 2) {
            String op1Str = anacos[0];
            String operacion = anacos[1];
            String op2Str = "0";

            if (!operacion.toUpperCase().equals("RAIZ")) {
                return "Formato inválido para RAIZ, asegúrate de non por tildes. Proba: 'n1 OPERADOR n2'";
            }
            anacos = new String[]{op1Str, operacion, op2Str};
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

            String operacionUpper = operacion.toUpperCase();

            switch (operacionUpper) {
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

                case "%":
                    resultado = op1 * (op2 / 100.0);
                    break;

                case "RAIZ":
                    if (op1 < 0) {
                        return "Oi, son unha calculadora simple, non fago raices de negativos";
                    }
                    resultado = Math.sqrt(op1);
                    break;

                default:
                    return "Iso que escribiches non é valido";
            }

            return String.valueOf(resultado);

        } catch (NumberFormatException e) {
            return "Ei, coa calculadora só podes usar numeros";
        }
    }
}