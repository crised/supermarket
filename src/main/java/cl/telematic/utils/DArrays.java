package cl.telematic.utils;

public enum DArrays {

    ARRAY1("DA_AO_01"), ARRAY2("DA_AO_S_01"),
    ARRAY3("DA_TStat_1000"), ARRAY4("DA_Tstat_3000"),
    ARRAY5("DA_Dev_1000"), ARRAY6("DA_Dev_3000");

    private String name;

    DArrays(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
