package ie.gmit.sw;

import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;

public class Reflection {
	private Class cls;
	ReceiveJar rj = new ReceiveJar();
	int outdegree, inDegree= 0;
	public Reflection(Class cls){
	      super();
	      this.cls = cls;
	      calculateBasicMetric();
	     rj.Metrics.get(cls.getName()).setOutDegree(outdegree);

	   }	 
	
	public void getMethods(Class cls) {
		  Method[] methods = cls.getMethods(); 
	        Class[] methodParams;

	       
	        for(Method m : methods){

	        	System.out.println("Method Name: " + m.getName());
				System.out.println("Method Return Type: " + m.getReturnType());
				System.out.println("DeclaringClass = " + m.getDeclaringClass());

	            Class methodReturnType = m.getReturnType(); 
	            

	            if(rj.Metrics.containsKey(methodReturnType.getName())){

	                
	                outdegree++;

	            
	                metric bm = rj.Metrics.get(methodReturnType.getName());
	                bm.setInDegree(bm.getInDegree() + 1);
	            }

	            methodParams = m.getParameterTypes(); 
	            for(Class mp : methodParams){

	            	System.out.println("Method Parameter: " + mp.getName());

	                if(rj.Metrics.containsKey(mp.getName())){

	                    outdegree++;

	                    
	                    metric bm = rj.Metrics.get(mp.getName());
	                    bm.setInDegree(bm.getInDegree() + 1);

	                } 
	            } 
	        } 
	}
	
	
	public void getFields(Class cls) {
		 Field[] fields = cls.getFields(); //Get the fields / attributes

	        for(Field f : fields){
	        	 System.out.println("Field Name = " + f.getName());
		         System.out.println("Type = " + f.getType());
		         int mod = f.getModifiers();
		         System.out.println("Modifiers = " + Modifier.toString(mod));;

	            if(rj.Metrics.containsKey(f.getName())){

	                
	                outdegree++;

	         
	                metric m = rj.Metrics.get(f.getName());
	                m.setInDegree(m.getInDegree() + 1);

	            } 
	        } 
	}
	
	public void getInterfaces(Class cls){
		Class[] interfaces = cls.getInterfaces(); 
        
        for(Class i : interfaces){

            if(rj.Metrics.containsKey(i.getName())) {

               
                outdegree++;

                
                metric m = rj.Metrics.get(i.getName());
                m.setInDegree(m.getInDegree() + 1);

                //System.out.println("Implements Interface: " + i.getName());

            } // if
            //System.out.println("Implements Interface: " + i.getName());

        } // foreach
	}
	 public void getConstructors(Class cls){

	        Constructor[] cons = cls.getConstructors(); //Get the set of constructors
	        Class[] constructorParams;

	        // for each constructor, get it's parameters
	        for(Constructor c : cons){

	            //System.out.println("Contructor: " + c.getName());
	            constructorParams = c.getParameterTypes(); //Get the parameters
	            for(Class param : constructorParams){

	                if(rj.Metrics.containsKey(param.getName())){

	                    // increment outdegree
	                    outdegree++;

	                    // increment indegree for other class
	                    metric m = rj.Metrics.get(param.getName());
	                    m.setInDegree(m.getInDegree() + 1);

	                } // if

	                //System.out.println("Constructor Param: " + param.getName());
	            } // foreach
	        } // foreach

	   }

	 public void getPackage(){
		 Package pack = cls.getPackage(); 
	     System.out.println("Package Name: " + pack.getName());
	}
	
	 
	 public void calculateBasicMetric(){

	        try {

	            
	            File file = new File("src/TestJar.jar");

	            
	            URL url = file.toURI().toURL();
	            URL[] urls = new URL[]{url};

	           
	            ClassLoader cl = new URLClassLoader(urls);

	           
	            for (String className : rj.Metrics.keySet()) {

	                
	                Class cls = Class.forName(className, false, cl);
	                getMethods(cls);
		      	    getFields(cls) ;
		      	    getConstructors(cls);
		      	    getInterfaces(cls);

	            } 
	        } catch (Exception e){

	            e.printStackTrace();
	        }

	    } 

}
