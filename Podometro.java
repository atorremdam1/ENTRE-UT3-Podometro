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
            longitudZancada = Math.ceil(queAltura * ZANCADA_HOMBRE) / 100;
            sexo = queSexo;
            altura = queAltura;
        }
        else {
            longitudZancada = Math.floor(queAltura * ZANCADA_MUJER) / 100;
            sexo = queSexo;
            altura = queAltura;
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
        tiempo = (((horaFin / 100) - (horaInicio / 100))) + tiempo;

        totalDistanciaSemana = (totalPasosLaborales * longitudZancada) / 10000
        + totalDistanciaSemana + totalDistanciaFinSemana; 
        
         if(horaInicio >= 2100){
            caminatasNoche++;
        }
       
        switch(dia){
            case 1:  
            case 2: 
            case 3: 
            case 4: 
            case 5: totalPasosLaborales = totalPasosLaborales + pasos;
                break;
            case 6: totalPasosSabado = totalPasosSabado + pasos;
                totalDistanciaFinSemana = ((totalPasosSabado + totalPasosDomingo) * longitudZancada) 
                / 10000 + totalDistanciaFinSemana;  
                break;
            case 7: totalPasosDomingo = totalPasosDomingo + pasos;
                totalDistanciaFinSemana = ((totalPasosSabado + totalPasosDomingo) * longitudZancada) 
                / 10000 + totalDistanciaFinSemana; 
                break;
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
        String diaMayorNumeroPasos;
        int horas = tiempo;
        int minutos = horas % 60;
        System.out.println("Estadísticas\n*****************************" + 
            "\nDistancia recorrida toda la semana: " + totalDistanciaSemana + " Km" +
            "\nDistancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km" +
            "\n\nNº pasos días laborables: " + totalPasosLaborales + 
            "\nNº pasos SÁBADO: " + totalPasosSabado + 
            "\nNº pasos DOMINGO: " + totalPasosDomingo + 
            "\n\nNº caminatas realizadas a partir de las 21h: " + caminatasNoche + 
            "\n\nTiempo total caminando en toda la semana: " + horas + "h." + 
            " y " + minutos + "m." + 
            "\nDía/s con más pasos caminados : " + diaMayorNumeroPasos() );

    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String s = "";
        if (totalPasosLaborales > totalPasosDomingo || totalPasosLaborales > totalPasosSabado){
            s = "LABORALES";
        }
        else if(totalPasosDomingo > totalPasosLaborales || totalPasosDomingo > totalPasosSabado) {
            s = "DOMINGO";
        }
        else {
            s = "SABADO";
        }
        return s;
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
