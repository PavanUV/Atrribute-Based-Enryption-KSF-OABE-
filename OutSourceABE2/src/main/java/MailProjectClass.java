/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DLK 1
 */
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailProjectClass 
{
    public void fun(String fname,String email)
    {
         final String username = "projectmail225@gmail.com";
    final String password = "projectmail22555";

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
   
    

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
    	session.setDebug(true);
    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("mssjava007@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
        message.setSubject("Your File");
        message.setText("Please Find This Attachment");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = "C:\\files\\"+fname;
        String fileName = fname;
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("Sending");

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
    }
    public static void main(String[] args) 
    {
        MailProjectClass oo = new MailProjectClass();
    }
}

