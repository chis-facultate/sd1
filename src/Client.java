import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public void startClient(){
        //porneste functionalitate client
        DataOutputStream out = null;
        try {
            Socket socketSuccesor = SocketSuccesor.getSocketSuccesor();
            out = new DataOutputStream(socketSuccesor.getOutputStream());
            System.out.println("Conexiune stabilita cu succesorul " + socketSuccesor.getInetAddress() + ":"
                    + socketSuccesor.getPort());
        } catch (Exception e){
            System.out.println("Eroare la rularea clientului");
            System.out.println(e);
        }

        // Use a BufferedReader to read input from the console
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String line = "";
        // keep reading until "bye" is input
        while (!line.equals("bye")) {
            try {
                System.out.print("> ");
                line = consoleReader.readLine(); // Read input from the console
                //semnam mesajul
                line += ' ' + Main.semnatura;
                //trimite datele spre succesor
                out.writeUTF(line);
                System.out.println("Date trimise de la tastatura: " + line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        try {
            consoleReader.close();
            System.out.println("Client oprit");
        } catch (IOException i) {
            System.out.println(i);
        }
    }
}
