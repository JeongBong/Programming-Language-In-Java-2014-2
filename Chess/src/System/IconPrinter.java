package System;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class IconPrinter {

    public static void printUnicode(String code){
        PrintStream out;
        
		try {
			out = new PrintStream (System.out, true , "UTF8" );
			out.print(code);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
}