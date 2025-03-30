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
public final class DaggerCommandModule_LoginCommandFactory implements Factory<DaggerCommand> {
  private final DaggerCommandModule module;

  private final Provider<MyDaggerCommand> commandProvider;

  public DaggerCommandModule_LoginCommandFactory(DaggerCommandModule module,
      Provider<MyDaggerCommand> commandProvider) {
    this.module = module;
    this.commandProvider = commandProvider;
  }

  @Override
  public DaggerCommand get() {
    return loginCommand(module, commandProvider.get());
  }

  public static DaggerCommandModule_LoginCommandFactory create(DaggerCommandModule module,
      Provider<MyDaggerCommand> commandProvider) {
    return new DaggerCommandModule_LoginCommandFactory(module, commandProvider);
  }

  public static DaggerCommand loginCommand(DaggerCommandModule instance, MyDaggerCommand command) {
    return Preconditions.checkNotNullFromProvides(instance.loginCommand(command));
  }
}
