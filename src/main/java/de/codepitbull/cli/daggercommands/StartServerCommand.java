package de.codepitbull.cli.daggercommands;

import com.sun.net.httpserver.HttpServer;
import de.codepitbull.cli.Cli;
import de.codepitbull.cli.dagger.DaggerCommand;
import picocli.CommandLine;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

@Singleton
@CommandLine.Command(name = "server", mixinStandardHelpOptions = true, version = "1.0",
        description = {"""
                Start a hello world server
                """},
        subcommands = {CommandLine.HelpCommand.class})
public class StartServerCommand implements DaggerCommand {

    @Inject
    public StartServerCommand() {
    }

    public void run() {
        try {
            var helloWorld = "Hello World!";
            var address = new InetSocketAddress(8080);
            var server = HttpServer.create(address, 0);
            server.createContext("/", req -> {
                req.sendResponseHeaders(200, helloWorld.length());
                var os = req.getResponseBody();
                os.write(helloWorld.getBytes());
                os.close();
                System.out.println("Got a request!");
            });
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
