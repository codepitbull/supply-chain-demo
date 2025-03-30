package de.codepitbull.cli.dagger;

import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import de.codepitbull.cli.daggercommands.DaggerCommandModule;
import de.codepitbull.cli.daggercommands.DaggerCommandModule_LoginCommandFactory;
import de.codepitbull.cli.daggercommands.DaggerCommandModule_StartServerCommandFactory;
import de.codepitbull.cli.daggercommands.MyDaggerCommand;
import de.codepitbull.cli.daggercommands.MyDaggerCommand_Factory;
import de.codepitbull.cli.daggercommands.SharedService_Factory;
import de.codepitbull.cli.daggercommands.StartServerCommand;
import de.codepitbull.cli.daggercommands.StartServerCommand_Factory;
import java.util.Map;
import javax.annotation.processing.Generated;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DaggerDaggerCommandsFactoryComponent {
  private DaggerDaggerCommandsFactoryComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static DaggerCommandsFactoryComponent create() {
    return new Builder().build();
  }

  public static final class Builder {
    private DaggerCommandModule daggerCommandModule;

    private Builder() {
    }

    public Builder daggerCommandModule(DaggerCommandModule daggerCommandModule) {
      this.daggerCommandModule = Preconditions.checkNotNull(daggerCommandModule);
      return this;
    }

    public DaggerCommandsFactoryComponent build() {
      if (daggerCommandModule == null) {
        this.daggerCommandModule = new DaggerCommandModule();
      }
      return new DaggerCommandsFactoryComponentImpl(daggerCommandModule);
    }
  }

  private static final class DaggerCommandsFactoryComponentImpl implements DaggerCommandsFactoryComponent {
    private final DaggerCommandModule daggerCommandModule;

    private final DaggerCommandsFactoryComponentImpl daggerCommandsFactoryComponentImpl = this;

    private Provider<MyDaggerCommand> myDaggerCommandProvider;

    private Provider<StartServerCommand> startServerCommandProvider;

    private DaggerCommandsFactoryComponentImpl(DaggerCommandModule daggerCommandModuleParam) {
      this.daggerCommandModule = daggerCommandModuleParam;
      initialize(daggerCommandModuleParam);

    }

    private DaggerCommand loginCommand() {
      return DaggerCommandModule_LoginCommandFactory.loginCommand(daggerCommandModule, myDaggerCommandProvider.get());
    }

    private DaggerCommand startServerCommand() {
      return DaggerCommandModule_StartServerCommandFactory.startServerCommand(daggerCommandModule, startServerCommandProvider.get());
    }

    private Map<Class<? extends DaggerCommand>, DaggerCommand> mapOfClassOfAndDaggerCommand() {
      return MapBuilder.<Class<? extends DaggerCommand>, DaggerCommand>newMapBuilder(2).put(MyDaggerCommand.class, loginCommand()).put(StartServerCommand.class, startServerCommand()).build();
    }

    @SuppressWarnings("unchecked")
    private void initialize(final DaggerCommandModule daggerCommandModuleParam) {
      this.myDaggerCommandProvider = DoubleCheck.provider(MyDaggerCommand_Factory.create(SharedService_Factory.create()));
      this.startServerCommandProvider = DoubleCheck.provider(StartServerCommand_Factory.create());
    }

    @Override
    public DaggerCommandsFactory sharedCommandsFactory() {
      return new DaggerCommandsFactory(mapOfClassOfAndDaggerCommand());
    }
  }
}
