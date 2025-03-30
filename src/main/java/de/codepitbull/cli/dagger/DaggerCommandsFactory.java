package de.codepitbull.cli.dagger;

import de.codepitbull.cli.dagger.DaggerCommand;
import picocli.CommandLine;

import javax.inject.Inject;
import java.util.Map;


public class DaggerCommandsFactory implements CommandLine.IFactory{

    private final Map<Class<? extends DaggerCommand>, DaggerCommand> command;

    @Inject
    public DaggerCommandsFactory(Map<Class<? extends DaggerCommand>, DaggerCommand> command) {
        this.command = command;
    }

    @Override
    public <K> K create(Class<K> cls) {
        if(command.containsKey(cls)) {
            DaggerCommand cmd = command.get(cls);
            return (K) cmd;
        }

        return null;
    }
}
