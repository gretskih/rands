
package ru.job4j.rest.cxf;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import ru.job4j.rest.dto.StudentDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentsResponse", propOrder = {
    "student"
})
public class StudentsResponse {

    protected List<StudentDTO> student;

    public List<StudentDTO> getStudent() {
        if (student == null) {
            student = new ArrayList<StudentDTO>();
        }
        return this.student;
    }

}
