import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
    private int port;

    public ServerThread(int port){
        this.port = port;
    }

    @Override
    public void run(){
        //porneste functionalitate server
        //pe alt fir de executie
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Mesaj thread server: server pornit");

            Socket socketPredecesor = serverSocket.accept();
            Socket socketSuccesor = SocketSuccesor.getSocketSuccesor();

            DataInputStream inPredecesor = new DataInputStream(socketPredecesor.getInputStream());
            DataOutputStream outSuccesor = new DataOutputStream(socketSuccesor.getOutputStream());

            String line;
            do{
                //datele primite de la predecesor
                line = inPredecesor.readUTF();

                //le afisam in consola
                System.out.println("Date primite: " + line);

                //sunt verificate
                if (!verifica(line)){
                    /*
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                     */
                    //sunt modificate
                    line += ' ' + Main.semnatura;
                    //si le redirectionam spre succesor
                    outSuccesor.writeUTF(line);
                    System.out.println("Date redirectionate: " + line);
                }
            }while(!line.equals("bye"));

            serverSocket.close();
            System.out.println("Server oprit");
        } catch (IOException e) {
            System.out.println("Eroare in timpul rularii serverului");
            throw new RuntimeException(e);
        }

        System.out.println("Final thread server");
    }

    private boolean verifica(String line){
        String[] arr = line.split(" ");
        for (String cuvant: arr){
            if (cuvant.equals(Main.semnatura)){
                return true;
            }
        }
        return false;
    }
}
