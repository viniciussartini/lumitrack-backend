package br.com.backend.Lumitrack.models.enums;

public enum Profile {
    ADMIN(1),
    COMMON(2);

    private int code;

    private Profile(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Profile valueOf(int code) {
        for(Profile value : Profile.values()) {
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new RuntimeException("fazer algo aqui"); // Fazer uma Exception personalizada
    }
}
