
package ru.job4j.rest.cxf;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import ru.job4j.rest.dto.StudentDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentOneResponse", propOrder = {
    "student"
})
public class StudentOneResponse {

    protected StudentDTO student;

    /**
     * Gets the value of the student property.
     * 
     * @return
     *     possible object is
     *     {@link StudentDTO }
     *     
     */
    public StudentDTO getStudent() {
        return student;
    }

    /**
     * Sets the value of the student property.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentDTO }
     *     
     */
    public void setStudent(StudentDTO value) {
        this.student = value;
    }

}
