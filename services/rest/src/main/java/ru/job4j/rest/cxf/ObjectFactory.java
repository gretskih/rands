
package ru.job4j.rest.cxf;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName STUDENT_ONE_REQUEST = new QName("http://", "StudentOneRequest");
    private final static QName STUDENT_ONE_RESPONSE = new QName("http://", "StudentOneResponse");
    private final static QName STUDENTS_REQUEST_QNAME = new QName("http://", "StudentsRequest");
    private final static QName STUDENTS_RESPONSE_QNAME = new QName("http://", "StudentsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StudentOneRequest }
     * 
     */
    public StudentOneRequest createStudentOneRequest() {
        return new StudentOneRequest();
    }

    /**
     * Create an instance of {@link StudentOneResponse }
     * 
     */
    public StudentOneResponse createStudentOneResponse() {
        return new StudentOneResponse();
    }

    /**
     * Create an instance of {@link StudentsRequest }
     * 
     */
    public StudentsRequest createStudentsRequest() {
        return new StudentsRequest();
    }

    /**
     * Create an instance of {@link StudentsResponse }
     * 
     */
    public StudentsResponse createStudentsResponse() {
        return new StudentsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentOneRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StudentOneRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://", name = "StudentOneRequest")
    public JAXBElement<StudentOneRequest> createStudentOneRequest(StudentOneRequest value) {
        return new JAXBElement<StudentOneRequest>(STUDENT_ONE_REQUEST, StudentOneRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentOneResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StudentOneResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://", name = "StudentOneResponse")
    public JAXBElement<StudentOneResponse> createStudentOneResponse(StudentOneResponse value) {
        return new JAXBElement<StudentOneResponse>(STUDENT_ONE_RESPONSE, StudentOneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentsRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StudentsRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://", name = "StudentsRequest")
    public JAXBElement<StudentsRequest> createStudentsRequest(StudentsRequest value) {
        return new JAXBElement<StudentsRequest>(STUDENTS_REQUEST_QNAME, StudentsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StudentsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://", name = "StudentsResponse")
    public JAXBElement<StudentsResponse> createStudentsResponse(StudentsResponse value) {
        return new JAXBElement<StudentsResponse>(STUDENTS_RESPONSE_QNAME, StudentsResponse.class, null, value);
    }

}
