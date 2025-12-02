import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Cliente que se conecta ao servidor da calculadora para enviar operacións.
 */
public class ClienteCalculadora {

    private static final String HOST = "localhost";
    private static final int PORTO = 2500;

    /**
     * Punto de inicio da aplicación cliente.
     * Tenta conectarse, enviar peticións e recibir resultados.
     * @param args Argumentos de liña de comandos (non usados).
     */
    public static void main(String[] args) {
        System.out.println("Es o cliente, estás intentando conectarte" + HOST + ":" + PORTO + "...");

        try (
                Socket socket = new Socket(HOST, PORTO);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
                Scanner escaner = new Scanner(System.in)
        ) {
            System.out.println("Todo ben. Escribe 'SAIR' para terminar.");
            System.out.println("Como funciona?: Escribe OPERACION n1 n2 (SUMA 1 5),(20 % 30), (2 RAIZ),(ACUMULAR + 5), (LAST_RES * 2), TES QUE POR ESPAZOS");
            System.out.println("NON ACEPTO OPERACIÓNS COMBINADAS");
            System.out.println("Operacións dispoñibles : SUMA; RESTA, DIVISION, MULTIPLICACION, PORCENTAXE, RAIZ, ACuaMULAR, LAST_RES");
            String operacion;

            while (true) {
                System.out.print("Cálculo > ");
                operacion = escaner.nextLine();

                if (operacion.trim().equalsIgnoreCase("SAIR")) {
                    break;
                }

                saida.println(operacion);
                String resposta = entrada.readLine();

                if (resposta != null) {
                    System.out.println("Solución: " + resposta);
                } else {
                    System.out.println("Servidor desconectado.");
                    break;
                }
            }

        } catch (ConnectException e) {
            System.err.println("Non te conectaches, fíxate se corriches o servidor");
        } catch (IOException e) {
            System.err.println("Problema na comunicación" + e.getMessage());
        } finally {
            System.out.println("Rematado");
        }
    }
}