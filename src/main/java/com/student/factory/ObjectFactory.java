package com.student.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @Author: QTX @Date: 2021/4/26 */
public class ObjectFactory {
  private static Map<String, Object> objects = new HashMap();

  public ObjectFactory() {}

  public static Object getObj(String name) {
    return objects.get(name);
  }

  static {
    SAXReader saxReader = new SAXReader();

    try {
      Document obj =
          saxReader.read(ObjectFactory.class.getClassLoader().getResourceAsStream("object.xml"));
      Element rootElement = obj.getRootElement();
      List<Element> elements = rootElement.elements();

      for (int i = 0; i < elements.size(); ++i) {
        Element child = (Element) elements.get(i);
        String name = child.attributeValue("name");
        String classPath = child.attributeValue("class");
        Object o = Class.forName(classPath).newInstance();
        objects.put(name, o);
      }
    } catch (ClassNotFoundException | DocumentException var9) {
      var9.printStackTrace();
    } catch (InstantiationException var10) {
      var10.printStackTrace();
    } catch (IllegalAccessException var11) {
      var11.printStackTrace();
    }
  }
}
