
package ru.job4j.rest.dto;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class StudentDTOFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: localhost
     * 
     */
    public StudentDTOFactory() {
    }

    /**
     * Create an instance of {@link StudentDTO }
     * 
     */
    public StudentDTO createStudentDTO() {
        return new StudentDTO();
    }

}
