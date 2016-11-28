package experiment;

import static org.junit.Assert.*;

import org.junit.Test;

public class blackboxtest1 {

	@Test
	public void test() {
		expression ex =new expression();
		ex.expression="2+x+y+x*x";
		boolean result=ex.expression();
		assertEquals(true,result);
	}

}
