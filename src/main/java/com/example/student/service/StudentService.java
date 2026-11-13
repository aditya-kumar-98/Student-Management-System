package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.exception.DuplicateEmailException;
import com.example.student.exception.StudentNotFoundException;
import com.example.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new DuplicateEmailException(student.getEmail());
        }
        return studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = getStudentById(id);

        studentRepository.findByEmail(updatedStudent.getEmail())
                .filter(student -> !student.getId().equals(id))
                .ifPresent(student -> { throw new DuplicateEmailException(updatedStudent.getEmail()); });

        existing.setName(updatedStudent.getName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setCourse(updatedStudent.getCourse());
        existing.setAge(updatedStudent.getAge());

        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByCourse(String course) {
        return studentRepository.findByCourseIgnoreCase(course);
    }
}
