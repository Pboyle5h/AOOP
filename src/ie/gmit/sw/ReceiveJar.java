package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class ReceiveJar {
	private String pathName;

public static HashMap<String, metric> Metrics = new HashMap<>();

public ReceiveJar(String path) {
	 this.pathName = path;
	 getJar();
}

public void getJar(){
	metric m = new metric();

		try {			
			File file  = new File(pathName);
		
			 URL url = file.toURI().toURL();
	         URL[] urls = new URL[]{url};
	            
	         ClassLoader cl = new URLClassLoader(urls);
	         
	        JarInputStream in = new JarInputStream(new FileInputStream(pathName));
			JarEntry next = in.getNextJarEntry();
			while (next != null) {
			 if (next.getName().endsWith(".class")) {
			 String name = next.getName().replaceAll("/", "\\.");
			 name = name.replaceAll(".class", "");
			 if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
					
			
				try {
					Class cls = Class.forName(name, false, cl);
					//if statement to block out standard classes
					if(name.contains("org.")){
					
					}
					else{
						Metrics.put(name, new metric());
						Metrics.get(name).setName(name);
						Reflection ref = new Reflection(cls , pathName);
					}
					
				
				} catch (ClassNotFoundException e) {
					System.out.println("Couldn't find class "); 
				} 
			 
			 }
			 next = in.getNextJarEntry();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}




 

}
