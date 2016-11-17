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
	String stringvarible;}//����һ���࣬����ϵ��number�ͱ���stringvariable

public class lab1
{	public static String cmd;//cmd��ʾָ��
	public static String exp;//exp��ʾ���ʽ
	public static String expression;//expressionҲ��ʾ���ʽ
	
	public static void main(String[] args) throws IOException 
	{	/*������ִ�ж�����ʽ��ָ��Ĺ���*/
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{	System.out.println("��������ʽ��");
			exp = buff.readLine();
			if(!Expression(exp))
				continue;
			System.out.println("���������");
			cmd = buff.readLine();
			validate(cmd);}}
	
	public static boolean Expression(String str) throws IOException/*���ʽ���*/
	{	String F="0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		String Ch="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		String Cal="+*";
		
		if(F.indexOf(str.charAt(0))==-1)
		{	System.out.println("���ַ��Ƿ�");
			return false;}
		
		for(int i=1;i<str.length();i++)
		{	if( (F.indexOf(str.charAt(i))==-1) && (Cal.indexOf(str.charAt(i))==-1) )
			{	System.out.print("���зǷ��ַ���");
				System.out.println(str.charAt(i));
				return false;}
		
			if( (Ch.indexOf(str.charAt(i))!=-1) && (Ch.indexOf(str.charAt(i-1))!=-1))
			{	System.out.println("���ж���ĸ����");
				return false;}
		
			if( (Cal.indexOf(str.charAt(i))!=-1) && (Cal.indexOf(str.charAt(i-1))!=-1))
			{	System.out.println("���������");
				return false;}}
		
		return true;}
	
	public static void validate(String string) throws IOException 
	{	if (string.startsWith("!simplify"))//����ָ��
		{	String varibles[] = string.substring(10).split(" ");
			if (varibles[0].isEmpty())/*�ж��Ƿ�û�б�����ֵ�����û�и�������ֵ����ֱ������ô�����*/
			{	System.out.println(exp);
				return;}
			
			for (String string1 : varibles) 
			{	String varible4[] = string1.split("=");
				if (exp.indexOf(varible4[0]) == -1) 
				{	/*��δ��뿴ԭ���ʽ�Ƿ��������ı���*/
					System.out.println("Invaild simplification!");
					return;}
				
				else/*����ǿ���ֵʱ�Ⱥ��ұ��Ƿ�����������������֣���ô��ֵ��Ȼ��Ч*/
				{	try
					{	Integer.parseInt(varible4[1]);}
					catch(NumberFormatException ex)
					{	System.out.println("Invaild simplification!");
						return;}}}
			expression = exp;
			String[] variable = cmd.substring(10).split(" ");
			for (String element : variable) 
			{	/*��δ���ʵ�ֵĹ����ǽ���ֵ��ֵ�滻��ԭ���ʽ�еı���*/
				String[] replacement = element.split("=");
				expression = expression.replaceAll(replacement[0], replacement[1]);}
			simplify();return;}
		
		else if (string.startsWith("!d/d "))
		{	String varible = string.substring(5);
			if (exp.indexOf(varible) == -1) 
			{	/*��Ȼ��δ��������ж��󵼱����Ƿ���Ч��ͬ����������ʽ�ǹ���x�ģ����󵼱���Ϊy����ʱ�ж�Ϊ��Ч*/
				System.out.println("Inavaild derivative!");
				return;}
			else
			{	derivative();return;}}}

	public static void derivative() throws IOException 
	{	String varibles2 = cmd.substring(5);//����ָ���ҵ��󵼱���
		String stringarray2[] = exp.split("\\+");//�ԼӺŷָ�
		StringBuilder stringbuff3 = new StringBuilder();
		for (String element : stringarray2) 
		{	if (element.contains(varibles2)) 
			{	int constant;
				constant = 0;
				String elements[] = element.split("\\*");
				for (String varible : elements) 
				{	if (varible.equals(varibles2))//��������һ�Σ�constant���ۼ�1
						constant++;}//�����ۼӵõ�x�ĸ���
				element = element.replaceFirst(varibles2, Integer.toString(constant));
			stringbuff3.append(element);//�Ѵ�����ʽ�Ӽ���StringBuilder
			stringbuff3.append("+");}}
		int len1 = stringbuff3.length();
		stringbuff3.deleteCharAt(len1 - 1);//��Ȼ��������ҲҪȥ�����һ���Ӻ�
		expression = stringbuff3.toString();
		simplify();}//���û�����
	
	public static String simplify()  throws IOException
	{	ArrayList<IntegerString> arraylist = new ArrayList<IntegerString>();
		Map<String, Integer> map = new HashMap<String, Integer>(); //map�����ϲ�ͬ����
		StringBuilder stringbuff = new StringBuilder(); //stringbuilder�������ַ������ж�̬����
		String[] stringarray = expression.split("\\+");  //�������ԼӺŷָ�
		
		for (String str : stringarray)//��ÿ����ʽ���д���
		{ 	int constant = 1;
			int number1;
			List<String> varibles = new ArrayList<String>();/*����*/
			IntegerString integerstring1 = new IntegerString();
			StringBuilder stringbuff1 = new StringBuilder();
			String[] stringarray1 = str.split("\\*");/*���˺ŷָ�*/
			for (String element : stringarray1) 
			{	if("0123456789".indexOf(element.charAt(0))!=-1)
				{	/*��������֣��ͳ�����*/
					number1 = Integer.parseInt(element);
					constant *= number1;} 
				else
				{	varibles.add(element);}}//����Ǳ������ͼӵ��ַ�����������
			
			for (String var : varibles)
			{	stringbuff1.append(var);}//���ַ��������еı�������StringBuilder
			
			integerstring1.stringvarible = stringbuff1.toString();//ÿһ��ʽ�ӵı�����������
			integerstring1.number = constant; //ÿ��ʽ�ӵ���ֵ���������ֵ��
			arraylist.add(integerstring1);}
		
		for (IntegerString intstr : arraylist)//�ϲ�ͬ����
		{ 	String key = intstr.stringvarible;
			int value = intstr.number;
			if (map.containsKey(key)==false) 
			{	map.put(key, value);}//���������ͬ��ֱ�ӽ���ֵ�Լ���map 
			else if (map.containsKey(key)==true) 
			{	value += map.get(key); //���������ͬ����ô�ͽ�ϵ���ۼ�֮���ټ���map
				map.put(key, value);}}
		
		for (String keyinset : map.keySet())/*��ÿһ�Լ�ֵ�Լ���StringBuilder��,��̬�����ַ���*/
		{	stringbuff.append(map.get(keyinset).toString() + keyinset + '+');}
		
		int len = stringbuff.length();
		stringbuff.deleteCharAt(len - 1);
		System.out.println(stringbuff.toString());
		return stringbuff.toString();}}