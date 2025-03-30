package de.codepitbull.cli.dagger;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Map;
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
public final class DaggerCommandsFactory_Factory implements Factory<DaggerCommandsFactory> {
  private final Provider<Map<Class<? extends DaggerCommand>, DaggerCommand>> commandProvider;

  public DaggerCommandsFactory_Factory(
      Provider<Map<Class<? extends DaggerCommand>, DaggerCommand>> commandProvider) {
    this.commandProvider = commandProvider;
  }

  @Override
  public DaggerCommandsFactory get() {
    return newInstance(commandProvider.get());
  }

  public static DaggerCommandsFactory_Factory create(
      Provider<Map<Class<? extends DaggerCommand>, DaggerCommand>> commandProvider) {
    return new DaggerCommandsFactory_Factory(commandProvider);
  }

  public static DaggerCommandsFactory newInstance(
      Map<Class<? extends DaggerCommand>, DaggerCommand> command) {
    return new DaggerCommandsFactory(command);
  }
}
