package Model;

public class Etapa {
    // Atributos
    private String nombre;
    private Date fechaInicioPlanificada;
    private Date fechaFinPlanificada;
    private Date fechaInicioReal;
    private Date fechaFinReal;
    private boolean aprobacionCumplimiento;

    // Constructor
    public Etapa(String nombre, Date fechaInicioPlanificada, Date fechaFinPlanificada) {
        this.nombre = nombre;
        this.fechaInicioPlanificada = fechaInicioPlanificada;
        this.fechaFinPlanificada = fechaFinPlanificada;
        this.fechaInicioReal = null;
        this.fechaFinReal = null;
        this.aprobacionCumplimiento = false;
    }

    // Métodos
    public void actualizarEtapa(Date fechaInicioReal, Date fechaFinReal, boolean aprobacionCumplimiento) {
        this.fechaInicioReal = fechaInicioReal;
        this.fechaFinReal = fechaFinReal;
        this.aprobacionCumplimiento = aprobacionCumplimiento;
    }

    public void guardarEtapa() {
        // Implementación para guardar la etapa en la base de datos
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicioPlanificada() {
        return fechaInicioPlanificada;
    }

    public void setFechaInicioPlanificada(Date fechaInicioPlanificada) {
        this.fechaInicioPlanificada = fechaInicioPlanificada;
    }

    public Date getFechaFinPlanificada() {
        return fechaFinPlanificada;
    }

    public void setFechaFinPlanificada(Date fechaFinPlanificada) {
        this.fechaFinPlanificada = fechaFinPlanificada;
    }

    public Date getFechaInicioReal() {
        return fechaInicioReal;
    }

    public void setFechaInicioReal(Date fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
    }

    public Date getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(Date fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }

    public boolean isAprobacionCumplimiento() {
        return aprobacionCumplimiento;
    }

    public void setAprobacionCumplimiento(boolean aprobacionCumplimiento) {
        this.aprobacionCumplimiento = aprobacionCumplimiento;
    }
}
