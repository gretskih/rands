
package ru.job4j.restxml.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    private Integer testBookNumber;
    private String faculty;
    private String firstName;
    private String lastName;
    private String middleName;
    private byte[] photo;
}
