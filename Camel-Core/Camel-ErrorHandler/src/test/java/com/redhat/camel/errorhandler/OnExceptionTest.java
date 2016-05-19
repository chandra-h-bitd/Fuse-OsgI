package com.redhat.camel.errorhandler;

import java.net.ConnectException;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spi.ShutdownStrategy;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * @version $Revision$
 */
public class OnExceptionTest extends CamelTestSupport {

    @Override
    public boolean isUseRouteBuilder() {
        // each unit test include their own route builder
        return false;
    }

    /**
     * This test shows that a direct match will of course trigger the onException
     */
    @Test
    public void testOnExceptionDirectMatch() throws Exception {
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                context.setTracing(true);
                ShutdownStrategy shutdownStrategy = context.getShutdownStrategy();
                shutdownStrategy.setTimeUnit(TimeUnit.SECONDS);
                shutdownStrategy.setTimeout(1);

                onException(OrderFailedException.class).maximumRedeliveries(3);

                from("direct:order")
                    .bean(OrderServiceBean.class, "handleOrder");
                
               
            }
        });
        context.start();

        try {
            template.requestBody("direct:order", "haa in Action");
            fail("Should throw an exception");
        } catch (CamelExecutionException e) {
            assertIsInstanceOf(OrderFailedException.class, e.getCause());
        }
    }


    /**
     * This test shows that a wrapped connection exception in OrderFailedException will still
     * be triggered by the onException.
     */
    //@Test
    public void testOnExceptionWrappedMatch() throws Exception {
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                context.setTracing(true);

                onException(OrderFailedException.class).maximumRedeliveries(3);

                from("direct:order")
                    .bean(OrderServiceBean.class, "handleOrder")
                    .bean(OrderServiceBean.class, "saveToDB");
            }
        });
        context.start();

        try {
            template.requestBody("direct:order", "Camel in Action");
            fail("Should throw an exception");
        } catch (CamelExecutionException e) {
            assertIsInstanceOf(OrderFailedException.class, e.getCause());
            assertIsInstanceOf(ConnectException.class, e.getCause().getCause());
        }
    }

}
