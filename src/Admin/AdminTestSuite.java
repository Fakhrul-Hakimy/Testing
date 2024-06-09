package Admin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SearchPaper.class,
	AddReviewer.class,
	EditPaper.class,
    AddPaperReviewer.class,
    AcceptPaper.class
    
    
    
})
public class AdminTestSuite {
    // This class doesn't need any code, it only serves as a holder for the annotations
}
