package br.com.backend.Lumitrack.models.enums;

public enum BuildingType {

    APARTMENT(1),
    HOUSE(2),
    COMMERCE(3),
    SCHOOL(4),
    OFFICE(5);

    private int code;

    private BuildingType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BuildingType valueOf(int code) {
        for(BuildingType value: BuildingType.values()) {
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new RuntimeException("fazer algo aqui"); // Fazer uma Exception personalizada
    }
}
