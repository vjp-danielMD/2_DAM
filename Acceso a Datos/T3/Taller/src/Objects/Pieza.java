package Objects;

public class Pieza {

    private String nombre;
    private String descripcion;
    private int stock;
    private double precio;
    private boolean unico;
    private int categoriaId;
    private int materialId;

    public Pieza() {
    }

    public Pieza(String nombre, String descripcion, int stock, double precio, boolean unico,
            int categoriaId, int materialId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.unico = unico;
        this.categoriaId = categoriaId;
        this.materialId = materialId;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isUnico() {
        return unico;
    }

    public void setUnico(boolean unico) {
        this.unico = unico;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }
}
