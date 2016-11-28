package experiment;

import static org.junit.Assert.*;

import org.junit.Test;

public class blackboxtest5 {

	@Test
	public void test() {
		expression e=new expression();
		e.expression="2+3*x++y";
		boolean result=e.expression();
		assertEquals(false,result);
	}

}
