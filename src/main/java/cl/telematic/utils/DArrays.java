package cl.telematic.utils;

public enum DArrays {

    ARRAY1("DA_AO_01", 15, Double.class),
    ARRAY2("DA_AO_S_01", 15, Double.class),
    ARRAY3("DA_TStat_1000", 10, Double.class),
    ARRAY4("DA_Tstat_3000", 10, Double.class),
    ARRAY5("DA_Dev_1000", 10, Double.class),
    ARRAY6("DA_Dev_3000", 10, Double.class);

    private String name;
    private Integer length; //Number of Values
    //Types: Float, Bit, UInt16, SInt16, Packed_Bit, Byte, Packed_Byte, Swapped_Byte
    // Assumed that all are doubles (they should be Float in FieldServer)
    private Class type;

    //Type of Object Holding is not neccesary


    private DArrays(String name, Integer length, Class type) {
        this.name = name;
        this.length = length;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Integer getLength() {
        return length;
    }

    public Class getType() {
        return type;
    }
}
