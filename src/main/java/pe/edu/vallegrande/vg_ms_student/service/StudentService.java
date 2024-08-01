package pe.edu.vallegrande.vg_ms_student.service;

import pe.edu.vallegrande.vg_ms_student.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<Student> listAllActive();
    Flux<Student> listAllInactive();
    Mono<Student> createStudent (Student student);
    Mono<Student> deleteStudent(String id);
    Mono<Student> reactivateStudent(String id);
    Mono<Student> updateStudent(String id, Student student);
    Mono<Student> findStudentById(String id);

}
