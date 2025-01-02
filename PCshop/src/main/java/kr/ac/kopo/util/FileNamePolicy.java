package kr.ac.kopo.util;

import java.io.File;
import java.util.UUID;

public class FileNamePolicy {

	public File rename(File f) {
		String name = f.getName();
		String ext = "";
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			ext = name.substring(dot); 
		} else {
			ext = "";
		}
		
		String str = "pcshop" + UUID.randomUUID();
		File renameFile = new File(f.getParent(), str + ext);
		return renameFile;
	}
	
	public String rename(String fName) {
		String name = fName;
		String ext = "";
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			ext = name.substring(dot); 
		} else {
			ext = "";
		}
		
		String str = "pcshop" + UUID.randomUUID();
		//String renameFile = new File(f.getParent(), str + ext);
		String renameFile = str + ext; 
		return renameFile;
	}
}