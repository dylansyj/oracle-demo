package com.oracle;


import com.oracle.api.OracleDemoResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;

public class OracleDemoApplication extends Application<OracleDemoConfiguration> {
    public static void main(String[] args) throws Exception {
        new OracleDemoApplication().run(args);
    }

    @Override
    public void run(OracleDemoConfiguration configuration, Environment environment) {
        final OracleDemoResource resource = new OracleDemoResource(configuration);
        environment.jersey().register(resource);
    }
}