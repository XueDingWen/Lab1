package experiment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IntegerString {
	int number;
	String stringvarible;//构造一个类，包括系数number和变量stringvariable
}

public class lab1 {
	public static String cmd;//cmd表示指令
	public static String exp;//exp表示表达式
	public static String expression;//expression也表示表达式

	public static void main(String[] args) throws IOException {
		/*
		 * 主函数执行读入表达式和指令的功能 
		 */
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		exp = buff.readLine();
		cmd = buff.readLine();
		validate(cmd);
	}

	public static void expression(String string) throws IOException {
		/*
		 * expression函数执行打印字符串
		 */
		System.out.println(string);
	}

	private static void simplify() throws IOException {
		ArrayList<IntegerString> arraylist = new ArrayList<IntegerString>();
		Map<String, Integer> map = new HashMap<String, Integer>();//map用来合并同类项
		StringBuilder stringbuff = new StringBuilder();//stringbuilder用来对字符串进行动态操作
		String stringarray[] = expression.split("\\+");//首先先以加号分割
		for (String str : stringarray) {//对每个加号连接的式子进行划尖
			int constant = 1;
			int number1;
			List<String> varibles = new ArrayList<String>();
			String stringarray1[] = str.split("\\*");
			IntegerString integerstring1 = new IntegerString();
			StringBuilder stringbuff1 = new StringBuilder();
			for (String element : stringarray1) {
				try {
					/*
					 * 如果该变量是数字，就乘起来
					 */
					number1 = Integer.parseInt(element);
					constant *= number1;
				} catch (Exception e) {
					varibles.add(element);//如果是变量，就加到字符串数组里面
				}
			}
			for (String var : varibles) {
				stringbuff1.append(var);//将字符串数组中的变量加入StringBuilder
			}
			integerstring1.stringvarible = stringbuff1.toString();//每一个式子的变量加入类的未知数变量中
			integerstring1.number = constant;//每个式子的数值加入类的数值中
			arraylist.add(integerstring1);
		}
		for (IntegerString intstr : arraylist) {//合并同类项
			String key = intstr.stringvarible;
			int value = intstr.number;
			if (map.containsKey(key) == false) {
				map.put(key, value);//如果系数不同，直接将键值对加入map
			} else if (map.containsKey(key) == true) {
				value += map.get(key);//如果系数相同，那么就将系数累加之后再加入map
				map.put(key, value);
			}
		}
		for (String keyinset : map.keySet()) {
			/*
			 * 将每一对键值对加入StringBuilder中
			 * 动态处理字符串
			 */
			stringbuff.append(map.get(keyinset).toString() + keyinset + '+');
		}
		int len = stringbuff.length();
		stringbuff.deleteCharAt(len - 1);//去掉最后一个加号
		expression(stringbuff.toString());
	}

	public static void derivative() throws IOException {
		String varibles2 = cmd.substring(5);//这条指令找到求导变量
		String stringarray2[] = exp.split("\\+");//以加号分割
		StringBuilder stringbuff3 = new StringBuilder();
		for (String element : stringarray2) {
			if (element.contains(varibles2)) {
				if(element.contains("^")){
					/*
					 * 这段代码处理用^表示乘方的情况
					 */
					int len3=element.indexOf("^");
					String number2=element.substring(len3 +1, len3 +2);//截取^后面的数值
					stringbuff3.append(number2).append("*");//将指数先加入StringBuilder
					int number3=Integer.parseInt(number2)-1;
					element=element.replace(number2, Integer.toString(number3));//求导之后将指数减一
				}
				else{
					/*
					 * 这段处理正常乘号*连接的变量求导
					 */
					int constant;
					constant = 0;
					String elements[] = element.split("\\*");
					for (String varible : elements) {
						if (varible.equals(varibles2))//变量出现一次，constant就累加1
							constant++;//最终累加得到x的个数
					}
					element = element.replaceFirst(varibles2, Integer.toString(constant));
				}
				stringbuff3.append(element);//把处理后的式子加入StringBuilder
				stringbuff3.append("+");
			}
		}
		int len1 = stringbuff3.length();
		stringbuff3.deleteCharAt(len1 - 1);//当然，在这里也要去掉最后一个加号
		expression = stringbuff3.toString();
		simplify();//调用化简函数
	}

	public static void validate(String string) throws IOException {
		if (string.startsWith("!simplify")) {//化简指令
			String varibles[] = string.substring(10).split(" ");
			if (varibles[0].isEmpty()) {
				/*
				 * 判断是否没有变量赋值
				 * 如果没有给变量赋值
				 * 就直接输出该串即可
				 */
				expression(exp);
				return;
			}
			for (String string1 : varibles) {
				String varible4[] = string1.split("=");
				if ((exp.indexOf(varible4[0]) == -1)) {
					/*
					 * 这段代码看原表达式是否包含带入的变量
					 * 比如原表达式是关于x的多项式
					 * 而带入的值是y=3
					 * 此时带入值无效
					 */
					System.out.println("Invaild simplification!");
					return;
				} 
				else
				{
					/*
					 * 这段是看赋值时等号右边是否是数字
					 * 如果不是数字，那么赋值显然无效
					 */
					try
					{
						Integer.parseInt(varible4[1]);
					}
					catch(NumberFormatException ex){
						System.out.println("Invaild simplification!");
						return;
					}
				}
			}
			expression = exp;
			String[] variable = cmd.substring(10).split(" ");
			for (String element : variable) {
				/*
				 * 这段代码实现的功能是将赋值的值替换给原表达式中的变量
				 */
				String[] replacement = element.split("=");
				expression = expression.replaceAll(replacement[0], replacement[1]);
			}
			simplify();
		} else if (string.startsWith("!d/d ")) {//求导指令
			String varible = string.substring(5);
			if (exp.indexOf(varible) == -1) {
				/*
				 * 显然这段代码用来判断求导变量是否有效
				 * 同样，如果表达式是关于x的，而求导变量为y
				 * 此时判断为无效
				 */
				System.out.println("Inavaild derivative!");
				return;
			} else
				derivative();
		}
	}
}
