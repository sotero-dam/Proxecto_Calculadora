import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor principal que escoita conexións de clientes e asigna un XestorCliente a cada un.
 */
public class ServidorCalculadora {

    private static final int PORTO = 2500;

    /**
     * Inicia o servidor, acepta conexións e crea fíos para xestionar indiidualmente cada cliente.
     */
    public static void main(String[] args) {
        System.out.println("Servidor iniciado e escoitando no porto " + PORTO + "...");

        try (ServerSocket socketServidor = new ServerSocket(PORTO)) {
            while (true) {
                Socket socketCliente = socketServidor.accept();

                XestorCliente xestor = new XestorCliente(socketCliente);
                xestor.start();
            }
        } catch (IOException e) {
            System.err.println("Erro ao iniciar o servidor ou aceptar conexións: " + e.getMessage());
        }
    }
}