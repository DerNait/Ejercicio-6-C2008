/*
Kevin Josué Villagrán Mérida - 23584
Ejercicio #6 
Fecha de creación: 1/11/2023 14:15
Fecha de ultima modificación: 1/11/2023 18:06
*/
public interface DispositivoElectronico{

    //Metodos para los dispositivos electronicos
    public void encender();

    public void apagar();

    public String verificarEncendido();//Verificar si el dispositivo esta o no encendido.

    /*Decidi agregar este nuevo metodo que originalmente no estaba en el analisis y diseño, pues no es que sea tanto para la funcionalidad de las subclases, 
    si no que es para pasar los datos del dispositivo con el formato del CSV, originalmente no previ eso, pero con este unico metodo se cambia eso.*/
    public String getDatos();
}