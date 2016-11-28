package experiment;

import static org.junit.Assert.*;

import org.junit.Test;

public class whiteboxtest5 {

	@Test
	public void test() {
		instruction i=new instruction();
		simplify s=new simplify();
		i.exp="x*x+y*x+z";
		i.expression=i.exp;
		i.cmd="!simplify x=5 y=2";
		i.instruction();
		s.expression=i.expression;
		String result=s.simplify();
		assertEquals("35+1z",result);
	}

}
