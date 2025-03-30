package de.codepitbull.cli.daggercommands;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MyDaggerCommand_Factory implements Factory<MyDaggerCommand> {
  private final Provider<SharedService> sharedServiceProvider;

  public MyDaggerCommand_Factory(Provider<SharedService> sharedServiceProvider) {
    this.sharedServiceProvider = sharedServiceProvider;
  }

  @Override
  public MyDaggerCommand get() {
    return newInstance(sharedServiceProvider.get());
  }

  public static MyDaggerCommand_Factory create(Provider<SharedService> sharedServiceProvider) {
    return new MyDaggerCommand_Factory(sharedServiceProvider);
  }

  public static MyDaggerCommand newInstance(SharedService sharedService) {
    return new MyDaggerCommand(sharedService);
  }
}
