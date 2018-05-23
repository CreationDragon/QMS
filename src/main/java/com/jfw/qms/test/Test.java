package com.jfw.qms.test;

import com.jfw.qms.algorithm.BaseAttrSelector;
import com.jfw.qms.algorithm.DecisionTree;
import com.jfw.qms.algorithm.IAttrSelector;
import com.jfw.qms.common.TreeNode;
import com.jfw.qms.data.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public void statrtMain(TreeNode treeNode) {
        List<BaseRecord> records = new ArrayList<BaseRecord>();
        HumanAttrRecord record0 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmExercise.OFTEN, EmFood.LIGHT, EmSleep.GOOD, false);
        HumanAttrRecord record1 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmExercise.OFTEN, EmFood.LIGHT, EmSleep.GOOD, false);
        HumanAttrRecord record2 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmExercise.OCCASIONAL, EmFood.ORDINARY, EmSleep.ORDINARY, true);
        HumanAttrRecord record3 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmExercise.ORDINARY, EmFood.LIGHT, EmSleep.BAD, true);
        HumanAttrRecord record4 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmExercise.OFTEN, EmFood.HEAVY, EmSleep.ORDINARY, true);
        HumanAttrRecord record5 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmExercise.OFTEN, EmFood.LIGHT, EmSleep.GOOD, false);
        HumanAttrRecord record6 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmExercise.OCCASIONAL, EmFood.HEAVY, EmSleep.BAD, true);
        HumanAttrRecord record7 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmExercise.ORDINARY, EmFood.ORDINARY, EmSleep.ORDINARY, false);
        HumanAttrRecord record8 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmExercise.OCCASIONAL, EmFood.HEAVY, EmSleep.BAD, true);
        HumanAttrRecord record9 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmExercise.OCCASIONAL, EmFood.LIGHT, EmSleep.BAD, true);
        HumanAttrRecord record10 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmExercise.OFTEN, EmFood.HEAVY, EmSleep.BAD, true);
        HumanAttrRecord record11 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmExercise.ORDINARY, EmFood.LIGHT, EmSleep.BAD, true);
        HumanAttrRecord record12 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmExercise.ORDINARY, EmFood.HEAVY, EmSleep.BAD, true);
       /* HumanAttrRecord record13 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.LOW,false, EmCreditRate.EXCELLENT,false);
        HumanAttrRecord record14 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.MEDIUM,false, EmCreditRate.FAIR,false);
        HumanAttrRecord record15 = new HumanAttrRecord(EmAgeLevel.MIDDLE_AGED, EmIncome.MEDIUM,true, EmCreditRate.EXCELLENT,true);
        HumanAttrRecord record16 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.LOW,false, EmCreditRate.EXCELLENT,true);
        HumanAttrRecord record17 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.HIGH,true, EmCreditRate.EXCELLENT,true);
        HumanAttrRecord record18 = new HumanAttrRecord(EmAgeLevel.YOUTH, EmIncome.MEDIUM,false, EmCreditRate.EXCELLENT,false);
        HumanAttrRecord record19 = new HumanAttrRecord(EmAgeLevel.SENIOR, EmIncome.LOW,false, EmCreditRate.FAIR,false);
*/
        records.add(record0);
        records.add(record1);
        records.add(record2);
        records.add(record3);
        records.add(record4);
        records.add(record5);
        records.add(record6);
        records.add(record7);
        records.add(record8);
        records.add(record9);
        records.add(record10);
        records.add(record11);
        records.add(record12);
  /*      records.add(record13);
        records.add(record14);
        records.add(record15);
        records.add(record16);
        records.add(record17);
        records.add(record18);
        records.add(record19);*/


        Set<Field> fieldSet = new HashSet<Field>();
        Field[] fields = HumanAttrRecord.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("decisionAttr")) continue;
            fieldSet.add(field);
        }

        IAttrSelector selector = new BaseAttrSelector();
        DecisionTree decisionTree = new DecisionTree(selector);
        TreeNode root = decisionTree.createTree(records, fieldSet, treeNode);
        String msg = null;
        List<String> strings = new ArrayList<>();
        if (null != root) {
            root.print(0);
        }
    }
}
