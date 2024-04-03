
package ru.job4j.restxml.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    public StudentDTO(String faculty) {
        this.faculty = faculty;
    }

    protected String faculty;
    protected String firstName;
    protected String lastName;
    protected String middleName;
    protected byte[] photo;
    protected Integer testBookNumber;
}
