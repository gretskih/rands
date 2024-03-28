package ru.job4j.soap.service;

import jakarta.jws.WebService;

@WebService(serviceName = "StudentService", portName = "Port",
        targetNamespace = "http://",
        endpointInterface = "ru.job4j.soap.service.Students")
public class StudentsImpl implements Students {

    public String getAllStudents(String sort) {
        try {
            return "Все студенты!!! " + sort;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getOneStudent(String number) {
        try {
            return "Cтудент номер: " + number;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
