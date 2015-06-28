package uk.co.umbaska.Managers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FileManager {
	
	public void createFile(String file) {
		File f = new File(file);
		if (f.exists()) {
			return;
		} else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String[] loopFile(String file) {
		File f = new File(file);
		
		if (f.exists()) {
			return null;
		} else {
			LineIterator it = null;
			try {
				it = FileUtils.lineIterator(f, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Integer i = 0;
			try {
			    while (it.hasNext()) {
				    ++i;
			    }
			} finally {
			    it.close();
			}
			
			String[] s = new String[i];
			
			i = 0;
			try {
			    while (it.hasNext()) {
				    String line = it.nextLine();
				    s[i] = line;
				    ++i;
			    }
			} finally {
			    it.close();
			}
			
			return s;
		}
	}
	
	public void writeToFile(String file, String text) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(file), "utf-8"))) {
			writer.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
