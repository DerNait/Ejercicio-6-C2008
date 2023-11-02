/*
Kevin Josué Villagrán Mérida - 23584
Ejercicio #6 
Fecha de creación: 1/11/2023 14:15
Fecha de ultima modificación: 17/10/2023 18:06
*/
public class Telefono implements DispositivoElectronico{

    private boolean encendido;
    private String modelo;

    public Telefono(String modelo){
        this.modelo = modelo;
    }

    @Override
    public void encender(){
        encendido = true;
    }

    @Override
    public void apagar(){
        encendido = false;
    }

    @Override
    public String verificarEncendido(){
        return (this.encendido ? "Encendido" : "Apagado");
    }

    @Override
    public String toString(){
        return "\n=== TELEFONO ===" + "\nModelo: " + modelo + "\nEstado: " + verificarEncendido();
    }

    @Override
    public String getDatos(){
        return "Telefono;;"+modelo+";"+verificarEncendido();
    }
}