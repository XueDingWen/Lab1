package experiment;


public class instruction extends validate {
	public simplify s=new simplify();
	
	public void instruction(){
		if (cmd.startsWith("!simplify"))// ����ָ��
		{
			String varibles[] = cmd.substring(10).split(" ");
			if (varibles[0].isEmpty())/* �ж��Ƿ�û�б�����ֵ�����û�и�������ֵ����ֱ������ô����� */
			{
				System.out.println(exp);
				return;
			}

			for (String string1 : varibles) {
				String varible4[] = string1.split("=");
				if (exp.indexOf(varible4[0]) == -1) { /* ��δ��뿴ԭ���ʽ�Ƿ��������ı��� */
					System.out.println("Invaild simplification!");
					return;
				}

				else/* ����ǿ���ֵʱ�Ⱥ��ұ��Ƿ�����������������֣���ô��ֵ��Ȼ��Ч */
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
			for (String element : variable) { /* ��δ���ʵ�ֵĹ����ǽ���ֵ��ֵ�滻��ԭ���ʽ�еı��� */
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
											 * ��Ȼ��δ��������ж��󵼱����Ƿ���Ч��ͬ����������ʽ�ǹ���x�ģ�
											 * ���󵼱���Ϊy����ʱ�ж�Ϊ��Ч
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
