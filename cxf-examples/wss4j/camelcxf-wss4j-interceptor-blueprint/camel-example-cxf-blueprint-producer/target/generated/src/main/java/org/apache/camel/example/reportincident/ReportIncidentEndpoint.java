package org.apache.camel.example.reportincident;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.4.redhat-621084
 * 2016-02-18T07:20:56.021+05:30
 * Generated source version: 3.0.4.redhat-621084
 * 
 */
@WebService(targetNamespace = "http://reportincident.example.camel.apache.org", name = "ReportIncidentEndpoint")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ReportIncidentEndpoint {

    @WebMethod(operationName = "ReportIncident", action = "http://reportincident.example.camel.apache.org/ReportIncident")
    @WebResult(name = "outputReportIncident", targetNamespace = "http://reportincident.example.camel.apache.org", partName = "out")
    public OutputReportIncident reportIncident(
        @WebParam(partName = "in", name = "inputReportIncident", targetNamespace = "http://reportincident.example.camel.apache.org")
        InputReportIncident in
    );
}
