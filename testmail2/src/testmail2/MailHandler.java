/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testmail2;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
/**
 *
 * @author Евгения
 */

public class MailHandler {

    public static void sendMail(String email, String id, File file) {


        final String username = "ddmmyy20150112@gmail.com";
        final String password = "password20150112";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ddmmyy20150112@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setHeader("Content-Type","text/html;charset=UTF-8");
            try {
                message.setSubject(MimeUtility.encodeText(id, "UTF-8", "B"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            try {
                messageBodyPart.setFileName(javax.mail.internet.MimeUtility.encodeWord(source.getName(), "UTF-8", null));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            multipart.addBodyPart(messageBodyPart);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setContent(multipart);
            System.out.println("Sending");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

