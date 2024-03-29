package ru.job4j.soap.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;
import ru.job4j.soap.dto.StudentDTO;

import java.util.List;

@WebService(targetNamespace = "http://localhost:9912", name = "StudentsStorageService")
public interface Students {

    @WebResult(name = "student", targetNamespace = "")
    @RequestWrapper(localName = "StudentsRequest",
            targetNamespace = "http://",
            className = "ru.job4j.soap.service.StudentsRequest")
    @WebMethod(action = "urn:GetAllStudents")
    @ResponseWrapper(localName = "StudentsResponse",
            targetNamespace = "http://",
            className = "ru.job4j.soap.service.StudentsResponse")
    List<StudentDTO> getAllStudents(@WebParam(name = "sort", targetNamespace = "") String sort);

    @WebResult(name = "student", targetNamespace = "")
    @RequestWrapper(localName = "StudentOneRequest",
            targetNamespace = "http://",
            className = "ru.job4j.soap.service.StudentOneRequest")
    @WebMethod(action = "urn:GetOneStudent")
    @ResponseWrapper(localName = "StudentOneResponse",
            targetNamespace = "http://",
            className = "ru.job4j.soap.service.StudentOneResponse")
    StudentDTO getOneStudent(@WebParam(name = "number", targetNamespace = "") Integer number);
}
