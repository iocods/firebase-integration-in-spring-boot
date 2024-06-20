package iocode.web.app.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

  List<Student> findAll();

  Optional<Student> findByEmail(String email);
}
