package experiment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class simplify extends operation {
	public ArrayList<integerstring> arraylist = new ArrayList<integerstring>();
	public Map<String, Integer> map = new HashMap<String, Integer>(); //map用来合并同类项
	public StringBuilder stringbuff = new StringBuilder(); //stringbuilder用来对字符串进行动态操作
	
	public	String simplify()  
	{	
		String[] stringarray = expression.split("\\+");  //首先先以加号分割
		for (String str : stringarray)//对每个子式进行处理
		{ 	int constant = 1;
			int number1;
			List<String> varibles = new ArrayList<String>();/*变量*/
			integerstring integerstring1 = new integerstring();
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
		
		for (integerstring intstr : arraylist)//合并同类项
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
		return stringbuff.toString();
		}
	
}
