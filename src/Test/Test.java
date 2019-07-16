package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import cc.openhome.IO;
import cc.openhome.Novel;

public class Test {
	public static void main(String[] args) throws Exception{
//		byte[] e = Base64.getEncoder().encode("e0pn2ya1".getBytes());
//		System.out.println(new String(e));
//		byte[] d = Base64.getDecoder().decode(new String(e));
//		System.out.println(new String(d));
		Novel n = new Novel();
		if(n instanceof Novel)
			System.out.println("true");
		else
			System.out.println("false");
	}
}
