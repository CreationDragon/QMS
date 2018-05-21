package com.jfw.qms.algorithm;

import com.jfw.qms.common.TreeNode;
import com.jfw.qms.data.BaseRecord;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DecisionTree {


    IAttrSelector selector;

    public DecisionTree(IAttrSelector selector) {
        this.selector = selector;
    }

    /**
     * ����������
     */
    public TreeNode createTree(List<BaseRecord> records, Set<Field> attrSet, TreeNode treeNode) {
        if (null == records || records.size() < 1)
            return null;
        TreeNode node = new TreeNode();
        node.setAnswers(treeNode.getAnswers());
        //1.������еļ�¼��������ֵ����ͬ�����ȫ����ͬ��ֱ�ӷ��ط�������ֵ
        if (isAllInSameClass(records)) {
            node.setAttrName(String.valueOf(records.get(0).getDecisionAttr()));
            return node;
        }
        //2.��������б�Ϊ�գ�ͳ�Ƽ�¼����������������������>��?true:false
        if (null == attrSet || 0 == attrSet.size()) {
            node.setAttrName(String.valueOf(getMostClass(records)));
            return node;
        }
        //3.ѡ�����������������
        Field bestField = selector.select(records, attrSet);
        //4.����������Ե�ֵ��Ϊ�����֧
        List<List<BaseRecord>> splitValues = splitRecords(records, bestField);
        List<TreeNode> children = new ArrayList<TreeNode>(splitValues.size());
        attrSet.remove(bestField);
        //5.�����ӽڵ�
        for (List<BaseRecord> recordList : splitValues) {
            children.add(createTree(recordList, attrSet, treeNode));
        }
        node.setTreeNodeList(children);
        node.setAttrName(bestField.getName());
        return node;
    }

    /**
     * �������Ե�ֵ�ֲ�ͬ�б�
     */
    private List<List<BaseRecord>> splitRecords(List<BaseRecord> records, Field field) {
        List<List<BaseRecord>> result = new ArrayList<List<BaseRecord>>();
        try {
            field.setAccessible(true);
            outerLoop:
            for (BaseRecord record : records) {
                Object value = field.get(record);
                for (List<BaseRecord> recordList : result) {
                    if (field.get(recordList.get(0)).equals(value)) {
                        recordList.add(record);
                        continue outerLoop;
                    }
                }
                List<BaseRecord> recordList = new ArrayList<BaseRecord>();
                recordList.add(record);
                result.add(recordList);
            }
        } catch (Exception ex) {
            System.out.println("method access exception");
        }

        return result;
    }

    /**
     * �����б��з��������������������Ҷ�ӽڵ�Ϊtrue or false
     */
    private Boolean getMostClass(List<BaseRecord> records) {
        int positCount = 0;
        int negatCount = 0;
        for (BaseRecord record : records) {
            if (record.getDecisionAttr())
                ++positCount;
            else
                ++negatCount;
        }
        return positCount > negatCount ? true : false;
    }

    /**
     * �ж����м�¼�Ƿ������ͬ�ķ���ֵ
     */
    private boolean isAllInSameClass(List<BaseRecord> records) {
        Boolean buyComp = records.get(0).getDecisionAttr();
        for (BaseRecord record : records) {
            if (!buyComp.equals(record.getDecisionAttr()))
                return false;
        }
        return true;
    }

}
