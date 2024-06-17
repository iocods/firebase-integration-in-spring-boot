package iocode.web.app.controller;

import iocode.web.app.dto.StudentDto;
import iocode.web.app.entity.Student;
import iocode.web.app.entity.StudentRepository;
import iocode.web.app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;
  private final StudentRepository studentRepository;

  @GetMapping
  public String index() {
    return "redirect:/home";
  }

  @GetMapping({"/students", "/home"})
  public String home(Model model){
    model.addAttribute("dto", new StudentDto());
    model.addAttribute("newStudent", new StudentDto());
    return "index";
  }

  @PostMapping("/students/get")
  public String getStudent(@ModelAttribute("dto") StudentDto dto, Model model) {
    Student student = studentService.getByEmail(dto.getEmail());
    model.addAttribute("student", student);
    return "profile";
  }

  @PostMapping("/students/add")
  public String addStudent(@ModelAttribute("newStudent") StudentDto dto, Model model) {
    Student student = studentService.saveStudent(dto);
    model.addAttribute("student", student);
    if(dto.getMultipartFile().isEmpty()){
      System.out.println("File is not present");
    }
    return "profile";
  }
}
