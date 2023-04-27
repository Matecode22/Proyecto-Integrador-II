package Model;

public class Proyecto {
    private static int proyectosCreados = 0;
    private static final int LIMITE_PROYECTOS = 10;
    
    private String nombre;
    private String cliente;
    private Date fechaInicioPlanificada;
    private Date fechaFinPlanificada;
    private double presupuesto;
    private String gerenteGreenNombre;
    private String gerenteGreenNumeroCelular;
    private String gerenteClienteNombre;
    private String gerenteClienteNumeroCelular;
    private List<Etapa> etapas;
    private Etapa etapaActiva;
    
    public Proyecto(String nombre, String cliente, Date fechaInicioPlanificada, Date fechaFinPlanificada, double presupuesto, String gerenteGreenNombre, String gerenteGreenNumeroCelular, String gerenteClienteNombre, String gerenteClienteNumeroCelular) {
        if (proyectosCreados >= LIMITE_PROYECTOS) {
            throw new RuntimeException("Se ha alcanzado el límite de proyectos creados.");
        }
        
        proyectosCreados++;
        this.nombre = nombre;
        this.cliente = cliente;
        this.fechaInicioPlanificada = fechaInicioPlanificada;
        this.fechaFinPlanificada = fechaFinPlanificada;
        this.presupuesto = presupuesto;
        this.gerenteGreenNombre = gerenteGreenNombre;
        this.gerenteGreenNumeroCelular = gerenteGreenNumeroCelular;
        this.gerenteClienteNombre = gerenteClienteNombre;
        this.gerenteClienteNumeroCelular = gerenteClienteNumeroCelular;
        this.etapas = new ArrayList<Etapa>();
        crearEtapas();
        asignarFechasPlaneadas();
    }
    
    private void crearEtapas() {
        Etapa inicio = new Etapa("Inicio");
        Etapa analisis = new Etapa("Análisis");
        Etapa diseño = new Etapa("Diseño");
        Etapa ejecucion = new Etapa("Ejecución");
        Etapa cierre = new Etapa("Cierre");
        Etapa seguimientoControl = new Etapa("Seguimiento y control del proyecto");
        
        inicio.setActiva(true);
        etapas.add(inicio);
        etapas.add(analisis);
        etapas.add(diseño);
        etapas.add(ejecucion);
        etapas.add(cierre);
        etapas.add(seguimientoControl);
    }
    
