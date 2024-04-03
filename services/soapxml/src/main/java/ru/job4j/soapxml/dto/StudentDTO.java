package ru.job4j.soapxml.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentDTO {
    private Integer testBookNumber;
    private String faculty;
    private String lastName;
    private String firstName;
    private String middleName;
    private byte[] photo;

    public Integer getTestBookNumber() {
        return testBookNumber;
    }

    public void setTestBookNumber(Integer testBookNumber) {
        this.testBookNumber = testBookNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
