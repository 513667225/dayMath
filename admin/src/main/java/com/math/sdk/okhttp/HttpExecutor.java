package com.math.sdk.okhttp;

import com.math.sdk.core.HttpRequest;
import com.math.sdk.core.HttpResponse;
import com.math.sdk.core.HttpResponseImpl;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(HttpExecutor.class);

    private static final HttpExecutor INSTANCE = new HttpExecutor();

    private HttpExecutor() {
    }

    public static HttpExecutor create() {
        return INSTANCE;
    }


    public <R> HttpResponse execute(HttpRequest<R> request) throws Exception {
        LOG.debug("Executing Request: {} -> {}", request.getEndpoint(), request.getPath());
        HttpCommand<R> command = HttpCommand.create(request);
        return invokeRequest(command);
    }

    private <R> HttpResponse invokeRequest(HttpCommand<R> command) throws Exception {
        Response response = command.execute();
        if (command.getRetries() == 0 && response.code() != 200) {
            return invokeRequest(command.incrementRetriesAndReturn());
        }
        return HttpResponseImpl.wrap(response);
    }

}
