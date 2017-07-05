package html2pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by erica on 2017/7/5.
 */
public class Html2Pdf {

    public static void main(String[] args) {
        Document document = new Document();
        try (ByteArrayOutputStream os = new ByteArrayOutputStream(1024 * 1024 * 10);
             FileOutputStream fos = new FileOutputStream("a.pdf")) {
            PdfWriter writer = PdfWriter.getInstance(document, os);
            document.open();

            String str = "<html><head></head><body style=\"font-size:12.0pt; font-family:Times New Roman\">" +
                    "<a href='http://www.rgagnon.com/howto.html'><b>Real's HowTo</b></a>" +
                    "<h1>Show your support</h1>" +
                    "<p>It DOES cost a lot to produce this site - in ISP storage and transfer fees</p>" +
                    "<p>TEST POLSKICH ZNAKÓW: 你好</p>" +
                    "<hr/>" +
                    "<p>the huge amounts of time it takes for one person to design and write the actual content.</p>" +
                    "<p>If you feel that effort has been useful to you, perhaps you will consider giving something back?</p>" +
                    "<p>Donate using PayPal  abc 哈哈</p>" +
                    "<p>Contributions via PayPal are accepted in any amount</p>" +
                    "<p><br/><table border='1'><tr><td>Java HowTo</td></tr><tr>" +
                    "<td style='background-color:red;'>Javascript HowTo</td></tr>" +
                    "<tr><td>Powerbuilder HowTo</td></tr></table></p>" +
                    "</body></html>";

            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            InputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
            worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"), new XMLWorkerFontProvider("resources/fonts/"));
            // step 5
            document.close();
            os.writeTo(fos);
            os.close();
            fos.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
