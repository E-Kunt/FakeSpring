package com.ekunt.fakespring;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * ��ģ��Spring��ȡXML����������ʵ��ע��Ĺ���
 * @author E-Kunt
 *
 */
public class ClassPathXmlApplicationContext  implements BeanFactory{
	
	private Map<String , Object> beans = new HashMap<String, Object>();

	//IOC=Inverse of Control; DI=Dependency Injection
	public ClassPathXmlApplicationContext() throws Exception {
		//����������
		SAXBuilder sb = new SAXBuilder();
		//�����ĵ�����
		Document doc = sb.build(this.getClass().getClassLoader().getResourceAsStream("beans.xml"));
		//��ȡ��Ԫ��
		Element root = doc.getRootElement();
		//ȡ����Ϊbean������Ԫ��
		List list = root.getChildren("bean");
		
		//ʵ����ÿ�����󣬷Ž�������
		for(int i=0; i<list.size(); i++) {
			Element element = (Element)list.get(i);
			String id = element.getAttributeValue("id");
			String clazz = element.getAttributeValue("class");
			Object o = Class.forName(clazz).newInstance();
			beans.put(id, o);
			
			//ע���������ʵ��
			for(Element propertyElement : (List<Element>)element.getChildren("property")) {
				String name = propertyElement.getAttributeValue("name");
				String ref = propertyElement.getAttributeValue("ref");
				Object beanObject = beans.get(ref);
				String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
				Method m = o.getClass().getMethod(methodName, beanObject.getClass().getInterfaces()[0]);
				m.invoke(o, beanObject);
			}
			
		}
	}

	@Override
	public Object getBean(String id) {
		System.out.println("Get bean from FakeSpring context !");
		return beans.get(id);
	}
	
	
}
