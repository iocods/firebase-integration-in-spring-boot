package iocode.web.app.service;

import iocode.web.app.dto.StudentDto;
import iocode.web.app.entity.Student;
import iocode.web.app.entity.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;
  public Student getByEmail(String studentEmail) {
    return studentRepository.findByEmail(studentEmail)
            .orElseThrow();
  }

  public Student saveStudent(StudentDto dto) {
    return studentRepository.save(mapToStudent(dto));
  }

  private Student mapToStudent(StudentDto dto){
    var hobbies = List.of(
        "Playing Football",
        "Reading Stories ",
        "Travelling",
        "Cooking"
    );
    return Student.builder()
        .email(dto.getEmail())
        .firstname(dto.getFirstname())
        .lastname(dto.getLastname())
        .about(dto.getAbout())
        .speech(dto.getSpeech())
        .contact(dto.getContact())
        .age(dto.getAge())
        .hobbies(hobbies)
        .build();
  }
}
