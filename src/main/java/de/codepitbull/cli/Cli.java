package de.codepitbull.cli;


import de.codepitbull.cli.dagger.DaggerDaggerCommandsFactoryComponent;
import de.codepitbull.cli.daggercommands.MyDaggerCommand;
import de.codepitbull.cli.daggercommands.StartServerCommand;
import org.fusesource.jansi.AnsiConsole;
import org.jline.builtins.ConfigurationPath;
import org.jline.console.SystemRegistry;
import org.jline.console.impl.Builtins;
import org.jline.console.impl.SystemRegistryImpl;
import org.jline.keymap.KeyMap;
import org.jline.reader.*;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.widget.TailTipWidgets;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.shell.jline3.PicocliCommands;
import picocli.shell.jline3.PicocliCommands.PicocliCommandsFactory;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

/**
 * Example that demonstrates how to build an interactive shell with JLine3 and picocli.
 * This example requires JLine 3.16+ and picocli 4.4+.
 * <p>
 * The built-in {@code PicocliCommands.ClearScreen} command was introduced in picocli 4.6.
 * </p>
 */
public class Cli {

    /**
     * Top-level command that just prints help.
     */
    @Command(name = "",
            description = {"""
                        Example interactive shell with completion and autosuggestions.
                        Hit @|magenta <TAB>|@ to see available commands.
                        Hit @|magenta ALT-S|@ to toggle tailtips.
                    """},
            footer = {"", "Press Ctrl-D to exit."},
            subcommands = {
                    MyDaggerCommand.class, StartServerCommand.class, PicocliCommands.ClearScreen.class, CommandLine.HelpCommand.class})
    public static class CliCommands implements Runnable {
        public PrintWriter out;

        CliCommands() {}

        public void setReader(LineReader reader){
            out = reader.getTerminal().writer();
        }

        public void run() {
            out.println(new CommandLine(this).getUsageMessage());
        }
    }

    public static void main(String[] args) {

        if (args.length > 0) {
            CliCommands commands = new CliCommands();

            var daggerFactory = DaggerDaggerCommandsFactoryComponent.create();
            PicocliCommandsFactory factory = new PicocliCommandsFactory(daggerFactory.sharedCommandsFactory());
            new CommandLine(commands, factory).execute(args);
        }
        else {
            AnsiConsole.systemInstall();
            try {
                Supplier<Path> workDir = () -> Paths.get(System.getProperty("user.dir"));
                // set up JLine built-in commands
                Builtins builtins = new Builtins(workDir, new ConfigurationPath(workDir.get(), workDir.get()), null);
                builtins.rename(Builtins.Command.TTOP, "top");
                builtins.alias("zle", "widget");
                builtins.alias("bindkey", "keymap");
                // set up picocli commands
                CliCommands commands = new CliCommands();

                var daggerFactory = DaggerDaggerCommandsFactoryComponent.create();

                PicocliCommandsFactory factory = new PicocliCommandsFactory(daggerFactory.sharedCommandsFactory());
                // Or, if you have your own factory, you can chain them like this:
                // MyCustomFactory customFactory = createCustomFactory(); // your application custom factory
                // PicocliCommandsFactory factory = new PicocliCommandsFactory(customFactory); // chain the factories

                CommandLine cmd = new CommandLine(commands, factory);
                PicocliCommands picocliCommands = new PicocliCommands(cmd);

                Parser parser = new DefaultParser();
                try (Terminal terminal = TerminalBuilder.builder().build()) {
                    SystemRegistry systemRegistry = new SystemRegistryImpl(parser, terminal, workDir, null);
                    systemRegistry.setCommandRegistries(builtins, picocliCommands);
                    systemRegistry.register("help", picocliCommands);

                    LineReader reader = LineReaderBuilder.builder()
                            .terminal(terminal)
                            .completer(systemRegistry.completer())
                            .parser(parser)
                            .variable(LineReader.LIST_MAX, 50)   // max tab completion candidates
                            .build();
                    builtins.setLineReader(reader);
                    commands.setReader(reader);
                    factory.setTerminal(terminal);
                    TailTipWidgets widgets = new TailTipWidgets(reader, systemRegistry::commandDescription, 5, TailTipWidgets.TipType.COMPLETER);
                    widgets.enable();
                    KeyMap<Binding> keyMap = reader.getKeyMaps().get("main");
                    keyMap.bind(new Reference("tailtip-toggle"), KeyMap.alt("s"));

                    String prompt = "prompt> ";
                    String rightPrompt = null;

                    // start the shell and process input until the user quits with Ctrl-D
                    String line;
                    while (true) {
                        try {
                            systemRegistry.cleanUp();
                            line = reader.readLine(prompt, rightPrompt, (MaskingCallback) null, null);
                            systemRegistry.execute(line);
                        } catch (UserInterruptException e) {
                            // Ignore
                        } catch (EndOfFileException e) {
                            return;
                        } catch (Exception e) {
                            systemRegistry.trace(e);
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                AnsiConsole.systemUninstall();
            }
        }

    }
}