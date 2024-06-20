package iocode.web.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import iocode.web.app.dto.StudentDto;
import iocode.web.app.entity.Student;
import iocode.web.app.entity.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

  @Value("${image.base.url}")
  private String imageBaseUrl;
  private final StudentRepository studentRepository;
  private final FirebaseService firebaseService;
  public Student getByEmail(String studentEmail) {
    return studentRepository.findByEmail(studentEmail)
            .orElseThrow();
  }

  public Student saveStudent(StudentDto dto) throws IOException, InterruptedException {
    String email = dto.getEmail();
    email = email.replace(".", "_");
    firebaseService.upload(dto.getMultipartFile(), email);
    String url = imageBaseUrl + email;
    dto.setImageUrl(getImageUrl(url));
    System.out.println("Image Url " + dto.getImageUrl());
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
        .imageUrl(dto.getImageUrl())
        .hobbies(hobbies)
        .build();
  }

  public String getImageUrl(String baseUrl) throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl))
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    String jsonResponse = response.body();
    JsonNode node = new ObjectMapper().readTree(jsonResponse);
    String imageToken = node.get("downloadTokens").asText();
    return baseUrl + "?alt=media&token=" + imageToken;
  }
}
