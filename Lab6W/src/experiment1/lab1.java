package experiment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class IntegerString 
{	int number;
	String stringvarible;}//构造一个类，包括系数number和变量stringvariable

public class lab1
{	public static String cmd;//cmd表示指令
	public static String exp;//exp表示表达式
	public static String expression;//expression也表示表达式
	
	public static void main(String[] args) throws IOException 
	{	/*主函数执行读入表达式和指令的功能*/
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{	System.out.println("请输入表达式：");
			exp = buff.readLine();
			if(!Expression(exp))
				continue;
			System.out.println("请输入命令：");
			cmd = buff.readLine();
			validate(cmd);}}
	
	public static boolean Expression(String str) throws IOException/*表达式检查*/
	{	String F="0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		String Ch="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		String Cal="+*";
		
		if(F.indexOf(str.charAt(0))==-1)
		{	System.out.println("首字符非法");
			return false;}
		
		for(int i=1;i<str.length();i++)
		{	if( (F.indexOf(str.charAt(i))==-1) && (Cal.indexOf(str.charAt(i))==-1) )
			{	System.out.print("含有非法字符：");
				System.out.println(str.charAt(i));
				return false;}
		
			if( (Ch.indexOf(str.charAt(i))!=-1) && (Ch.indexOf(str.charAt(i-1))!=-1))
			{	System.out.println("含有多字母变量");
				return false;}
		
			if( (Cal.indexOf(str.charAt(i))!=-1) && (Cal.indexOf(str.charAt(i-1))!=-1))
			{	System.out.println("运算符相连");
				return false;}}
		
		return true;}
	
	public static void validate(String string) throws IOException 
	{	if (string.startsWith("!simplify"))//化简指令
		{	String varibles[] = string.substring(10).split(" ");
			if (varibles[0].isEmpty())/*判断是否没有变量赋值，如果没有给变量赋值，就直接输出该串即可*/
			{	System.out.println(exp);
				return;}
			
			for (String string1 : varibles) 
			{	String varible4[] = string1.split("=");
				if (exp.indexOf(varible4[0]) == -1) 
				{	/*这段代码看原表达式是否包含带入的变量*/
					System.out.println("Invaild simplification!");
					return;}
				
				else/*这段是看赋值时等号右边是否是数字如果不是数字，那么赋值显然无效*/
				{	try
					{	Integer.parseInt(varible4[1]);}
					catch(NumberFormatException ex)
					{	System.out.println("Invaild simplification!");
						return;}}}
			expression = exp;
			String[] variable = cmd.substring(10).split(" ");
			for (String element : variable) 
			{	/*这段代码实现的功能是将赋值的值替换给原表达式中的变量*/
				String[] replacement = element.split("=");
				expression = expression.replaceAll(replacement[0], replacement[1]);}
			simplify();return;}
		
		else if (string.startsWith("!d/d "))
		{	String varible = string.substring(5);
			if (exp.indexOf(varible) == -1) 
			{	/*显然这段代码用来判断求导变量是否有效，同样，如果表达式是关于x的，而求导变量为y，此时判断为无效*/
				System.out.println("Inavaild derivative!");
				return;}
			else
			{	derivative();return;}}}

	public static void derivative() throws IOException 
	{	String varibles2 = cmd.substring(5);//这条指令找到求导变量
		String stringarray2[] = exp.split("\\+");//以加号分割
		StringBuilder stringbuff3 = new StringBuilder();
		for (String element : stringarray2) 
		{	if (element.contains(varibles2)) 
			{	int constant;
				constant = 0;
				String elements[] = element.split("\\*");
				for (String varible : elements) 
				{	if (varible.equals(varibles2))//变量出现一次，constant就累加1
						constant++;}//最终累加得到x的个数
				element = element.replaceFirst(varibles2, Integer.toString(constant));
			stringbuff3.append(element);//把处理后的式子加入StringBuilder
			stringbuff3.append("+");}}
		int len1 = stringbuff3.length();
		stringbuff3.deleteCharAt(len1 - 1);//当然，在这里也要去掉最后一个加号
		expression = stringbuff3.toString();
		simplify();}//调用化简函数
	
	public static String simplify()  throws IOException
	{	ArrayList<IntegerString> arraylist = new ArrayList<IntegerString>();
		Map<String, Integer> map = new HashMap<String, Integer>(); //map用来合并同类项
		StringBuilder stringbuff = new StringBuilder(); //stringbuilder用来对字符串进行动态操作
		String[] stringarray = expression.split("\\+");  //首先先以加号分割
		
		for (String str : stringarray)//对每个子式进行处理
		{ 	int constant = 1;
			int number1;
			List<String> varibles = new ArrayList<String>();/*变量*/
			IntegerString integerstring1 = new IntegerString();
			StringBuilder stringbuff1 = new StringBuilder();
			String[] stringarray1 = str.split("\\*");/*按乘号分割*/
			for (String element : stringarray1) 
			{	if("0123456789".indexOf(element.charAt(0))!=-1)
				{	/*如果是数字，就乘起来*/
					number1 = Integer.parseInt(element);
					constant *= number1;} 
				else
				{	varibles.add(element);}}//如果是变量，就加到字符串数组里面
			
			for (String var : varibles)
			{	stringbuff1.append(var);}//将字符串数组中的变量加入StringBuilder
			
			integerstring1.stringvarible = stringbuff1.toString();//每一个式子的变量加入类中
			integerstring1.number = constant; //每个式子的数值加入类的数值中
			arraylist.add(integerstring1);}
		
		for (IntegerString intstr : arraylist)//合并同类项
		{ 	String key = intstr.stringvarible;
			int value = intstr.number;
			if (map.containsKey(key)==false) 
			{	map.put(key, value);}//如果变量不同，直接将键值对加入map 
			else if (map.containsKey(key)==true) 
			{	value += map.get(key); //如果变量相同，那么就将系数累加之后再加入map
				map.put(key, value);}}
		
		for (String keyinset : map.keySet())/*将每一对键值对加入StringBuilder中,动态处理字符串*/
		{	stringbuff.append(map.get(keyinset).toString() + keyinset + '+');}
		
		int len = stringbuff.length();
		stringbuff.deleteCharAt(len - 1);
		System.out.println(stringbuff.toString());
		return stringbuff.toString();}}