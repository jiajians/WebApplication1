package ca.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

@Named(value = "customer")
@SessionScoped
public class CustomerManagedBean implements Serializable {
    String firstName ="";
    String lastName ="";
    int  age =0;
    
    // window.open('#{customer.getPDF()}','1099'); 
    
    public void getPDF() throws Exception{
        // Get the FacesContext
        FacesContext facesContext = FacesContext.getCurrentInstance();
         
        // Get HTTP response
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
         
        // Set response headers
        response.reset();   // Reset the response in the first place
        response.setHeader("Content-Type", "application/pdf");  // Set only the content type
         
        // Open response output stream
        OutputStream responseOutputStream = response.getOutputStream();
         
        // Read PDF contents
        URL url = (new File("C:/temp/cook.pdf")).toURI().toURL();

        InputStream pdfInputStream = url.openStream();
         
        // Read PDF contents and write them to the output
        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = pdfInputStream.read(bytesBuffer)) > 0) {
            responseOutputStream.write(bytesBuffer, 0, bytesRead);
        }
         
        // Make sure that everything is out
        responseOutputStream.flush();
          
        // Close both streams
        pdfInputStream.close();
        responseOutputStream.close();
         
        // JSF doc:
        // Signal the JavaServer Faces implementation that the HTTP response for this request has already been generated
        // (such as an HTTP redirect), and that the request processing lifecycle should be terminated
        // as soon as the current phase is completed.
        facesContext.responseComplete();
    }    

    public String newPDF() throws Exception{
        // Get the FacesContext
        FacesContext facesContext = FacesContext.getCurrentInstance();
         
        // Get HTTP response
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
         
        // Set response headers
        response.reset();   // Reset the response in the first place
        response.setHeader("Content-Type", "application/pdf");  // Set only the content type
         
        // Open response output stream
        OutputStream responseOutputStream = response.getOutputStream();
         
        // Read PDF contents
        URL url = (new File("C:/temp/cook.pdf")).toURI().toURL();

        InputStream pdfInputStream = url.openStream();
         
        // Read PDF contents and write them to the output
        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = pdfInputStream.read(bytesBuffer)) > 0) {
            responseOutputStream.write(bytesBuffer, 0, bytesRead);
        }
         
        // Make sure that everything is out
        responseOutputStream.flush();
          
        // Close both streams
        pdfInputStream.close();
        responseOutputStream.close();
         
        // JSF doc:
        // Signal the JavaServer Faces implementation that the HTTP response for this request has already been generated
        // (such as an HTTP redirect), and that the request processing lifecycle should be terminated
        // as soon as the current phase is completed.
        facesContext.responseComplete();
        
        return "index.html";
    }    
    
    public void copyPDF(){
        OutputStream oos=null;
        try {
            System.out.println("getPDF() got called!");
            File f=new File("C:/temp/cook.pdf");
            oos = new FileOutputStream("C:/temp/test.pdf");
            byte[] buf = new byte[8192];
            InputStream is = new FileInputStream(f);
            int c = 0;
            try {
                while ((c = is.read(buf, 0, buf.length)) > 0) {
                    oos.write(buf, 0, c);
                    oos.flush();
                }
            } catch (IOException ex) {
                Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("stop");
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}
