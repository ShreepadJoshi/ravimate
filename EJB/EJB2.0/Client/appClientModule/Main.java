import java.util.Properties;

import headfirst.Advice;
import headfirst.AdviceHome;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;


public class Main {
	public static void main(String[] args) {
		new Main().go();
	}
	
	public void go(){
		try{
			Properties pro = new Properties();
			pro.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
			pro.put("java.naming.provider.url","localhost:1099");
			pro.put("java.naming.factory.url.pkgs","org.jboss.naming");		
			
			Context ic = new InitialContext(pro);
			Object o = ic.lookup("AdviceBean");
			
			AdviceHome home = (AdviceHome)PortableRemoteObject.narrow(o, AdviceHome.class);
			
			
			Advice advice = home.create(); 
			System.out.println(advice.getAdvice());
		}catch(Exception e){
			System.out.println(e);
		}
	}
}