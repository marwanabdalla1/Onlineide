package edu.tum.ase.project.service;
import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.model.SourceFile;
import edu.tum.ase.project.repository.ProjectRepository;
import edu.tum.ase.project.repository.SourceFileRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
    public class SourceFileService {

        @Autowired
        private SourceFileRepository sourceFileRepository;

        @Autowired
        private ProjectRepository projectRepository;



    public Optional<SourceFile> findbyId(String id) {
        return sourceFileRepository.findById(id);
    }


    public ResponseEntity<SourceFile> updateSourceFile (String SourceFileId, SourceFile updatedSourceFile){
        Optional<SourceFile> existingSourceFileOptional = sourceFileRepository.findById(SourceFileId);

        if (existingSourceFileOptional.isPresent()) {
            SourceFile existingSourceFile = existingSourceFileOptional.get();

            existingSourceFile.setFileName(updatedSourceFile.getFileName());
            existingSourceFile.setSourceCode(updatedSourceFile.getSourceCode());

            SourceFile updatedSourceFileResult = sourceFileRepository.save(existingSourceFile);

            return new ResponseEntity<>(updatedSourceFile, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public boolean deleteSourceFileById(String SourceFileId) {
        Optional<SourceFile> projectOptional = sourceFileRepository.findById(SourceFileId);

        if (projectOptional.isPresent()) {
            sourceFileRepository.deleteById(SourceFileId);
            return true;
        }
        else {
            return false;
        }
    }

    public SourceFile createSourceFile(SourceFile sourceFile, String projectId) {
        // Check if the project with the given ID exists
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            // Link the source file to the project
            sourceFile.setProject(project);
            return sourceFileRepository.save(sourceFile);
        } else {
            throw new EntityNotFoundException("Project with ID " + projectId + " not found");
        }
    }


        public SourceFile findByName(String fileName) { 
            return sourceFileRepository.findByFileName(fileName);
        }


        public ArrayList<SourceFile> getSourceFiles() { 
            return sourceFileRepository.findAll();
        } 
}
