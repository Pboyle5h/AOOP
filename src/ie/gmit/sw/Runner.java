package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class Runner {

	public static void main(String[] args) {
		ReceiveJar rj = new ReceiveJar();
		rj.getJar();
		
	}
	
	

}
