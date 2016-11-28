package experiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String exp = null, cmd=null;
		BufferedReader buff = new BufferedReader(new InputStreamReader(
				System.in));
		while (true) {
			System.out.println("请输入表达式：");
			try {
				exp = buff.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("请输入命令：");
			try {
				cmd = buff.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			control ct = new control();
			ct.exp = exp;
			ct.expression=exp;
			ct.cmd=cmd;
			ct.control();
		}
		/*
		 * while (true) { expression e = new expression(); instruction i=new
		 * instruction(); System.out.println("请输入表达式："); try { e.exp =
		 * buff.readLine(); } catch (IOException e1) { // TODO Auto-generated
		 * catch block e1.printStackTrace(); }
		 * 
		 * e.expression=e.exp; if (!e.expression()) continue;
		 * System.out.println("请输入命令：");
		 * 
		 * try { i.cmd = buff.readLine(); } catch (IOException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); } i.exp=e.exp;
		 * i.expression=e.expression; i.instruction(); }
		 */
	}

}
