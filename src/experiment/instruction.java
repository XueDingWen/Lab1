package experiment;


public class instruction extends validate {
	public simplify s=new simplify();
	
	public void instruction(){
		if (cmd.startsWith("!simplify"))// 化简指令
		{
			String varibles[] = cmd.substring(10).split(" ");
			if (varibles[0].isEmpty())/* 判断是否没有变量赋值，如果没有给变量赋值，就直接输出该串即可 */
			{
				System.out.println(exp);
				return;
			}

			for (String string1 : varibles) {
				String varible4[] = string1.split("=");
				if (exp.indexOf(varible4[0]) == -1) { /* 这段代码看原表达式是否包含带入的变量 */
					System.out.println("Invaild simplification!");
					return;
				}

				else/* 这段是看赋值时等号右边是否是数字如果不是数字，那么赋值显然无效 */
				{
					try {
						Integer.parseInt(varible4[1]);
					} catch (NumberFormatException ex) {
						System.out.println("Invaild simplification!");
						return;
					}
				}
			}
			expression = exp;
			String[] variable = cmd.substring(10).split(" ");
			for (String element : variable) { /* 这段代码实现的功能是将赋值的值替换给原表达式中的变量 */
				String[] replacement = element.split("=");
				expression = expression.replaceAll(replacement[0],
						replacement[1]);
			}
			//simplify s=new simplify();
			s.expression=expression;
			//System.out.println(expression);
			//System.out.println(s.expression);
			s.simplify();
			return;
		}

		else if (cmd.startsWith("!d/d ")) {
			String varible = cmd.substring(5);
			if (exp.indexOf(varible) == -1) { /*
											 * 显然这段代码用来判断求导变量是否有效，同样，如果表达式是关于x的，
											 * 而求导变量为y，此时判断为无效
											 */
				System.out.println("Inavaild derivative!");
				return;
			} else {
				derivative d=new derivative();
				d.cmd=cmd;
				d.exp=exp;
				d.derivative();
				return;
			}
		}
	}

}
