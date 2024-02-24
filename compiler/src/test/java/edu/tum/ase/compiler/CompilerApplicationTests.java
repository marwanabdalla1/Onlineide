package edu.tum.ase.compiler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompilerServiceUnitTest{

	@Autowired
	private CompilerService CompilerService;

	@MockBean
	private OSProcessExecutor OSProcessExecutor;

	@MockBean
	private FileSystemService FileSystemService;

	@Test
	public void should_CompileSuccesfully_When_CompilationsSucceeds(){

	    //GIVEN
	    SourceCode sourceCode = new SourceCode("Hello World");
	    Process mockProcess = mock(Process.class);

	    given(osProcessExecutor.executeCommand(anyString())).willReturn(mockProcess);
	    given(mockProcess.getInputStream()).willReturn(new ByteArrayInputStream("Compiler output".
		getBytes()));
        given(mockProcess.getErrorStream()).willReturn(new ByteArrayInputStream("".getBytes()));
        given(mockProcess.waitFor()).willReturn(0);

        //WHEN
        SourceCode result = compilerService.compile(sourceCode);

        //THEN
        assertTrue(result.isCompilable());
        assertEquals("Compiler output", result.getStdout());
	}

	@Test
    public void should_HandleCompilationError_When_CompilationFails() throws IOException,   
	InterruptedException {
        
		// given
        SourceCode sourceCode = new SourceCode("Hello World");
        Process mockProcess = mock(Process.class);

        given(osProcessExecutor.executeCommand(anyString())).willReturn(mockProcess);
        given(mockProcess.getInputStream()).willReturn(new ByteArrayInputStream("".getBytes()));
        given(mockProcess.getErrorStream()).willReturn(new ByteArrayInputStream("Compiler error output".getBytes()));
        given(mockProcess.waitFor()).willReturn(1);

        // when
        SourceCode result = compilerService.compile(sourceCode);

        // then
        assertFalse(result.isCompilable());
        assertEquals("Compiler error output", result.getStderr());
    }

}

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CompilerServiceIntegrationTest {

    @Autowired
    private CompilerService compilerService;

	 @Autowired
    private ProjectRepository systemUnderTest

    @AfterEach
    public void tearDown() {
        systemUnderTest.deleteAll();
    }

    @Test
    public void should_CompileSuccessfully_When_CompilationSucceeds() {
        
		//GIVEN
        SourceCode sourceCode = new SourceCode("Hello World");

        //WHEN
        SourceCode result = compilerService.compile(sourceCode);

        //THEN
        assertTrue(result.isCompilable());
        assertNotNull(result.getStdout());
        assertNull(result.getStderr());
    }

    @Test
    public void should_HandleCompilationError_When_CompilationFails() {

        //GIVEN
        SourceCode sourceCode = new SourceCode("code with compilation error");

        //WHEN
        SourceCode result = compilerService.compile(sourceCode);

        //THEN
        assertFalse(result.isCompilable());
        assertNull(result.getStdout());
        assertNotNull(result.getStderr());
    }
}

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectE2ERestTests {

    private final String URL = "compiler/src/main/java/edu/tum/ase/compiler/service";

    @Autowired
    private MockMvc systemUnderTest;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void tearDown() {
        projectRepository.deleteAll();
    }

    @Test
    public void should_ReturnPersistedProject_When_PostWithProject() throws Exception {
        
		//GIVEN
        Project project = new Project();
        project.setName("Test");
        Project createdProject = projectRepository.save(project);

        //WHEN
        ResultActions result = systemUnderTest.perform(post(URL)
                .content(objectMapper.writeValueAsString(project))
                .contentType(MediaType.APPLICATION_JSON));

        //THEN
        result
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(createdProject.getId()))
            .andExpect(jsonPath("$.name").value(createdProject.getName()));
        
        Project fetchedProject = projectRepository.findById(createdProject.getId()).orElse(null);
        assertEquals("Test", fetchedProject.getName());
    }
}
