package uk.co.umbaska.Managers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.SecurityException; //import security exception
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class FileManager {
	
	public void createFile(String file) {
		File f = new File(file);
		if (f.exists()) {
			return;
		} else {
			try {
				f.getParentFile().mkdirs(); //Creates the directory if the directory doesn't exist, see http://docs.oracle.com/javase/7/docs/api/java/io/File.html#mkdirs%28%29 
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SecurityException s){ //if security exception is thrown
				s.printStackTrace; //print the stack trace
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
		
		if (!f.exists()) {
			return;
		} else {
			Scanner s = new Scanner(System.in);
	        Scanner numScan = null;
	        l = l - 1;
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
	        Integer size = null;
	        if (l > sss.size()) {
	        	Integer dif = l - sss.size();
	        	size = dif + sss.size();
	        } else {
	        	size = sss.size();
	        }
	        String[] ss = new String[size];
	        ss = sss.toArray(ss);
	        ss[l] = text;
	        deleteFile(file);
	        createFile(file);
	        for(int ii = 0; ii < ss.length; ii++) {
	        	if (ss[ii].isEmpty()) {
	        		writeToFile(file, "", true);
	        	} else {
	        		writeToFile(file, ss[ii], true);
	        	}
	        	System.out.println("Wrote: " + ss[ii]);
	        }
		}
	}
	
	public String getLineOfFile(String file, Integer l) {
		File f = new File(file);
		
		if (!f.exists()) {
			return null;
		} else if (l < 0) {
			return null;
		} else {
			Scanner s = new Scanner(System.in);
			l = l - 1;
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
	        if (ss[l].isEmpty()) {
	        	return null;
	        }
			return ss[l];
		}
	}
	
	
	public String[] getLinesOfFile(String file) {
		File f = new File(file);
		
		if (!f.exists()) {
			String[] ssss = new String[0];
			ssss[0] = "no file found";
			return ssss;
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
			
	
	public void writeToFile(String file, String text, Boolean overwrite) {
			if (fileExists(file)) {
				Writer output = null;
				try {
					if (overwrite == true) {
						output = new BufferedWriter(new FileWriter(file, overwrite));
					} else {
						output = new BufferedWriter(new FileWriter(file));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}  //clears file every time
				try {
					output.append(text + System.getProperty("line.separator"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					output.close();
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
