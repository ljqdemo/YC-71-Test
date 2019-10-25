package text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test11 {

	public static void main(String[] args) {
		String reg="^([a-zA-Z]|[0-9])(w|-)+@[a-zA-Z0-9]+.([a-zA-Z]{2,4})$";
		boolean b=startCheck(reg,"123456789@qq.com");
		System.out.println(b);
	}
	 public static boolean startCheck(String reg, String string) {

	  boolean tem = false;
	  Pattern pattern = Pattern.compile(reg);
	  Matcher matcher = pattern.matcher(string);
	  tem = matcher.matches();
	  return tem;
	 }
	
	 

}
