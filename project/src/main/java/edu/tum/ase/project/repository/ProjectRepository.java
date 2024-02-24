package edu.tum.ase.project.repository;
import edu.tum.ase.project.model.Project;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
  Optional<Project> findById(String projectId);
  ArrayList<Project> findAll();

  Project findByName(String name);

  @Override
  void deleteById(String s);

}

