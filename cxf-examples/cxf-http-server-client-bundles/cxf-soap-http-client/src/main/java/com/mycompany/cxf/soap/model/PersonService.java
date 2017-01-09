package com.mycompany.cxf.soap.model;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.4.redhat-621107
 * 2016-08-26T20:15:47.599+05:30
 * Generated source version: 3.0.4.redhat-621107
 * 
 */
@WebService(targetNamespace = "http://model.soap.cxf.mycompany.com/", name = "PersonService")
@XmlSeeAlso({ObjectFactory.class})
public interface PersonService {

    @WebMethod
    @RequestWrapper(localName = "deletePerson", targetNamespace = "http://model.soap.cxf.mycompany.com/", className = "com.mycompany.cxf.soap.model.DeletePerson")
    @ResponseWrapper(localName = "deletePersonResponse", targetNamespace = "http://model.soap.cxf.mycompany.com/", className = "com.mycompany.cxf.soap.model.DeletePersonResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.mycompany.cxf.soap.model.Person deletePerson(
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    ) throws PersonException_Exception;

    @WebMethod
    @RequestWrapper(localName = "getPerson", targetNamespace = "http://model.soap.cxf.mycompany.com/", className = "com.mycompany.cxf.soap.model.GetPerson")
    @ResponseWrapper(localName = "getPersonResponse", targetNamespace = "http://model.soap.cxf.mycompany.com/", className = "com.mycompany.cxf.soap.model.GetPersonResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.mycompany.cxf.soap.model.Person getPerson(
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    ) throws PersonException_Exception;

    @WebMethod
    @RequestWrapper(localName = "putPerson", targetNamespace = "http://model.soap.cxf.mycompany.com/", className = "com.mycompany.cxf.soap.model.PutPerson")
    @ResponseWrapper(localName = "putPersonResponse", targetNamespace = "http://model.soap.cxf.mycompany.com/", className = "com.mycompany.cxf.soap.model.PutPersonResponse")
    @WebResult(name = "return", targetNamespace = "")
    public com.mycompany.cxf.soap.model.Person putPerson(
        @WebParam(name = "arg0", targetNamespace = "")
        com.mycompany.cxf.soap.model.Person arg0
    );
}