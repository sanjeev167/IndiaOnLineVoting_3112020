/**
 * 
 */
package com.pon.config.wraper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
/**
 * @author Sanjeev
 *
 */
public class CharResponseWrapper extends HttpServletResponseWrapper {
    private CharArrayWriter writer;
     
    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
        writer = new CharArrayWriter();
    }
     
    public PrintWriter getWriter() {
        return new PrintWriter(writer);
    }
     
    public String toString() {
        return writer.toString();
    }
 
}
