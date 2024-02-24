package edu.tum.ase.project.repository;

import edu.tum.ase.project.model.SourceFile;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceFileRepository extends JpaRepository<SourceFile, String> {
    SourceFile findByFileName(String fileName);
    ArrayList<SourceFile> findAll();
//     Optional<SourceFile> findById(String id);

}
