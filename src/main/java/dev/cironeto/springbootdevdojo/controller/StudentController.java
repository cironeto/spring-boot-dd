package dev.cironeto.springbootdevdojo.controller;

import dev.cironeto.springbootdevdojo.domain.Student;
import dev.cironeto.springbootdevdojo.requests.StudentPostRequestBody;
import dev.cironeto.springbootdevdojo.requests.StudentPutRequestBody;
import dev.cironeto.springbootdevdojo.service.StudentService;
import dev.cironeto.springbootdevdojo.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("students")
public class StudentController {
    private DateUtil dateUtil;
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> list() {
        log.info(dateUtil.formatLocalDateTimetoDatebaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(studentService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable long id) {
        log.info(dateUtil.formatLocalDateTimetoDatebaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(studentService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody StudentPostRequestBody studentPostRequestBody) {
        return new ResponseEntity<>(studentService.save(studentPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Student> replace(@RequestBody StudentPutRequestBody studentPutRequestBody) {
        studentService.replace(studentPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
