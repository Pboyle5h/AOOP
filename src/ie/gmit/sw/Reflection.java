package ie.gmit.sw;

import java.lang.reflect.Method;

public class Reflection {
	private Class c;
	ReceiveJar rj = new ReceiveJar();
	int outDegree, inDegree= 0;
	public Reflection(Class c){
	      super();
	      this.c = c;
	      getMethods();

	   }	 
	
	public void getMethods() {
		Method[] methods = c.getMethods(); 
		Class[] methodParams;

		
		for(Method m : methods){

			System.out.println("Method Name: " + m.getName());
			System.out.println("Method Return Type: " + m.getReturnType());
			System.out.println("DeclaringClass = " + m.getDeclaringClass());
			 

			methodParams = m.getParameterTypes();
			for(Class mp : methodParams){

				System.out.println("Method Parameter: " + mp.getName());
			}
		}
	}

}
