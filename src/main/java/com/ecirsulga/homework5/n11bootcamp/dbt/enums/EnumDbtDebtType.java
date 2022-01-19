package com.ecirsulga.homework5.n11bootcamp.dbt.enums;

public enum EnumDbtDebtType {


    MAINDEBT("MAINDEBT"),
    LATEDEBT("LATEDEBT")
    ;

    private String type;

    EnumDbtDebtType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }

}
