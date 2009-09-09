package interceptors;

import javax.interceptor.AroundInvoke;

public class ShreeInterceptor {
	
	@AroundInvoke
	public Object log(javax.interceptor.InvocationContext invocationContext) throws Exception {
		System.err.println(invocationContext.getMethod().getName() + " called from ShreeInterceptor 1");
		return invocationContext.proceed();
	}
}
