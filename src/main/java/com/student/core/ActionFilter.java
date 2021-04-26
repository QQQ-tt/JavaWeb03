package com.student.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** @Author: QTX @Date: 2021/4/26 */
@WebFilter(
    filterName = "actionFilter",
    urlPatterns = {"/*"},
    initParams = {@WebInitParam(name = "charset", value = "utf-8")})
public class ActionFilter implements Filter {
  private Map<String, ActionConfig> actionConfigMap = new HashMap();
  private String charset;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    String charset = filterConfig.getInitParameter("charset");
    this.charset = charset;
    SAXReader reader = new SAXReader();

    try {
      Document document =
          reader.read(ActionFilter.class.getClassLoader().getResourceAsStream("action.xml"));
      Element rootElement = document.getRootElement();
      String aClass = rootElement.attributeValue("class");
      List<Element> elements = rootElement.elements();
      Iterator var8 = elements.iterator();

      while (var8.hasNext()) {
        Element element = (Element) var8.next();
        String name = element.attributeValue("name");
        String method = element.attributeValue("method");
        ActionConfig actionConfig = new ActionConfig(name, method, aClass);
        this.actionConfigMap.put(name, actionConfig);
        List<Element> rlist = element.elements();
        Iterator var14 = rlist.iterator();

        while (var14.hasNext()) {
          Element eres = (Element) var14.next();
          String rname = eres.attributeValue("name");
          String rtype = eres.attributeValue("type");
          String path = eres.getText();
          ResultConfig rc = new ResultConfig(rname, rtype, path);
          actionConfig.getAcResult().put(rname, rc);
          System.out.println(rc);
        }
      }
    } catch (DocumentException var20) {
      var20.printStackTrace();
    }
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    req.setCharacterEncoding(this.charset);
    resp.setContentType("text/html;charset=utf-8");
    ActionContext.setActionContext(new ActionContext(resp, req));
    String requestUri = req.getRequestURI();
    String uri = requestUri.substring(req.getContextPath().length() + 1);
    System.out.println(uri);
    System.out.println("--------------");
    if (this.actionConfigMap.containsKey(uri)) {
      ActionConfig actionConfig = (ActionConfig) this.actionConfigMap.get(uri);
      String className = actionConfig.getClassName();

      try {
        Class aClass = Class.forName(className);
        Object o = aClass.newInstance();
        String method = actionConfig.getMethod();
        Method declaredMethod = aClass.getDeclaredMethod(method, (Class[]) null);
        String resView = (String) declaredMethod.invoke(o, (Object[]) null);
        Map<String, ResultConfig> resMap = actionConfig.getAcResult();
        if (!resMap.containsKey(resView)) {
          throw new RuntimeException("在" + actionConfig.getName() + "找不到" + resView + "该视图");
        }

        ResultConfig rcfig = (ResultConfig) resMap.get(resView);
        if ("dispatcher".equals(rcfig.getType())) {
          System.out.println(rcfig.getPath());
          System.out.println("------转发-------");
          req.getRequestDispatcher(rcfig.getPath()).forward(req, resp);
        } else {
          System.out.println(rcfig.getPath());
          System.out.println("------重定向-------");
          resp.sendRedirect(req.getContextPath() + rcfig.getPath());
        }
      } catch (InstantiationException var17) {
        var17.printStackTrace();
      } catch (IllegalAccessException var18) {
        var18.printStackTrace();
      } catch (ClassNotFoundException var19) {
        var19.printStackTrace();
      } catch (NoSuchMethodException var20) {
        var20.printStackTrace();
      } catch (InvocationTargetException var21) {
        var21.printStackTrace();
      }
    } else {
      System.out.println("-----放行-----");
      chain.doFilter(req, resp);
    }
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}
