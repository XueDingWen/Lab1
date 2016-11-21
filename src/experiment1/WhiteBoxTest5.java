package experiment1;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class WhiteBoxTest5 
{	@Test
	public void test() throws IOException 
	{	lab1 lab=new lab1();
		lab.exp="x*x+y*x+z";lab.cmd="!simplify x=5 y=2";
		lab.validate(lab.cmd);
		String result=lab.simplify();
		assertEquals("35+1z",result);}}
