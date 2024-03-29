package ru.job4j.soap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Integer testBookNumber;
    private String faculty;
    private String lastName;
    private String firstName;
    private String middleName;
    private byte[] photo;
 }
