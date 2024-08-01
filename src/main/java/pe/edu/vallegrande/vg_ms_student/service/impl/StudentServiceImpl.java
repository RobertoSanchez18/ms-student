package pe.edu.vallegrande.vg_ms_student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vg_ms_student.model.Student;
import pe.edu.vallegrande.vg_ms_student.repository.StudentRepository;
import pe.edu.vallegrande.vg_ms_student.service.StudentService;
import static pe.edu.vallegrande.vg_ms_student.util.Student.Activo;
import static pe.edu.vallegrande.vg_ms_student.util.Student.Inactivo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.modelmapper.ModelMapper;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    private final ModelMapper modelMapper =new ModelMapper();

    @Autowired
    public StudentServiceImpl (StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Flux<Student> listAllActive (){
        return studentRepository.findByStatus(Activo);
    }

    @Override
    public Flux<Student> listAllInactive (){
        return studentRepository.findByStatus(Inactivo);
    }

    @Override
    public Mono<Student> createStudent(Student student) {
        Student newStudent = modelMapper.map(student, Student.class);
        newStudent.setStatus(Activo);
        return studentRepository.save(newStudent);
    }

    @Override
    public Mono<Student> deleteStudent(String id) {
        return studentRepository.findById(id)
                .flatMap(existingStudent -> {
                    existingStudent.setStatus(Inactivo);
                    return studentRepository.save(existingStudent);
                });
    }

    @Override
    public Mono<Student> reactivateStudent(String id) {
        return studentRepository.findById(id)
                .flatMap(existingStudent -> {
                    existingStudent.setStatus(Activo);
                    return studentRepository.save(existingStudent);
                });
    }

    @Override
    public Mono<Student> updateStudent(String id, Student student) {
        return studentRepository.findById(id)
                .flatMap(existingStudent -> {
                    existingStudent.setName(student.getName());
                    existingStudent.setLastName(student.getLastName());
                    existingStudent.setDocumentType(student.getDocumentType());
                    existingStudent.setDocumentNumber(student.getDocumentNumber());
                    existingStudent.setGender(student.getGender());
                    existingStudent.setBirthDate(student.getBirthDate());
                    existingStudent.setBaptism(student.getBaptism());
                    existingStudent.setCommunion(student.getCommunion());
                    existingStudent.setEmail(student.getEmail());
                    existingStudent.setBirthPlace(student.getBirthPlace());
                    existingStudent.setLevel(student.getLevel());
                    existingStudent.setGrade(student.getGrade());
                    existingStudent.setSection(student.getSection());
                    return studentRepository.save(existingStudent);
                });
    }

    @Override
    public Mono<Student> findStudentById(String id) {
        return studentRepository.findById(id);
    }

}
