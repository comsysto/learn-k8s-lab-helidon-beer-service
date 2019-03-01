package io.helidon.examples.quickstart.se;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

public class ShutdownService implements Service {
    @Override
    public void update(Routing.Rules rules) {
        rules
                .get("/", this::shutDownHandler);
    }

    private void shutDownHandler(ServerRequest serverRequest, ServerResponse serverResponse) {
        System.out.println("Shutting down, ciao!");

        serverResponse.send("Shutting down!");
        System.exit(-1);
    }
}
