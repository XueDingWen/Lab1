package experiment;

import static org.junit.Assert.*;

import org.junit.Test;

public class whiteboxtest4 {

	@Test
	public void test() {
		instruction i=new instruction();
		simplify s=new simplify();
		i.exp="x*y+y";
		i.expression=i.exp;
		i.cmd="!simplify x=50";
		i.instruction();
		s.expression=i.expression;
		String result=s.simplify();
		assertEquals("51y",result);
	}

}
