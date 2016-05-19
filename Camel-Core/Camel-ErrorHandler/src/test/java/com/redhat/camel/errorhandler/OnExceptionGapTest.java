package com.redhat.camel.errorhandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * @version $Revision$
 */
public class OnExceptionGapTest extends CamelTestSupport {

    @Override
    public boolean isUseRouteBuilder() {
        // each unit test include their own route builder
        return false;
    }

    /**
     * This tests shows no match for onException and therefore fallback on the error handler
     * and by which there are no explicit configured. Therefore default error handler will be
     * used which by default does NO redelivery attempts.
     */
    @Test
    public void testOnExceptionGab() throws Exception {
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                context.setTracing(true);

                onException(ConnectException.class).maximumRedeliveries(5);
                onException(IOException.class).maximumRedeliveries(3).redeliveryDelay(1000);
                onException(Exception.class).maximumRedeliveries(1).redeliveryDelay(5000);

                from("direct:order")
                    .bean(OrderServiceBean.class, "handleOrder")
                    .bean(OrderServiceBean.class, "enrichFromFile");
            }
        });
        context.start();

        try {
            template.requestBody("direct:order", "Camel in Action");
            fail("Should throw an exception");
        } catch (CamelExecutionException e) {
            assertIsInstanceOf(OrderFailedException.class, e.getCause());
            assertIsInstanceOf(FileNotFoundException.class, e.getCause().getCause());
        }
    }

}