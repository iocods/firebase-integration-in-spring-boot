package iocode.web.app.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
  private String firstname;
  private String lastname;
  private String middleName;
  private String email;
  private int age;
  private String course;
  private String about;
  private String speech;
  private String contact;
  private MultipartFile multipartFile;
}
