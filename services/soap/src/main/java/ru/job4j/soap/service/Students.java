package ru.job4j.soap.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
@WebService(targetNamespace = "http://localhost:9912", name = "StudentsStorageService")
public interface Students {

    @WebResult(name = "name", targetNamespace = "")
    @RequestWrapper(localName = "StudentsRequest",
            targetNamespace = "http://",
            className = "ru.job4j.soap.service.StudentsRequest")
    @WebMethod(action = "urn:GetAllStudents")
    @ResponseWrapper(localName = "StudentsResponse",
            targetNamespace = "http://",
            className = "ru.job4j.soap.service.StudentsResponse")
    String getAllStudents(@WebParam(name = "sort", targetNamespace = "") String sort);

    @WebResult(name = "name1", targetNamespace = "")
    @RequestWrapper(localName = "StudentOneRequest",
            targetNamespace = "http://",
            className = "ru.job4j.soap.service.StudentOneRequest")
    @WebMethod(action = "urn:GetOneStudent")
    @ResponseWrapper(localName = "StudentOneResponse",
            targetNamespace = "http://",
            className = "ru.job4j.soap.service.StudentOneResponse")
    String getOneStudent(@WebParam(name = "number", targetNamespace = "") String number);
}
