package com.ekunt.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ��̬����������־����.
 * ģ��Spring��AOP.
 * @author E-Kunt
 *
 */
public class LogInterceptor implements InvocationHandler {
	/**
	 * ��������󡣱���ʵ��ĳ�ӿڡ�
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
		//�������ӵ��߼�
		this.beforeMethod(method);
		//���ñ������������߼�
		method.invoke(target, args);
		//�������ӵ��߼�
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
