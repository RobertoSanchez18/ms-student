package pe.edu.vallegrande.vg_ms_student.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vg_ms_student.model.Student;
import pe.edu.vallegrande.vg_ms_student.service.impl.StudentServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class StudentRest {

    private  final StudentServiceImpl studentService;

    @Autowired
    public StudentRest(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/actives")
    public Flux <Student> getListAllActive(){
        return studentService.listAllActive();
    }

    @GetMapping("/inactive")
    public Flux <Student> getListAllInactive(){ return studentService.listAllInactive();
    }

    @PostMapping("/create")
    public Mono<Student> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Student>> deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/reactivate/{id}")
    public Mono<ResponseEntity<Student>> reactivateStudent(@PathVariable String id) {
        return studentService.reactivateStudent(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Student>> updateStudent(@PathVariable String id, @RequestBody Student student) {
        return studentService.updateStudent(id, student)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
}

    @GetMapping("/{id}")
    public Mono<Student> getStudentById(@PathVariable String id) {
        return studentService.findStudentById(id);
}

}