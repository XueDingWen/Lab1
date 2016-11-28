package experiment;

import static org.junit.Assert.*;

import org.junit.Test;

public class whiteboxtest3 {

	@Test
	public void test() {
		instruction i=new instruction();
		simplify s=new simplify();
		i.exp="x*x+y*x*z+z+4*z";
		i.expression=i.exp;
		i.cmd="!simplify x=5 y=2 z=3";
		i.instruction();
		s.expression=i.expression;
		String result=s.simplify();
		assertEquals("70",result);
	}

}
