package ie.gmit.sw;

import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;

public class Reflection {
	private Class cls;
	ReceiveJar rj = new ReceiveJar();
	int outdegree= 0;
	public Reflection(Class cls){
	      super();
	      this.cls = cls;
	      calculateMetric();
	      rj.Metrics.get(cls.getName()).setOutDegree(outdegree);

	   }	 
	
	public void getMethods(Class cls) {
		  Method[] methods = cls.getMethods(); 
	        Class[] methodParams;

	       
	        for(Method m : methods){

	        	System.out.println("Method Name: " + m.getName());
				System.out.println("Method Return Type: " + m.getReturnType());
				System.out.println("DeclaringClass = " + m.getDeclaringClass());

	            Class returnType = m.getReturnType(); 
	            

	            if(rj.Metrics.containsKey(returnType.getName())){

	                
	                outdegree++;

	            
	                metric met = rj.Metrics.get(returnType.getName());
	                met.setInDegree(met.getInDegree() + 1);
	            }

	            methodParams = m.getParameterTypes(); 
	            for(Class mp : methodParams){

	            	System.out.println("Method Parameter: " + mp.getName());

	                if(rj.Metrics.containsKey(mp.getName())){

	                    outdegree++;

	                    
	                    metric met = rj.Metrics.get(mp.getName());
	                    met.setInDegree(met.getInDegree() + 1);

	                } 
	            } 
	        } 
	}
	
	
	public void getFields(Class cls) {
		 Field[] fields = cls.getFields(); 

	        for(Field f : fields){
	        	 System.out.println("Field Name = " + f.getName());
		         System.out.println("Type = " + f.getType());
		         int mod = f.getModifiers();
		         System.out.println("Modifiers = " + Modifier.toString(mod));;

	            if(rj.Metrics.containsKey(f.getName())){

	                
	                outdegree++;

	         
	                metric met = rj.Metrics.get(f.getName());
	                met.setInDegree(met.getInDegree() + 1);

	            } 
	        } 
	}
	
	public void getInterfaces(Class cls){
		Class[] interfaces = cls.getInterfaces(); 
        
        for(Class i : interfaces){

            if(rj.Metrics.containsKey(i.getName())) {

               
                outdegree++;

                
                metric met = rj.Metrics.get(i.getName());
                met.setInDegree(met.getInDegree() + 1);

                

            } 
      

        } 
	}
	 public void getConstructors(Class cls){

	        Constructor[] cons = cls.getConstructors();
	        Class[] consParams;

	        
	        for(Constructor c : cons){

	            
	        	consParams = c.getParameterTypes(); 
	            for(Class param : consParams){

	                if(rj.Metrics.containsKey(param.getName())){	                    
	                    outdegree++;
	              
	                    metric met = rj.Metrics.get(param.getName());
	                    met.setInDegree(met.getInDegree() + 1);

	                } 
	                
	            } 
	        } 

	   }

	 public void getPackage(){
		 Package pack = cls.getPackage(); 
	     System.out.println("Package Name: " + pack.getName());
	}
	
	 
	 public void calculateMetric(){

	        try {

	            
	            File file = new File("src/string-service.jar");

	            
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
