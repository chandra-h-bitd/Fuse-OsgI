/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.cxf.jaxrs.testbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @version $Revision: 995636 $
 */
@Path("/customerservice/")
public class CustomerService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    long currentId = 123;
    Map<Long, Customer> customers = new HashMap<Long, Customer>();
    Map<Long, Order> orders = new HashMap<Long, Order>();
    Map<Long, Product> products = new HashMap<Long, Product>();
    
    public CustomerService() {
        init();
    }

    @GET
    @Path("/customers/{id}")
    @Produces("application/customer+xml")
    public Response getCustomer(@PathParam("id") String id) {
        long idNumber = Long.parseLong(id);
        LOG.info("received a GET request on /customers/" + id);
        Customer c = customers.get(idNumber);
        if (c != null) {
            LOG.info(c.toString());
        }
        Response resp = Response.ok(c).build();
        return resp;
    }
    
    @GET
    @Path("/customers")
    @Produces("application/customer+xml")
    public Response getCustomerByQueryParam(@QueryParam("id") String id) {
        long idNumber = Long.parseLong(id);
        Customer c = customers.get(idNumber);
        Response resp = Response.ok(c).build();
        return resp;
    }
    
    @GET
    @Path("/customers")
    @Produces("application/xml")
    public Response getCustomers() {
        LOG.info("received a GET request on /customers so returning a list of customers");
        List<Customer> l = new ArrayList<Customer>(customers.values());
        Response resp = Response.ok(new GenericEntity<List<Customer>>(l) {}).build();
        //Response resp = Response.ok(l).build();
        return resp;
    }
    
    @PUT
    @Path("/customers")
    @Consumes("application/customer+xml")
    @Produces("application/text")
    public Response updateCustomer(Customer customer) {
        Customer c = customers.get(customer.getId());
        Response r;
        if (c != null) {
            customers.put(customer.getId(), customer);
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @POST
    @Path("/customers")
    @Consumes("application/customer+xml")
    @Produces("application/customer+xml")
    public Response addCustomer(Customer customer) {
        LOG.info("received a POST request on /customers with customer info: " + customer.toString());
        //customer.setId(++currentId);

        customers.put(customer.getId(), customer);
        
        return Response.ok(customer).build();
    }
    
    @POST
    @Path("/customersUniqueResponseCode/")
    @Consumes("application/customer+xml")
    @Produces("application/customer+xml")
    public Response addCustomerUniqueResponseCode(Customer customer) {
        customer.setId(++currentId);

        customers.put(customer.getId(), customer);
        
        return Response.status(201).entity(customer).build();
    }

    @DELETE
    @Path("/customers/{id}")
    public Response deleteCustomer(@PathParam("id") String id) {
        long idNumber = Long.parseLong(id);
        Customer c = customers.get(idNumber);

        Response r;
        if (c != null) {
            r = Response.ok().build();
            customers.remove(idNumber);
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @GET
    @Path("/orders/{id}")
    @Produces("application/order+xml")
    public Response getOrder(@PathParam("id") String orderId) {
        long idNumber = Long.parseLong(orderId);
        Order o = orders.get(idNumber);
        Response resp = Response.ok(o).build();
        return resp;
    }

    @GET
    @Path("/orders")
    @Produces("application/xml")
    public Response getOrders() {
        List<Order> l = new ArrayList<Order>(orders.values());
        Response resp = Response.ok(l).build();
        return resp;
    }
    
    @PUT
    @Path("/orders")
    @Consumes("application/order+xml")
    @Produces("application/text")
    public Response updateOrder(Order order) {
        Order o = orders.get(order.getId());
        Response r;
        if (o != null) {
            orders.put(order.getId(), order);
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @POST
    @Path("/orders")
    @Consumes("application/order+xml")
    @Produces("application/order+xml")
    public Response addOrder(Order order) {
        order.setId(++currentId);

        orders.put(order.getId(), order);
        
        return Response.ok(order).build();
    }
    
    @DELETE
    @Path("/orders/{id}")
    public Response deleteOrder(@PathParam("id") String id) {
        long idNumber = Long.parseLong(id);
        Order o = orders.get(idNumber);

        Response r;
        if (o != null) {
            r = Response.ok().build();
            orders.remove(idNumber);
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @GET
    @Path("/products/{id}")
    @Produces("application/product+xml")
    public Response getProduct(@PathParam("id") String productId) {
        long idNumber = Long.parseLong(productId);
        Product p = products.get(idNumber);
        Response resp = Response.ok(p).build();
        return resp;
    }

    @GET
    @Path("/products")
    @Produces("application/xml")
    public Response getProducts() {
        List<Product> l = new ArrayList<Product>(products.values());
        Response resp = Response.ok(l).build();
        return resp;
    }
    
    @PUT
    @Path("/products")
    @Consumes("application/product+xml")
    @Produces("application/text")
    public Response updateProduct(Product product) {
        Product p = products.get(product.getId());
        Response r;
        if (p != null) {
            products.put(product.getId(), product);
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @POST
    @Path("/products")
    @Consumes("application/product+xml")
    @Produces("application/product+xml")
    public Response addProduct(Product product) {
        product.setId(++currentId);

        products.put(product.getId(), product);
        
        return Response.ok(product).build();
    }
    
    @DELETE
    @Path("/products/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        long idNumber = Long.parseLong(id);
        Product p = products.get(idNumber);

        Response r;
        if (p != null) {
            r = Response.ok().build();
            products.remove(idNumber);
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    final void init() {
        Customer c = new Customer();
        c.setName("John");
        c.setId(123);
        customers.put(c.getId(), c);

        c = new Customer();
        c.setName("Dan");
        c.setId(113);
        customers.put(c.getId(), c);

        Order o = new Order();
        o.setDescription("order 223");
        o.setId(223);
        orders.put(o.getId(), o);
    }

}

