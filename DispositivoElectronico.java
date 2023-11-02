/*
Kevin Josué Villagrán Mérida - 23584
Ejercicio #6 
Fecha de creación: 1/11/2023 14:15
Fecha de ultima modificación: 17/10/2023 18:06
*/
public interface DispositivoElectronico{

    //Metodos para los dispositivos electronicos
    public void encender();

    public void apagar();

    public String verificarEncendido();//Verificar si el dispositivo esta o no encendido.

    public String getDatos();
}