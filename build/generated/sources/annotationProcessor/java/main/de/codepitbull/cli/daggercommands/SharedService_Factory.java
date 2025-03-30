package de.codepitbull.cli.daggercommands;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class SharedService_Factory implements Factory<SharedService> {
  @Override
  public SharedService get() {
    return newInstance();
  }

  public static SharedService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SharedService newInstance() {
    return new SharedService();
  }

  private static final class InstanceHolder {
    private static final SharedService_Factory INSTANCE = new SharedService_Factory();
  }
}
