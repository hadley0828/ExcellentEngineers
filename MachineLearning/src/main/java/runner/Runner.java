package runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import util.SyncBuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by I Like Milk on 2017/6/19.
 */
@EnableAutoConfiguration
@ComponentScan("controller")
public class Runner {
    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String serverHostname = "localhost";

                Socket echoSocket = null;
                PrintWriter out = null;
                BufferedReader in = null;

                try {
                    // echoSocket = new Socket("taranis", 7);
                    echoSocket = new Socket(serverHostname, 8081);
                    out = new PrintWriter(echoSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(
                            echoSocket.getInputStream()));
                } catch (UnknownHostException e) {
                    System.err.println("Don't know about host: " + serverHostname);
                    System.exit(1);
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for "
                            + "the connection to: " + serverHostname);
                    System.exit(1);
                }

                SyncBuff syncBuff = SyncBuff.getTransInst();
                while (true) {
                    if (!syncBuff.hasBuff())
                        continue;
                    out.println(syncBuff.getBuff());
                    try {
                        syncBuff.setOutput(in.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String serverHostname = "localhost";

                Socket echoSocket = null;
                PrintWriter out = null;
                BufferedReader in = null;

                try {
                    // echoSocket = new Socket("taranis", 7);
                    echoSocket = new Socket(serverHostname, 8082);
                    out = new PrintWriter(echoSocket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(
                            echoSocket.getInputStream()));
                } catch (UnknownHostException e) {
                    System.err.println("Don't know about host: " + serverHostname);
                    System.exit(1);
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for "
                            + "the connection to: " + serverHostname);
                    System.exit(1);
                }

                SyncBuff syncBuff = SyncBuff.getCommentInst();
                while (true) {
                    if (!syncBuff.hasBuff())
                        continue;
                    out.println(syncBuff.getBuff());
                    try {
                        syncBuff.setOutput(in.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        SpringApplication.run(Runner.class, args);
    }
}
