package experiment;

public class expression extends validate {
	public String F = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
	public String Ch = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
	public String Cal = "+*";
	public boolean expression() {
		if (F.indexOf(expression.charAt(0)) == -1) {
			System.out.println("���ַ��Ƿ�");
			return false;
		}

		for (int i = 1; i < expression.length(); i++) {
			if ((F.indexOf(expression.charAt(i)) == -1)
					&& (Cal.indexOf(expression.charAt(i)) == -1)) {
				System.out.print("���зǷ��ַ���");
				System.out.println(expression.charAt(i));
				return false;
			}

			if ((Ch.indexOf(expression.charAt(i)) != -1)
					&& (Ch.indexOf(expression.charAt(i - 1)) != -1)) {
				System.out.println("���ж���ĸ����");
				return false;
			}
			if ((Cal.indexOf(expression.charAt(i)) != -1)
					&& (Cal.indexOf(expression.charAt(i - 1)) != -1)) {
				System.out.println("���������");
				return false;
			}
		}
		return true;
	}
}
