/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author    -Alejandro Torreguitart - 
 */
public class Podometro {

    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;

    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborales;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String marcaPodometro) {
        marca = marcaPodometro;
        altura = 0;
        longitudZancada = 0;
        totalPasosLaborales = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        sexo = 'M';

    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        if (queSexo == 'H'){
            longitudZancada = Math.ceil(queAltura * ZANCADA_HOMBRE);
            sexo = queSexo;
            altura = queAltura;
        }
        else {
            longitudZancada = Math.floor(queAltura * ZANCADA_MUJER);
            sexo = queSexo;
        }
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFin – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        if (dia == 1 || dia == 2 || dia == 3 || dia == 4 || dia == 5 && horaInicio < 2100){
            totalPasosLaborales = totalPasosLaborales + pasos;

            totalDistanciaSemana = totalDistanciaSemana + 
            ((totalPasosLaborales * longitudZancada) / 10000) + totalDistanciaFinSemana;

            tiempo = ((horaFin - horaInicio) * 60) + tiempo; 
        } 
        else if(dia == SABADO && horaInicio < 2100){
            totalPasosSabado = totalPasosSabado + pasos;

            totalDistanciaFinSemana = totalDistanciaFinSemana + 
            (((totalPasosSabado + totalPasosDomingo) * longitudZancada) / 10000);  

            tiempo = ((horaFin - horaInicio) * 60) + tiempo;
        }
        else if (dia == DOMINGO && horaInicio < 2100){
            totalPasosDomingo = totalPasosDomingo + pasos;

            totalDistanciaFinSemana = totalDistanciaFinSemana + 
            (((totalPasosSabado + totalPasosDomingo) * longitudZancada) / 10000);

            tiempo = ((horaFin - horaInicio) * 60) + tiempo;
        }
        else  if (dia == 1 || dia == 2 || dia == 3 || dia == 4 || dia == 5 && horaInicio > 2100){
            totalPasosLaborales = totalPasosLaborales + pasos;

            totalDistanciaSemana = totalDistanciaSemana + 
            ((totalPasosLaborales * longitudZancada) / 10000) + totalDistanciaFinSemana;

            tiempo = ((horaFin - horaInicio) * 60) + tiempo; 

            caminatasNoche = caminatasNoche + 1;
        } 
        else if(dia == SABADO && horaInicio > 2100){
            totalPasosSabado = totalPasosSabado + pasos;

            totalDistanciaFinSemana = totalDistanciaFinSemana + 
            (((totalPasosSabado + totalPasosDomingo) * longitudZancada) / 10000);

            totalDistanciaSemana = totalDistanciaFinSemana + totalDistanciaSemana;

            tiempo = ((horaFin - horaInicio) * 60) + tiempo;

            caminatasNoche = caminatasNoche + 1;
        }
        else if (dia == DOMINGO && horaInicio > 2100){
            totalPasosDomingo = totalPasosDomingo + pasos;

            totalDistanciaFinSemana = totalDistanciaFinSemana + 
            (((totalPasosSabado + totalPasosDomingo) * longitudZancada) / 10000);

            totalDistanciaSemana = totalDistanciaFinSemana + totalDistanciaSemana;

            tiempo = ((horaFin - horaInicio) * 60) + tiempo;

            caminatasNoche = caminatasNoche + 1;
        }
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        altura = altura / 100;
        System.out.println("Configuracion del podómetro\n*****************************"
            + "\nAltura: " + altura + " mtos" + "\nSexo : " + sexo + "\nLongitud zancada: " 
            + longitudZancada + " mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas\n*****************************" + 
            "Distancia recorrida toda la semana: " + totalDistanciaSemana + " Km" +
            "Distancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km" +
            "\n\nNº pasos días laborables: " + totalPasosLaborales + 
            "\nNº pasos SÁBADO: " + totalPasosSabado + 
            "\nNº pasos DOMINGO: " + totalPasosDomingo + 
            "\n\nNº caminatas realizadas a partir de las 21h: " + caminatasNoche + 
            "\n\nTiempo total caminando en toda la semana: " + tiempo +
            "\nDía/s con más pasos caminados : " );
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    //String
    public void  diaMayorNumeroPasos() {

    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        altura = 0;
        longitudZancada = 0;
        totalPasosLaborales = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        sexo = 'M';
    }
}
