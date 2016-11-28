package experiment;

import static org.junit.Assert.*;

import org.junit.Test;

public class blackboxtest4 {

	@Test
	public void test() {
		expression e=new expression();
		e.expression="3+x+xyz";
		boolean result=e.expression();
		assertEquals(false,result);
	}

}
