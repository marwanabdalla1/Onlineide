package edu.tum.ase.project.repository;
import edu.tum.ase.project.model.Project;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository; import org.springframework.stereotype.Repository;


/**
 * Note that Spring provides a variety of Repository abstractions, JpaRepository is
     technology-specific
 * see https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#repositories
 */

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
  Project findByName(String name);
  ArrayList<Project> findAll();
}