    private void asignarFechasPlaneadas() {
        Scanner sc = new Scanner(System.in);
        int[] mesesEtapas = new int[6];
        
        for (int i = 0; i < mesesEtapas.length; i++) {
            System.out.println("Ingrese la cantidad de meses que llevará la etapa " + etapas.get(i).getNombre() + ": ");
            mesesEtapas[i] = sc.nextInt();
            etapas.get(i).setFechaInicioPlaneada(fechaFinPlanificada);
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaFinPlanificada);
            cal.add(Calendar.MONTH, mesesEtapas[i]);
            etapas.get(i).setFechaFinPlaneada(cal.getTime());
            fechaFinPlanificada = cal.getTime();
        }
    }
    
    public void actualizarEtapaActiva() {
        for (int i = 0; i < etapas.size(); i++) {
            Etapa etapa = etapas.get(i);
            if (etapa.isActiva()) {
                etapa.setActiva(false);
                if (i == etapas.size() - 1) {
                    etapaActiva = null;
            } else {
                    etapaActiva = etapas.get(i+1);
                    etapaActiva.setActiva(true);
                    etapaActiva.setFechaInicioReal(new Date());
            }
                break;
                    }
                    }
                    }
    public void avanzarDias(int dias) {
    if (etapaActiva == null) {
        System.out.println("No se puede avanzar días, el proyecto no tiene etapa activa.");
        return;
    }
    
    etapaActiva.avanzarDias(dias);
    
    if (etapaActiva.isFinalizada()) {
        actualizarEtapaActiva();
    }
}

    public void mostrarEstado() {
        System.out.println("Proyecto: " + nombre);
        System.out.println("Cliente: " + cliente);
        System.out.println("Fecha inicio planificada: " + fechaInicioPlanificada);
        System.out.println("Fecha fin planificada: " + fechaFinPlanificada);
        System.out.println("Presupuesto: " + presupuesto);
        System.out.println("Gerente de Green: " + gerenteGreenNombre);
        System.out.println("Número de celular del gerente de Green: " + gerenteGreenNumeroCelular);
        System.out.println("Gerente del cliente: " + gerenteClienteNombre);
        System.out.println("Número de celular del gerente del cliente: " + gerenteClienteNumeroCelular);
        
        if (etapaActiva == null) {
            System.out.println("El proyecto no tiene etapa activa.");
        } else {
            System.out.println("Etapa activa: " + etapaActiva.getNombre());
            System.out.println("Días restantes: " + etapaActiva.getDiasRestantes());
        }
    }

    public static boolean puedeCrearProyecto() {
        return proyectosCreados < LIMITE_PROYECTOS;
    }

    public static Proyecto crearProyecto(String nombre, String cliente, Date fechaInicioPlanificada, Date fechaFinPlanificada, double presupuesto, String gerenteGreenNombre, String gerenteGreenNumeroCelular, String gerenteClienteNombre, String gerenteClienteNumeroCelular) {
        if (!puedeCrearProyecto()) {
            System.out.println("Se ha alcanzado el límite de proyectos creados.");
            return null;
    }
    
        proyectosCreados++;
        Proyecto proyecto = new Proyecto(nombre, cliente, fechaInicioPlanificada, fechaFinPlanificada, presupuesto, gerenteGreenNombre, gerenteGreenNumeroCelular, gerenteClienteNombre, gerenteClienteNumeroCelular);
        return proyecto;
    }
    
    public void avanzarDias(int dias) {
    if (etapaActiva == null) {
        System.out.println("No se puede avanzar días, el proyecto no tiene etapa activa.");
        return;
    }
    
        etapaActiva.avanzarDias(dias);
        
        if (etapaActiva.isFinalizada()) {
            actualizarEtapaActiva();
        }
    }   

    public void mostrarEstado() {
        System.out.println("Proyecto: " + nombre);
        System.out.println("Cliente: " + cliente);
        System.out.println("Fecha inicio planificada: " + fechaInicioPlanificada);
        System.out.println("Fecha fin planificada: " + fechaFinPlanificada);
        System.out.println("Presupuesto: " + presupuesto);
        System.out.println("Gerente de Green: " + gerenteGreenNombre);
        System.out.println("Número de celular del gerente de Green: " + gerenteGreenNumeroCelular);
        System.out.println("Gerente del cliente: " + gerenteClienteNombre);
        System.out.println("Número de celular del gerente del cliente: " + gerenteClienteNumeroCelular);
        
        if (etapaActiva == null) {
            System.out.println("El proyecto no tiene etapa activa.");
        } else {
            System.out.println("Etapa activa: " + etapaActiva.getNombre());
            System.out.println("Días restantes: " + etapaActiva.getDiasRestantes());
        }
    }

    public static boolean puedeCrearProyecto() {
        return proyectosCreados < LIMITE_PROYECTOS;
    }

    public static Proyecto crearProyecto(String nombre, String cliente, Date fechaInicioPlanificada, Date fechaFinPlanificada, double presupuesto, String gerenteGreenNombre, String gerenteGreenNumeroCelular, String gerenteClienteNombre, String gerenteClienteNumeroCelular) {
        if (!puedeCrearProyecto()) {
            System.out.println("Se ha alcanzado el límite de proyectos creados.");
            return null;
        }
        
        proyectosCreados++;
        Proyecto proyecto = new Proyecto(nombre, cliente, fechaInicioPlanificada, fechaFinPlanificada, presupuesto, gerenteGreenNombre, gerenteGreenNumeroCelular, gerenteClienteNombre, gerenteClienteNumeroCelular);
        return proyecto;
    }

}