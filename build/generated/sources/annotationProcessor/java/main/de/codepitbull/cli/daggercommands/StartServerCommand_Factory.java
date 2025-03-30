package de.codepitbull.cli.daggercommands;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class StartServerCommand_Factory implements Factory<StartServerCommand> {
  @Override
  public StartServerCommand get() {
    return newInstance();
  }

  public static StartServerCommand_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static StartServerCommand newInstance() {
    return new StartServerCommand();
  }

  private static final class InstanceHolder {
    private static final StartServerCommand_Factory INSTANCE = new StartServerCommand_Factory();
  }
}
