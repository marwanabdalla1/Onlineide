package edu.tum.ase.compiler.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Service;

import edu.tum.ase.compiler.model.SourceCode;

@Service
public class CompilerService {
        public SourceCode compile (SourceCode sourceCode){
            
            try {


                //call the compile function
                Process process = compileJavaCode(sourceCode.getCode(), "App");
                
                // Return standard output and standard error
                String output = readStream(process.getInputStream());
                String errorOutput = readStream(process.getErrorStream());

                //Check if the compilation process was successful

                if (process.waitFor() == 0){
                    //Compilation is successful, set the variables of the sourceCode class
                    sourceCode.setCompilable(true);
                    sourceCode.setStdout(output);

                }
                else {
                    // Compilation failed, set the variables of the sourceCode class
                    sourceCode.setCompilable(false);
                    sourceCode.setStderr(errorOutput);

                }

                

            } catch (IOException | InterruptedException e) {
                //Handle exceptions
                e.printStackTrace();
                // edit the variables of the sourceCode class
                sourceCode.setCompilable(false);
                sourceCode.setStderr("Compilation failed due to an exception"+e.getMessage());
            }
            
            

            return sourceCode;
        }



        //main process, compile the code using the javac
        private Process compileJavaCode(String code, String className) throws IOException {
        // Utilize the javac compiler to compile the Java code from a string
        String javaFileName = className + ".java";
        File javaFile = new File(javaFileName);
    
        // Write the code to the Java file
        Files.write(javaFile.toPath(), code.getBytes(), StandardOpenOption.CREATE);
    
        // Compile the Java file
        return Runtime.getRuntime().exec("javac " + javaFile.getAbsolutePath());
    }
        


        private String readStream(InputStream inputStream) throws IOException {
        // Convert InputStream to String Additional Information from the javac compiler about what happened
        StringBuilder result = new StringBuilder();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            result.append(new String(buffer, 0, bytesRead));
        }
        return result.toString();
    }

    
    
    
}
