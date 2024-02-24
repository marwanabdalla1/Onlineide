package edu.tum.ase.project.controller;

import edu.tum.ase.project.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.tum.ase.project.model.SourceFile;
import edu.tum.ase.project.service.SourceFileService;;import java.util.Optional;


@RestController
@RequestMapping("/sourcefiles")


public class SourceFileController {


    @Autowired
    private SourceFileService sourceFileService;



    @RequestMapping(path = "/getSourceFile/{SourceFileId}", method = RequestMethod.GET)
    public Optional<SourceFile> getSourceFile(@PathVariable String SourceFileId) {
        return sourceFileService.findbyId(SourceFileId);
    }
    @PostMapping("/create/{id}") 
    public ResponseEntity<SourceFile> createSourceFile (@RequestBody SourceFile sourceFile, @PathVariable String id){

        SourceFile createdSourceFile = sourceFileService.createSourceFile(sourceFile, id);
        return new ResponseEntity<>(createdSourceFile, HttpStatus.CREATED);
        
    }

    @PutMapping (path = "/updatesourcefile/{SourceFileId}")
    public ResponseEntity<SourceFile> updateProject (@PathVariable String SourceFileId, @RequestBody SourceFile updatedSourceFile) {

        return sourceFileService.updateSourceFile(SourceFileId, updatedSourceFile);

    }


    @DeleteMapping("/deleteSourceFile/{SourceFileId}")
    public ResponseEntity<String> deleteProject(@PathVariable String SourceFileId) {
        boolean deleted = sourceFileService.deleteSourceFileById(SourceFileId);

        if (deleted) {
            return new ResponseEntity<>("Project deleted successfuly", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Project not found or could not be delted", HttpStatus.NOT_FOUND);
        }
    }
    


}
