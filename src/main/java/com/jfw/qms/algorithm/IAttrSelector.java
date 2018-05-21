package com.jfw.qms.algorithm;

import com.jfw.qms.data.BaseRecord;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public interface IAttrSelector {
    public Field select(List<BaseRecord> records, Set<Field> atrrs);
}
