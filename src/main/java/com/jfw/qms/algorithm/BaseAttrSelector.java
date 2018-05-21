package com.jfw.qms.algorithm;

import com.jfw.qms.data.BaseRecord;

import java.lang.reflect.Field;
import java.util.*;

public class BaseAttrSelector implements IAttrSelector {
    /**ͨ����¼�������¼�����Լ��ϣ���ѡ���������������������*/
    @Override
    public Field select(List<BaseRecord> records, Set<Field> atrrs){
        Field bestField = null;
        Double highestScore = 0D;
        Double setInfo = entropy(records);
        for(Field field : atrrs) {
            Double gainScore = setInfo - infoScore( records, field);
                if(gainScore > highestScore) {
                highestScore = gainScore;
                bestField = field;
            }
        }
        return bestField;
    }
    /**���ݼ�¼�б��������������أ��˷�����Ҫ�ֵ�����DcisionAtrr*/
    private Double entropy(List<BaseRecord> records) {
        Double positCount = 0D;
        Double negatCount = 0D;
        for(BaseRecord record : records) {
            if(record.getDecisionAttr())
                ++positCount;
            else
                ++negatCount;
        }
        return - positCount/records.size()* log2N(positCount / records.size())
                - negatCount/records.size()* log2N(negatCount / records.size());

    }

    /**log2(N), log ��2Ϊ��N�Ķ���*/
    private Double log2N(Double d) {
        return Math.log(d) / Math.log(2.0);
    }

    /**��ĳ�����Զ��ڷ���DecisionAttr��������������ʽ��<�����ھ�����뼼��>�о������ǽ�*/
    private Double infoScore(List<BaseRecord> records, Field field) {
        Double infoScore = 0D;
        try {
            //1.�������ÿ��ֵ���ڷ���������������������ж�����true�����ٸ�false;
            Map<Object,List<Integer>> count4Values = new HashMap<Object,List<Integer>>();//key:��Ÿ����Բ�ֵͬ,value:����Ϊ2����Ÿ�����ֵ�Է�������������
            Integer size = records.size();
            field.setAccessible(true);
            for(BaseRecord record : records) {
                Object attrValue = field.get(record);
                List<Integer> countList = count4Values.get(attrValue);
                if(countList == null) {
                    countList = new ArrayList<Integer>(2);
                    countList.add(0,0);
                    countList.add(1,0);
                }
                if(record.getDecisionAttr()){
                    countList.set(0,countList.get(0) + 1);
                } else {
                    countList.set(1,countList.get(1) + 1);
                }
                count4Values.put(attrValue, countList);
            }

            //2.����map�������ֵ
            for(Object key : count4Values.keySet()) {
                List<Integer> countList = count4Values.get(key);
                double positCount = countList.get(0);
                double negatCount = countList.get(1);
                if(positCount == 0 || negatCount == 0) //����������������Ϊ0���������Ϊ��Ч���Է���Ӱ����󣬷���Ϊ0;
                    continue;
                double valueCount = positCount + negatCount;
                infoScore += valueCount/size * ( - (positCount/valueCount) * log2N(positCount / valueCount)
                        - (negatCount/valueCount) * log2N(negatCount/valueCount));
            }

        } catch (Exception ex) {
            System.out.println("method access exception");
        }
        return infoScore;

    }

}
