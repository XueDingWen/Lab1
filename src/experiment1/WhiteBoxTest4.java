package experiment1;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class WhiteBoxTest4 
{	@Test
	public void test() throws IOException 
	{	lab1 lab=new lab1();
		lab.exp="x*y+y";
		lab.cmd="!simplify x=50";
		lab.validate(lab.cmd);
		String result=lab.simplify();
		assertEquals("51y",result);}}
