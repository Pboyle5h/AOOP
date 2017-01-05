package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class ReceiveJar {

public static Map<Class, Set<Class>> graph = new HashMap();
public static void getJar(){
	 JarInputStream in;
		try {			
			File file  = new File("src/string-service.jar");
		
			 URL url = file.toURI().toURL();
	         URL[] urls = new URL[]{url};
	            
	         ClassLoader cl = new URLClassLoader(urls);
	         
	     	in = new JarInputStream(new FileInputStream(new File("src/string-service.jar")));
			JarEntry next = in.getNextJarEntry();
			while (next != null) {
			 if (next.getName().endsWith(".class")) {
			 String name = next.getName().replaceAll("/", "\\.");
			 name = name.replaceAll(".class", "");
			 if (!name.contains("$")) name.substring(0, name.length() - ".class".length());
					
			// inspect class
			 	
				try {
					Class cls = Class.forName(name, false, cl);
					System.out.println(name);
					//getClassName(cls);
				} catch (ClassNotFoundException e) {
					System.out.println("Couldn't find class '" + name + "'"); 
				} // reflection
			 
			 }
			 next = in.getNextJarEntry();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

 

}
