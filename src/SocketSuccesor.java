import java.io.IOException;
import java.net.Socket;

public class SocketSuccesor {
    private static Socket socketSuccesor;

    private SocketSuccesor(){

    }

    public static Socket getSocketSuccesor(){
        if (socketSuccesor == null){
            try {
                socketSuccesor = new Socket(Main.ipSuccesor, Main.portSuccesor);
            } catch (IOException e) {
                System.out.println("Eroare initializare socket succesor");
                throw new RuntimeException(e);
            }
        }
        return socketSuccesor;
    }
}
