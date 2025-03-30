package de.codepitbull.cli.daggercommands;

import javax.inject.Inject;

public class SharedService {

    @Inject
    public SharedService() {
    }

    public String sharedMethod() {
        return "sharedMethod";
    }
}
