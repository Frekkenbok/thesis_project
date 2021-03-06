package utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;

/**
 * @author Evgeniya
 */

public class GmailClient {
Connection conn = null;
 Statement stat = null;
 ResultSet res = null;
 String userNameSQL = "root";
 String passwordSQL = "Password";
 String url = "jdbc:mysql://localhost:3306/testdb";
    private String userName;
    private String password;
    private String sendingHost;
    private int sendingPort;
    private String from;
    private String to;
    private String subject;
    private String text;
    private String receivingHost;
    private String saveDir;
    private String getDir;
    private static final String translated = "files/translated";
    
    public void setAccountDetails(String userName, String password) {

        this.userName = userName; //sender's email can also use as User Name
        this.password = password;

    }

    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }

    public void setGetDir(String getDir) {
        this.getDir = getDir;
    }

    public void sendGmail(String from, String to, String subject, String text, File file) {

        InternetAddress fromAddress = null;
        InternetAddress toAddress = null;

        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.sendingHost = "smtp.gmail.com";
        this.sendingPort = 465;

        Properties props = new Properties();
        props.put("mail.smtp.host", this.sendingHost);
        props.put("mail.smtp.port", String.valueOf(this.sendingPort));
        props.put("mail.smtp.user", this.userName);
        props.put("mail.smtp.password", this.password);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        Message message = new MimeMessage(session);


        try {
            fromAddress = new InternetAddress(this.from);
            toAddress = new InternetAddress(this.to);
        } catch (AddressException e) {
            e.printStackTrace();
        }


        try {
            message.setHeader("Content-Type", "multipart;charset=UTF-8");
            message.setFrom(fromAddress);
            message.setRecipient(RecipientType.TO, toAddress);
            message.setSubject(MimeUtility.encodeText(this.subject, "UTF-8", "B"));
            message.setText(this.text);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(javax.mail.internet.MimeUtility.encodeWord(source.getName(), "UTF-8", null));
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport transport = session.getTransport("smtps");
            transport.connect(this.sendingHost, sendingPort, this.userName, this.password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendGmail(OutMessage message) {
        String mailFrom = new String("ddmmyy20150112@gmail.com");
        String senderPassword = new String("password20150112");
        String senderUserName = new String("ddmmyy20150112@gmail.com");
        //Gmail client init
        setAccountDetails(senderUserName, senderPassword);
        //varivables as client email, id and file
       sendGmail(mailFrom, message.getEmail(), message.getId(), "", message.getFile());
    }

    public void readGmail(InMessage inMessage) {
        
       // String mailFrom = new String("ddmmyy20150112@gmail.com");
        String senderPassword = new String("password20150112");
        String senderUserName = new String("ddmmyy20150112@gmail.com");
        //Gmail client init
        setAccountDetails(senderUserName, senderPassword);

        this.receivingHost = "imap.gmail.com";//for imap protocol
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getDefaultInstance(props, null);
        try {
            Store store = session.getStore("imaps");
            store.connect(this.receivingHost, this.userName, this.password);
            Folder folder = store.getFolder("INBOX");//get inbox
            folder.open(Folder.READ_ONLY);//open folder only to read
            //folder.open(Folder.READ_WRITE);

            Message message[] = folder.getMessages();
            System.out.println("one");           
            System.out.println(message.length);
            
            for (int i = 0; i < message.length; i++) {

                Pattern p = Pattern.compile(inMessage.getInid());
                
System.out.println(inMessage.getInid());
                
                Matcher m = p.matcher(message[i].getSubject());
                System.out.println("two");                
                if (m.matches()) {
                    System.out.println("match");                   
                    System.out.println(message[i].getSubject());
                    String contentType = message[i].getContentType();
                    //String attachFiles = "";

                    if (contentType.contains("multipart")) {
                        // content may contain attachments
                        Multipart multipart = (Multipart) message[i].getContent();

                        System.out.println(multipart.getCount());
System.out.println("attachment");
                        for (int j = 0; j < multipart.getCount(); j++) {
                            BodyPart bodyPart = multipart.getBodyPart(j);
                            if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                                continue; // dealing with attachments only
                            }
                            InputStream is = bodyPart.getInputStream();

                            // getFilename always have wrong characters set
                            //String filename = bodyPart.getFileName();
                            String filename = MimeUtility.decodeText(bodyPart.getFileName());
   
                            File f = new File(translated 
                                    + File.separator 
                                    + inMessage.getCureId() 
                                    + File.separator
                                    + new SimpleDateFormat("yyyyMMdd").format(new Date())
                                    + File.separator
                                    + filename);
System.out.println("File path is "+ f);                            
                     // saving       
                            inMessage.setInfile(f);  
                            File filePath  = new File(f.getParent());
                            if(!filePath.exists()){
                            filePath.mkdirs();
            }

                            System.out.println(f.getName());
                            try {
                                if (f == null) {
                                    return;
                                }

                                FileOutputStream fos = new FileOutputStream(f);
                                BufferedOutputStream bos = new BufferedOutputStream(fos);
                                BufferedInputStream bis = new BufferedInputStream(is);

                                int aByte;
                                while ((aByte = bis.read()) >= 0) {
                                    bos.write(aByte);
                                }

                                fos.flush();
                                bos.flush();
                                bos.close();
                                bis.close();
                                fos.close();
// Апдейт таблицы - вставка оригинального пути, названия документа, даты вставки и замена статуса на "Готов"
                                System.out.println("Updating..");
           try
            {
                Class.forName ("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection (url, userNameSQL, passwordSQL);
                stat = conn.createStatement();
                String SQL =(" update documentsinfo "
                           + " set TranslatedPath = '"+f+"', "
                           + " Document = '"+filename+"', "
                           + " Status = 2, "
                           + " TranslationDate = NOW()"
                           + " where DocumentID = substring_index( substring_index('"+inMessage.getInid()+"', '_', -2), '_', 1) ");
                System.out.println("These raw was updated DocumentID=" + inMessage.getInid()+ ", Path=" +f+ ", FileName=" +filename);
                stat.executeUpdate(SQL);
                stat.close();
                
            }catch (Exception e )
            {
                System.err.println (e.getMessage());
            }
            finally {
                try { if ( conn != null ) { conn.close(); } } catch (Exception ignore) {}
            }
                            }
                            catch (IOException exp) {
                                System.out.println("IOException:" + exp);
                            }
                        }
                    }
                }
                //tempMessage.setFlag(Flags.Flag.DELETED, true);
            }
            folder.close(true);
            store.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        //init variables
      
        OutMessage message = new OutMessage();
        message.setEmail("ddmmyy20150112@gmail.com");
        message.setId("7");
        message.setFile(new File("files\\originals\\19\\20150228\\SQLQuery3.sql"));
        
        InMessage inMessage = new InMessage();
        inMessage.setCureId("5");
        inMessage.setInid("49");
       
        
        GmailClient gmailClient = new GmailClient();
        //Send mail
        //gmailClient.sendGmail(message);
        //Receive mails
        gmailClient.readGmail(inMessage);
    }
}