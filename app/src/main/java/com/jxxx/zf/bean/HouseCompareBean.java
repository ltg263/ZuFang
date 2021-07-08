package com.jxxx.zf.bean;

import java.util.List;

public class HouseCompareBean {

    /**
     * name : 租金方式
     * same : true
     * values : ["押一付二","押一付一","押一付二","押一付一","押一付一"]
     */

    private String name;
    private boolean same;
    private List<String> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSame() {
        return same;
    }

    public void setSame(boolean same) {
        this.same = same;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
