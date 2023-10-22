package pe.isil.cliente_2978.model;

import lombok.Data;

@Data
public class Pais {
    private Integer id;
    private String continente;
    private String nombre;
    private Integer provincias;
    private boolean isBeach;

    public boolean isBeach() {
        return isBeach;
    }

    public void setBeach(boolean beach) {
        isBeach = beach;
    }
}
