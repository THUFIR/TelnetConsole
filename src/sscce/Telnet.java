package sscce;

import java.io.BufferedReader;
import java.io.OutputStream;
import static java.lang.System.out;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;

public final class Telnet {

    private final static Logger LOG = Logger.getLogger(Telnet.class.getName());
    private TelnetClient telnetClient = new TelnetClient();

    public Telnet() throws SocketException, IOException {
        InetAddress host = InetAddress.getByName("rainmaker.wunderground.com");
        int port = 3000;
        telnetClient.connect(host, port);

        final InputStream inputStream = telnetClient.getInputStream();
        final OutputStream outputStream = telnetClient.getOutputStream();

        Thread print = new Thread() {

            @Override
            public void run() {
                out.println("print..");
                try {
                    char ch = (char) inputStream.read();
                    while (255 > ch && ch >= 0) {
                        out.print(ch);
                        ch = (char) inputStream.read();
                    }
                } catch (IOException ex) {
                    out.println("cannot read inputStream:\t" + ex);
                }
            }
        };


        Thread read = new Thread() {

            @Override
            public void run() {
                BufferedReader bufferedInput = new BufferedReader(new InputStreamReader(System.in));
                try {
                    do {
                        byte b = 10;
                        outputStream.write(10);
                        outputStream.flush();
                        String command = bufferedInput.readLine();
                        byte[] bytes = command.getBytes();
                        outputStream.write(bytes);
                        outputStream.flush();
                    } while (true);
                } catch (IOException ex) {
                }
            }
        };
        print.start();
        read.start();
    }

    
    public static void main(String[] args) throws SocketException, IOException {
    new Telnet();
    }
}