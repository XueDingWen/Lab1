package experiment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class simplify extends operation {
	public ArrayList<integerstring> arraylist = new ArrayList<integerstring>();
	public Map<String, Integer> map = new HashMap<String, Integer>(); //map�����ϲ�ͬ����
	public StringBuilder stringbuff = new StringBuilder(); //stringbuilder�������ַ������ж�̬����
	
	public	String simplify()  
	{	
		String[] stringarray = expression.split("\\+");  //�������ԼӺŷָ�
		for (String str : stringarray)//��ÿ����ʽ���д���
		{ 	int constant = 1;
			int number1;
			List<String> varibles = new ArrayList<String>();/*����*/
			integerstring integerstring1 = new integerstring();
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
		
		for (integerstring intstr : arraylist)//�ϲ�ͬ����
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
		return stringbuff.toString();
		}
	
}
