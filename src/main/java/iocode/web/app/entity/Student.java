package iocode.web.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;
  @Column(nullable = false)
  private String firstname;
  @Column(nullable = false)
  private String lastname;
  private String middleName;
  @Column(unique = true)
  private String email;
  private int age;
  private String course;
  @Column(nullable = false, length = 5000)
  private String about;
  @Column(nullable = false, length = 5000)
  private String speech;
  private String contact;
  private List<String> hobbies;
}
