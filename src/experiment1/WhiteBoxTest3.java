package experiment1;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class WhiteBoxTest3 
{	@Test
	public void test() throws IOException 
	{	lab1 lab=new lab1();
		lab.exp="x*x+y*x*z+z+4*z";
		lab.cmd="!simplify x=5 y=2 z=3";
		lab.validate(lab.cmd);
		String result=lab.simplify();
		assertEquals("70",result);}}
