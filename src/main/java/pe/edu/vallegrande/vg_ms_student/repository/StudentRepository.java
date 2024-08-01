package pe.edu.vallegrande.vg_ms_student.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.edu.vallegrande.vg_ms_student.model.Student;
import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveMongoRepository  <Student , String>{

    Flux<Student> findByStatus (String Status);
}
