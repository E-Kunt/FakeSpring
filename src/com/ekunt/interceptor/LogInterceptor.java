package com.ekunt.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理，增加日志功能.
 * 模拟Spring的AOP.
 * @author E-Kunt
 *
 */
public class LogInterceptor implements InvocationHandler {
	/**
	 * 被代理对象。必须实现某接口。
	 */
	private Object target;

	public void beforeMethod(Method m) {
		System.out.println(m.getName() + "  start.");
	}
	
	public void afterMethod(Method m) {
		System.out.println(m.getName() + "  end.");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//调用增加的逻辑
		this.beforeMethod(method);
		//调用被代理对象本身的逻辑
		method.invoke(target, args);
		//调用增加的逻辑
		this.afterMethod(method);
		return null;
	}
	
	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}
