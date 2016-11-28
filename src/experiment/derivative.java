package experiment;

public class derivative extends operation {
	public StringBuilder stringbuff3 = new StringBuilder();

	public void derivative() {
		String varibles2 = cmd.substring(5);// 这条指令找到求导变量
		String stringarray2[] = exp.split("\\+");// 以加号分割
		for (String element : stringarray2) {
			if (element.contains(varibles2)) {
				int constant;
				constant = 0;
				String elements[] = element.split("\\*");
				for (String varible : elements) {
					if (varible.equals(varibles2))// 变量出现一次，constant就累加1
						constant++;
				}// 最终累加得到x的个数
				element = element.replaceFirst(varibles2,
						Integer.toString(constant));
				stringbuff3.append(element);// 把处理后的式子加入StringBuilder
				stringbuff3.append("+");
			}
		}
		int len1 = stringbuff3.length();
		stringbuff3.deleteCharAt(len1 - 1);// 当然，在这里也要去掉最后一个加号
		expression = stringbuff3.toString();
		simplify s=new simplify();
		s.expression=expression;
		s.simplify();
	}// 调用化简函数
}
