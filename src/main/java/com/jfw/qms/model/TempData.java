package com.jfw.qms.model;

import java.util.ArrayList;
import java.util.List;

public class TempData {

    static List<String> tempData = new ArrayList<>();

    public static void storyData(String arg) {
        tempData.add(arg);
    }

    public static List<String> getData() {
        return tempData;
    }

}
