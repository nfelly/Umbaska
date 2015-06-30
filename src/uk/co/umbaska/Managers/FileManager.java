package uk.co.umbaska.Managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	public void deleteFile(String file) {
		File f = new File(file);
		if (f.exists()) {
			f.delete();
		} else {
			return;
		}
	}
	
	public void setLineOfFile(String file, String text, Integer l) {
		File f = new File(file);
		
		if (f.exists()) {
			return;
		} else {
			LineIterator it = null;
			try {
				it = FileUtils.lineIterator(f, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Integer i = -1;
			try {
			    while (it.hasNext()) {
				    ++i;
			    }
			} finally {
			    it.close();
			}
			
			String[] s = new String[i];
			
			i = -1;
			try {
			    while (it.hasNext()) {
				    String line = it.nextLine();
				    s[i] = line;
				    ++i;
			    }
			} finally {
			    it.close();
			}
			
			s[l] = text;
			for (int ii = -1; ii < s.length; ii++) {
				writeToFile(file, s[ii]);
			}
		}
	}
	
	public String getLineOfFile(String file, Integer l) {
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
			Integer i = -1;
			try {
			    while (it.hasNext()) {
				    ++i;
			    }
			} finally {
			    it.close();
			}
			
			String[] s = new String[i];
			
			i = -1;
			try {
			    while (it.hasNext()) {
				    String line = it.nextLine();
				    s[i] = line;
				    ++i;
			    }
			} finally {
			    it.close();
			}
			return s[l];
		}
	}
	
	
	public String[] getLinesOfFile(String file) {
		File f = new File(file);
		
		if (!f.exists()) {
			return null;
		} else {
			Scanner s = new Scanner(System.in);
	        Scanner numScan = null;
			try {
				numScan = new Scanner(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	         
	        String line;
	        List<String> sss = new ArrayList<String>();
	        while (numScan.hasNext())
	        {
	            line = numScan.nextLine();
	            sss.add(line);
	        }
	        numScan.close();
	        s.close();
	        String[] ss = new String[sss.size()];
	        ss = sss.toArray(ss);
			return ss;
		}
	}
			
	
	public void writeToFile(String file, String text) {
			if (fileExists(file)) {
				try {
					Files.write(Paths.get(file), text.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
	}
	
	public boolean fileExists(String file) {
		File f = new File(file);
		if (f.exists()) {
			return true;
		} else {
			return false;
		}
	}
}
