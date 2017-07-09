package html2pdf.sendmail;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created by erica on 2017/7/5.
 */
public class Html2PdfAndSendMail {

    public static void main(String[] args) {
        String str = "<html><head></head><body style=\"font-size:12.0pt; font-family:Times New Roman\">" +
                "<a href='http://www.rgagnon.com/howto.html'><b>Real's HowTo</b></a>" +
                "<h1>Show your support</h1>" +
                "<p>It DOES cost a lot to produce this site - in ISP storage and transfer fees</p>" +
                "<p>TEST POLSKICH ZNAKÓW: 你a好</p>" +
                "<hr/>" +
                "<p>the huge amounts of time it takes for one person to design and write the actual content.</p>" +
                "<p>If you feel that effort has been useful to you, perhaps you will consider giving something back?</p>" +
                "<p>Donate using PayPal  ab嗯c 哈哈</p>" +
                "<p>Contributions via PayPal are accepted in any amount</p>" +
                "<p><br/><table border='1'><tr><td>Java HowTo</td></tr><tr>" +
                "<td style='background-color:red;'>Javascript HowTo</td></tr>" +
                "<tr><td>Powerbuilder HowTo</td></tr></table></p>" +
                "</body></html>";

        String to = "iqboy118@qq.com";
        sendMail(to, str);
    }

    public static byte[] convert2Pdf(String str) {
        Document document = new Document();
        try (ByteArrayOutputStream os = new ByteArrayOutputStream(1024 * 1024 * 10)
             /*; FileOutputStream fos = new FileOutputStream("a.pdf")*/) {
            PdfWriter writer = PdfWriter.getInstance(document, os);
            document.open();

            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            InputStream is = new ByteArrayInputStream(str.getBytes());
            worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"), new AsianFontProvider());
            // step 5
            document.close();
            return os.toByteArray();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendMail(String to, String content) {
        String from = "zhangzl@133.cn";
        final String username= "zhangzl@133.cn";
        final String password = "****";
        String host = "smtp.qiye.163.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("行程单");

            Multipart multipart = new MimeMultipart();
            BodyPart contentPart = new MimeBodyPart();

            contentPart.setContent(content, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            BodyPart attchment = new MimeBodyPart();
            byte[] bytes = convert2Pdf(content);
            DataSource source = new ByteArrayDataSource(bytes, "application/pdf");
            attchment.setDataHandler(new DataHandler(source));

            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();

            attchment.setFileName("=?GBK?B?" + enc.encode("行程单.pdf".getBytes()) + "?=");
            multipart.addBodyPart(attchment);

            message.setContent(multipart);
            message.saveChanges();

            // Send the actual HTML message, as big as you like
            /*message.setContent(
                    content,
                    "text/html; charset=UTF-8");*/

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
