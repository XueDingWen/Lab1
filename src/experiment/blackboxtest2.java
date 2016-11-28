package experiment;

import static org.junit.Assert.*;

import org.junit.Test;

public class blackboxtest2 {

	@Test
	public void test() {
		expression e=new expression();
		e.expression="+3+x";
		boolean result=e.expression();
		assertEquals(false,result);
	}

}
