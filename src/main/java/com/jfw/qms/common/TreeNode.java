package com.jfw.qms.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String msg;
    private String info;
    private String attrName;
    private List<TreeNode> treeNodeList;
    private List<String> answers = new ArrayList<>();

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

    public String print(int level) {

        if (null == this)
            return null;
        for (int i = 0; i < level; ++i)
            System.out.print("-");
        System.out.println(this.attrName);
        info = reduction(this.attrName, level);
        if (info != null) {
            return info;
        }
        ++level;
        if (null != this.getTreeNodeList())
            if (info != null) {
                return info;
            } else {
                for (TreeNode node : this.getTreeNodeList()) {
                    node.print(level);
                    if (null != info) {
                        break;
                    }
                }
            }
        return info;
    }

    // �����жϷ���
    private String reduction(String attrname, int level) {
        switch (level) {
            case 0:
                if ("sleep".equals(attrname)) {
                    if (answers.get(3).equals("C")) {
                        System.out.println("你没有患有老年病");
                        msg = "你没有患有老年病";
                    } else if (answers.get(3).equals("A")) {
                        System.out.println("你患有老年病");
                        msg = "你患有老年病";
                    } else {
                        break;
                    }
                }

                break;

            case 1:
                if ("exercise".equals(attrname)) {
                    if (!answers.get(0).equals("B")) {
                        System.out.println("你患有老年病");
                        msg = "你患有老年病";
                    } else {
                        System.out.println("你没有患有老年病");
                        msg = "你没有患有老年病";
                    }
                }

                break;
        }
        return msg;

    }
}
