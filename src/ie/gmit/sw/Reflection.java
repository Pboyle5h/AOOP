package ie.gmit.sw;

import java.lang.reflect.*;

public class Reflection {
	private Class cls;
	ReceiveJar rj = new ReceiveJar();
	int outDegree, inDegree= 0;
	public Reflection(Class cls){
	      super();
	      this.cls = cls;
	      getMethods();
	      getFields() ;

	   }	 
	
	public void getMethods() {
		Method[] methods = cls.getMethods(); 
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
	
	public void getFields() {
		Field[] fields = cls.getFields(); //Get the fields / attributes
		//Loop over the Fields and print the name of each
		for(Field f : fields){

			System.out.println("Field Name: " + f.getName());
		}
	}
}
