/*
Kevin Josué Villagrán Mérida - 23584
Ejercicio #6 
Fecha de creación: 1/11/2023 14:15
Fecha de ultima modificación: 1/11/2023 18:06
*/
public class Telefono implements DispositivoElectronico{

    //Atributos
    private boolean encendido;
    private String modelo;

    public Telefono(String modelo){//Constructor que define el modelo del telefono
        this.modelo = modelo;
    }

    @Override
    public void encender(){//Para encender el dispositivo
        encendido = true;
    }

    @Override
    public void apagar(){//Para apagar el dispositivo
        encendido = false;
    }

    @Override
    public String verificarEncendido(){//Verificar si esta encendido o apagado
        return (this.encendido ? "Encendido" : "Apagado");
    }

    @Override
    public String toString(){//Mostrar a detalle los datos de este dispositivo
        return "\n=== TELEFONO ===" + "\nModelo: " + modelo + "\nEstado: " + verificarEncendido();
    }

    @Override
    public String getDatos(){//Se define el nuevo metodo para pasar los datos al CSV
        return "Telefono;;"+modelo+";"+verificarEncendido();
    }
}