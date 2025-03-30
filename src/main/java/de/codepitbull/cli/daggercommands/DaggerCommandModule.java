package de.codepitbull.cli.daggercommands;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import de.codepitbull.cli.dagger.DaggerCommand;
import de.codepitbull.cli.dagger.DaggerCommandClassKey;

@Module
public class DaggerCommandModule {
    @Provides
    @IntoMap
    @DaggerCommandClassKey(MyDaggerCommand.class)
    public DaggerCommand loginCommand(MyDaggerCommand command) {
        return command;
    }

    @Provides
    @IntoMap
    @DaggerCommandClassKey(StartServerCommand.class)
    public DaggerCommand startServerCommand(StartServerCommand command) {
        return command;
    }
}
