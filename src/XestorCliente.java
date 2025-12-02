import java.io.*;
import java.net.Socket;

/**
 * Xestiona a comunicación cun cliente nun fío separado.
 */
public class XestorCliente extends Thread {
    private final Socket socketCliente;

    /**
     * Crea un xestor de cliente para o socket dado.
     * @param socket O socket co cliente.
     */
    public XestorCliente(Socket socket) {
        this.socketCliente = socket;
    }

    /**
     * Executa o fío: escoita peticións e envía respostas.
     */
    @Override
    public void run() {
        long idFio = this.getId();
        System.out.println("-> O cliente conectouse, está no fio " + idFio);

        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter saida = new PrintWriter(socketCliente.getOutputStream(), true);
                Socket socketParaPechar = socketCliente
        ) {
            String peticion;

            while ((peticion = entrada.readLine()) != null) {
                String resposta = Calculadora.procesarPeticion(peticion);
                saida.println(resposta);
            }

        } catch (IOException e) {
        } finally {
            System.out.println("<- Cliente desconectouse  Do fio" + idFio + ").");
        }
    }
}