import net.sf.javaml.core.Dataset;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    private App app;
    
    @Before
    public void setUp() {
        app = new App();
    }
    
    @Test
    public void testLoadData() {
        String filePath = "iris.data";
        try {
            Dataset data = app.loadData(filePath); // Ensure this method exists in your App class
            assertNotNull("Dataset should not be null", data);
            assertFalse("Dataset should not be empty", data.isEmpty());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }
    
    // Future tests for clustering could go here, after refactoring your App.java to make it more testable
}

