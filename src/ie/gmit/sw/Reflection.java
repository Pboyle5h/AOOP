package ie.gmit.sw;

import java.lang.reflect.*;

public class Reflection {
	private Class cls;
	ReceiveJar rj = new ReceiveJar();
	int outDegree, inDegree= 0;
	public Reflection(Class cls){
	      super();
	      this.cls = cls;
	      //getMethods();
	      //getFields() ;
	      //getConstructors();
	      getInterfaces();

	   }	 
	
	public void getMethods() {
		Method[] methods = cls.getDeclaredMethods(); 
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
		Field[] fields = cls.getDeclaredFields(); //Get the fields / attributes
		//Loop over the Fields and print the name of each
		for(Field f : fields){
			 System.out.println("Field Name = " + f.getName());
	         System.out.println("Type = " + f.getType());
	         int mod = f.getModifiers();
	         System.out.println("Modifiers = " + Modifier.toString(mod));
		}
	}
	
	public void getInterfaces(){
	 boolean iface=false;	
	 if(iface = cls.isInterface()){ 

     Class[] interfaces = cls.getInterfaces(); 
     	for(Class i : interfaces){
             System.out.println("Interfaces: " + i.getName());       

     	}
     
	 
	 }
	}
	 public void getConstructors(){
	      Constructor cons[] = cls.getConstructors();
	      
	      for (Constructor c : cons) {
	         System.out.println("Constructor  = " + c.getName());
	         Class[] Params = c.getParameterTypes();
	    
	         for (Class p : Params){
	            System.out.println("Params "+ p.getName());
	         }

	         
	      }
	   }

	 public void getPackage(){
		 Package pack = cls.getPackage(); 
	     System.out.println("Package Name: " + pack.getName());
	}

}
