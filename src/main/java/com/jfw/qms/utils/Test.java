package com.jfw.qms.utils;

import cn.afterturn.easypoi.word.WordExportUtil;
import cn.afterturn.easypoi.word.entity.WordImageEntity;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {


    /**
     * 简单导出包含图片
     */

    public void imageWordExport() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("department", "Easypoi");
        map.put("person", "JueYue");
        map.put("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        WordImageEntity image = new WordImageEntity();
        image.setHeight(200);
        image.setWidth(500);
        image.setUrl("cn/afterturn/easypoi/test/word/img/testCode.png");
        image.setType(WordImageEntity.URL);
        map.put("testCode", image);
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                    "cn/afterturn/easypoi/test/word/doc/Image.docx", map);
            FileOutputStream fos = new FileOutputStream("D:/excel/image.docx");
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 简单导出没有图片和Excel
     */

    public void SimpleWordExport() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("department", "Easypoi");
        map.put("person", "JueYue");
        map.put("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        map.put("me", "JueYue");
        map.put("date", "2015-01-03");
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                    "cn/afterturn/easypoi/test/word/doc/Simple.docx", map);
            FileOutputStream fos = new FileOutputStream("D:/excel/simple.docx");
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
