package com.jfw.qms.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.jfw.qms.entity.Question;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class produceWordUtil {

    public static void produceWord(String root, List<Question> questionList, Integer quesID) throws Exception {

        //Blank Document
        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        String docPath = root.split("file:/")[1].split("application.properties")[0] + "/static/word/问卷" + quesID + ".docx";
        FileOutputStream out = new FileOutputStream(new File(docPath));


        for (Question question : questionList
                ) {
            //create Paragraph
            XWPFParagraph paragraph = document.createParagraph();
            //设置文字对齐方式
            paragraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun run = paragraph.createRun();
//            设置题目
            XWPFRun title = paragraph.createRun();
            title.setKerning(0);
            title.setText(question.getTitle());
            title.addBreak();

//            设置答案
            XWPFRun answer = paragraph.createRun();
            answer.setText("A、" + question.getAnswerA());
//            answer.addBreak();

            answer.setText("B、" + question.getAnswerB());
//            answer.addBreak();

            if (question.getAnswerC() != null && question.getAnswerD() != null) {
                answer.setText("C、" + question.getAnswerC());
//                answer.addBreak();
                answer.setText("D、" + question.getAnswerD());
//                answer.addBreak();
            }

//            title.addBreak();
            answer.addBreak();
        }

        document.write(out);
        out.close();
        System.out.println("createparagraph.docx written successfully");
    }
}