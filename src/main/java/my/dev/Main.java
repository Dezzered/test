package my.dev;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        /*try (FileInputStream fis = new FileInputStream("02_BarGU_monitoring_S_PRAVKAMI_2017_13062017.doc")) {
            XWPFDocument doc = new XWPFDocument(fis);

            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            StringBuilder result = new StringBuilder();
            boolean flag = false;
            for (XWPFParagraph paragraph : paragraphs) {
                if (paragraph.getText().contains("ГРАЖДАНСТВЕННОСТЬ И ПАТРИОТИЗМ")) {
                    System.out.println(22222);
                    flag = false;
                }
                if (paragraph.getText().startsWith("1.4.2. Общественно значимые")) {
                    System.out.println(1111);
                    flag = true;
                }
                if (flag) {
                    result.append(paragraph.getText());
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        FileInputStream fis = new FileInputStream("02_BarGU_monitoring_S_PRAVKAMI_2017_13062017.doc");
        HWPFDocument document = new HWPFDocument(fis);
        WordExtractor extractor = new WordExtractor(document);
        String[] fileData = extractor.getParagraphText();
        boolean flag = false;
        StringBuilder result = new StringBuilder();
        for (String str : fileData) {
            if (str.contains("Научно-исследовательская деятельность в области")) {
                System.out.println(22222);
                flag = false;
            }
            if (str.contains("Мероприятия, направленные на развитие национального ")) {
                System.out.println(1111);
                flag = true;
            }
            if (flag) {
                result.append(str);
            }
        }
        System.out.println(result);
    }
}
