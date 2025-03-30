package de.codepitbull.cli.dagger;

import dagger.Component;
import de.codepitbull.cli.daggercommands.DaggerCommandModule;

import javax.inject.Singleton;

@Component(
        modules = {
                DaggerCommandModule.class
        }
)

@Singleton
public interface DaggerCommandsFactoryComponent {

    DaggerCommandsFactory sharedCommandsFactory();
}
