package experiment;

public class derivative extends operation {
	public StringBuilder stringbuff3 = new StringBuilder();

	public void derivative() {
		String varibles2 = cmd.substring(5);// ����ָ���ҵ��󵼱���
		String stringarray2[] = exp.split("\\+");// �ԼӺŷָ�
		for (String element : stringarray2) {
			if (element.contains(varibles2)) {
				int constant;
				constant = 0;
				String elements[] = element.split("\\*");
				for (String varible : elements) {
					if (varible.equals(varibles2))// ��������һ�Σ�constant���ۼ�1
						constant++;
				}// �����ۼӵõ�x�ĸ���
				element = element.replaceFirst(varibles2,
						Integer.toString(constant));
				stringbuff3.append(element);// �Ѵ�����ʽ�Ӽ���StringBuilder
				stringbuff3.append("+");
			}
		}
		int len1 = stringbuff3.length();
		stringbuff3.deleteCharAt(len1 - 1);// ��Ȼ��������ҲҪȥ�����һ���Ӻ�
		expression = stringbuff3.toString();
		simplify s=new simplify();
		s.expression=expression;
		s.simplify();
	}// ���û�����
}
