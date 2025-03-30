package de.codepitbull.cli.daggercommands;

import de.codepitbull.cli.Cli;
import de.codepitbull.cli.dagger.DaggerCommand;
import picocli.CommandLine;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

@Singleton
@CommandLine.Command(name = "cmd", mixinStandardHelpOptions = true, version = "1.0",
        description = {"""
                Command with some options to demonstrate TAB-completion.
                (Note that enum values also get completed.)
                """},
        subcommands = {CommandLine.HelpCommand.class})
public class MyDaggerCommand implements DaggerCommand {

    private final SharedService sharedService;

    @Inject
    public MyDaggerCommand(SharedService sharedService) {
        this.sharedService = sharedService;
    }

    @CommandLine.Option(names = {"-v", "--verbose"},
            description = { """
                    Specify multiple -v options to increase verbosity.
                    For example, `-v -v -v` or `-vvv`
                    """})
    private boolean[] verbosity = {};

    @CommandLine.ArgGroup(exclusive = false)
    private MyDuration myDuration = new MyDuration();

    static class MyDuration {
        @CommandLine.Option(names = {"-d", "--duration"},
                description = "The duration quantity.",
                required = true)
        private int amount;

        @CommandLine.Option(names = {"-u", "--timeUnit"},
                description = "The duration time unit.",
                required = true)
        private TimeUnit unit;
    }

    @CommandLine.ParentCommand
    public Cli.CliCommands parent;

    public void run() {
        if (verbosity.length > 0) {
            parent.out.printf("Hi there. You asked for %d %s.%n %s",
                    myDuration.amount, myDuration.unit, sharedService.sharedMethod());
        } else {
            parent.out.println("hi! " + sharedService.sharedMethod());
        }
    }
}
