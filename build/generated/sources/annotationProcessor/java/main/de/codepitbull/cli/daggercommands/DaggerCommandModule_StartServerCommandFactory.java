package de.codepitbull.cli.daggercommands;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import de.codepitbull.cli.dagger.DaggerCommand;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class DaggerCommandModule_StartServerCommandFactory implements Factory<DaggerCommand> {
  private final DaggerCommandModule module;

  private final Provider<StartServerCommand> commandProvider;

  public DaggerCommandModule_StartServerCommandFactory(DaggerCommandModule module,
      Provider<StartServerCommand> commandProvider) {
    this.module = module;
    this.commandProvider = commandProvider;
  }

  @Override
  public DaggerCommand get() {
    return startServerCommand(module, commandProvider.get());
  }

  public static DaggerCommandModule_StartServerCommandFactory create(DaggerCommandModule module,
      Provider<StartServerCommand> commandProvider) {
    return new DaggerCommandModule_StartServerCommandFactory(module, commandProvider);
  }

  public static DaggerCommand startServerCommand(DaggerCommandModule instance,
      StartServerCommand command) {
    return Preconditions.checkNotNullFromProvides(instance.startServerCommand(command));
  }
}
