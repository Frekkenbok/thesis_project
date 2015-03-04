package testmail;

import java.io.*;
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
 * @author bkv
 */

public class GmailClient {

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

    public void sendGmail(String from, String to, String subject, String text, String file) {

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
            DataSource source = new FileDataSource(new File(getDir + File.separator + file));
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

    public void readGmail(String mailSubject) {

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
            for (int i = 0; i < message.length; i++) {

                Pattern p = Pattern.compile(mailSubject);
                Matcher m = p.matcher(message[i].getSubject());
                if (m.matches()) {

                    System.out.println(message[i].getSubject());
                    String contentType = message[i].getContentType();
                    String attachFiles = "";

                    if (contentType.toLowerCase().contains("multipart")) {
                        // content may contain attachments
                        Multipart multipart = (Multipart) message[i].getContent();

                        System.out.println(multipart.getCount());
                        

                        for (int j = 0; i < multipart.getCount(); j++) {
                            BodyPart bodyPart = multipart.getBodyPart(j);
                            if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                                continue; // dealing with attachments only
                            }
                            InputStream is = bodyPart.getInputStream();

                            // getFilename always have wrong characters set

                            System.out.println("2");
                            //String filename = bodyPart.getFileName();
                            String filename = MimeUtility.decodeText(bodyPart.getFileName());
                            File f = new File(saveDir + File.separator + filename);

                            System.out.println(f.getName());

                            try {
                                if (f == null) {
                                    return;
                                }
                                System.out.println("3");
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
        } catch (MessagingException | IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        //init variables
        String mailFrom = new String("ddmmyy20150112@gmail.com");
        String senderPassword = new String("password20150112");
        String senderUserName = new String("ddmmyy20150112@gmail.com");
        String mailText = new String("Have an Nice Day ...........!!!");

        //varivables as client email, id and file
        String mailSubject = new String("id_1235_id");
        String mailTo = new String("ddmmyy20150112@gmail.com");
        String file = "java-persistence-developer-guide — копия.pdf";
        //Gmail client init
        GmailClient gmailClient = new GmailClient();
        gmailClient.setAccountDetails(senderUserName, senderPassword);
        gmailClient.setGetDir("files/out");
        gmailClient.setSaveDir("files/in");

        //Send mail
        gmailClient.sendGmail(mailFrom, mailTo, mailSubject, mailText, file);
        //Receive mails
        gmailClient.readGmail(mailSubject);
    }
}