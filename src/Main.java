import java.util.Scanner;

public class Main {
    static final String ipSuccesor = "127.0.0.1";
    static final int portSuccesor = 5002;
    static final String semnatura = "m1";

    public static void main(String[] args) {
        int port = 5001;

        //porneste server pe alt thread
        ServerThread st = new ServerThread(port);
        st.start();

        System.out.println("Tastati un caracter pentru a stabili conexiunea cu " + ipSuccesor + ":" + portSuccesor);
        new Scanner(System.in).nextLine();

        Client client = new Client();
        client.startClient();

        System.out.println("Final thread principal");
    }
}