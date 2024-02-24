package edu.tum.ase.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "project_source_file")
public class SourceFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "project_id", nullable = false)
    // private String projectId;  // foreign referencing the project

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Lob
    @Column(name = "source_code", nullable = false, columnDefinition = "TEXT")
    private String sourceCode;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;


 

    protected SourceFile() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }
    
    // getters and setters


    // will not be manually setting project id

    //will take it as a jason object however


  
   

//    public String getProjectId(){
//        return project.getId();
//    }

    public String getFileName(){
        return fileName;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getSourceCode(){
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
}

