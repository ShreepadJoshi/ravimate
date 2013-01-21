package interceptors;

import javax.interceptor.AroundInvoke;

public class ShreeInterceptor {
	
	@AroundInvoke
	public Object log(javax.interceptor.InvocationContext invocationContext) throws Exception {
		System.err.println(invocationContext.getMethod().getName() + " In ShreeInterceptor");
		return invocationContext.proceed();
	}
}
