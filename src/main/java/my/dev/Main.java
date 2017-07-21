package my.dev;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> docs = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get("d:\\Универы\\"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> docs.add(path.toAbsolutePath().toString()));
        }

        System.out.println(docs);

        String start = "Мероприятия, направленные на развитие национального самосознания белорусских";
        String end = "Научно-исследовательская деятельность в области истории";

        for (String doc : docs) {

            if (doc.endsWith("docx")) {

                try (FileInputStream fis = new FileInputStream(doc)) {

                    XWPFDocument document = new XWPFDocument(fis);
                    XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                    List<XWPFParagraph> paragraphs = document.getParagraphs();
                    boolean flag = false;
                    StringBuilder sb = new StringBuilder();
                    for (XWPFParagraph paragraph : paragraphs) {
                        if (paragraph.getText().contains(start)) {
                            flag = true;
                        }
                        if (paragraph.getText().contains(end)) {
                            flag = false;
                        }
                        if (flag) {
                            sb.append(paragraph.getText() + "\n");
                        }
                    }
                    String resultDoc = doc.substring(11);
                    System.out.println(resultDoc.toUpperCase());
                    System.out.println(sb.toString());
                }

            } else if (doc.endsWith("doc")) {
                try (FileInputStream fis = new FileInputStream(doc)) {

                    HWPFDocument document = new HWPFDocument(fis);
                    WordExtractor extractor = new WordExtractor(document);
                    String[] fileData = extractor.getParagraphText();
                    boolean flag = false;
                    StringBuilder result = new StringBuilder();
                    for (String str : fileData) {
                        if (str.contains(start)) {
                            flag = true;
                        }
                        if (str.contains(end)) {
                            flag = false;
                        }
                        if (flag) {
                            result.append(str);
                        }
                    }
                    String resultDoc = doc.substring(11);
                    System.out.println(resultDoc.toUpperCase());
                    System.out.println(result);

                }
            }
        }
    }
}
