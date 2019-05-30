package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.io.Reader;

import cc.openhome.DES;

public class Test {
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("C:\\Users\\050303\\Desktop\\detailLog.20190530.0.txt");
			FileWriter fw = new FileWriter("C:\\Users\\050303\\Desktop\\test.txt");
			BufferedReader reader = new BufferedReader(fr);
			String line;
			String sql;
			DES des = new DES();
			while (reader.ready()) {
				line = reader.readLine();
				sql = des._DecryptByDES(line.substring(line.indexOf("ivr_detail_log - ")+17,line.indexOf("#")),"4F6947A77C96DEAB");
				fw.write(sql);
				fw.write("\r\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
