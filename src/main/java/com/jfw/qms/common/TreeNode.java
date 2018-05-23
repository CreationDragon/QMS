package com.jfw.qms.common;

import com.jfw.qms.model.TempData;
import com.jfw.qms.test.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String msg;
    private String info;
    private String attrName;
    private List<TreeNode> treeNodeList;
    private List<String> answers = new ArrayList<>();
    private List<String> strings = new ArrayList<>();

    public TreeNode() {
    }

    public TreeNode(String msg, String attrName, List<TreeNode> treeNodeList, List<String> answers) {
        this.msg = msg;
        this.attrName = attrName;
        this.treeNodeList = treeNodeList;
        this.answers = answers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public List<TreeNode> getTreeNodeList() {
        return treeNodeList;
    }

    public void setTreeNodeList(List<TreeNode> treeNodeList) {
        this.treeNodeList = treeNodeList;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private TempData tempData = new TempData();


    public void print(int level) {
        for (int i = 0; i < level; ++i) {
            System.out.print("-");
        }
        System.out.println(this.attrName);
        getWord(this.attrName);
        strings.add(this.attrName);
        ++level;
        if (null != this.getTreeNodeList())
            for (TreeNode node : this.getTreeNodeList()) {
                node.print(level);
            }
    }

    private void getWord(String attrName) {
        String root = null;
        try {
            root = String.valueOf(ResourceUtils.getURL("application.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String pathname = root.split("file:/")[1].split("application.properties")[0] + "/static";
        File fileDir = new File(pathname);
        if (!fileDir.exists()) { //如果不存在 则创建
            fileDir.mkdirs();
        }

        String path = pathname + "/" + "word.txt";

        FileWriter fw = null;
        try {
//如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f = new File(path);
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.print("," + attrName);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
