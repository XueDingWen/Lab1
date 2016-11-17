package experiment1;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ 	WhiteBoxTest1.class, 
	WhiteBoxTest2.class, 
	WhiteBoxTest3.class })

public class WhiteBoxTests {}