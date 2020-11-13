/**
 * 
 */
package com.pon.config.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pon.pub.hm.component.HitCountComponent;
import com.pon.pub.hm.ctrl.HomeController;

/**
 * @author Sanjeev
 *
 */
public class SessionCounterListener implements HttpSessionListener {
	
    public static int totalActiveSessions=0;
    public static HttpSession session;
    public static int getTotalActiveSession(){
          return totalActiveSessions;
    }
    
   @Override
   public void sessionCreated(HttpSessionEvent arg0) {
	      totalActiveSessions++;
	      session=arg0.getSession();
	      
          System.out.println("Sanjeev sessionCreated - add one session into counter");	
          adjustCounter(arg0);
   }

   @Override
   public void sessionDestroyed(HttpSessionEvent arg0) {
          totalActiveSessions--;
          //System.out.println("Sanjeev sessionDestroyed - deduct one session from counter");	
          adjustCounter(arg0);
   }	
 
   private void adjustCounter(HttpSessionEvent sessionEvent){

         HttpSession session = sessionEvent.getSession();
         
         ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
         HitCountComponent hitCountComponent =  (HitCountComponent) ctx.getBean("hitCountComponent");
         hitCountComponent.setLiveSessionCount(totalActiveSessions);
         HomeController.currentSessionCount = totalActiveSessions;
         System.out.println("Sanjeev total active session - "+totalActiveSessions);	
   }
   
   
}
