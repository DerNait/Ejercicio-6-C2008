/*
Kevin Josué Villagrán Mérida - 23584
Ejercicio #6 
Fecha de creación: 1/11/2023 14:15
Fecha de ultima modificación: 17/10/2023 18:06
*/

import java.util.*;
import java.io.*;

public class ElectroTech{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        ArrayList<DispositivoElectronico> dispositivos;

        dispositivos = cargarCSV("dispositivos_electrotech.csv");

        boolean salir = false;//Permite salir del programa
        String seleccion;

        System.out.println("\nBienvenido al sistema de registro del campeonato");

        while(!salir){//Menu principal
            System.out.println("\n=== MENU DE OPCIONES ===");
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Registrar dispositivos");           
            System.out.println("2. Mostrar todos la informacion de cada dispositivo");
            System.out.println("3. Mostrar que elementos se encuentran encendidos y cuales estan apagados");   
            System.out.println("4. Salir");

            seleccion = scan.nextLine();

            switch(seleccion){//Depende de la seleccion del jugador, se hace cada opcion del menu
                case "1":
                    registrarDispositivo(dispositivos);
                    break;
                case "2":
                    mostrarDispositivos(dispositivos);
                    break;
                case "3":
                    verEncendidosApagados(dispositivos);
                    break;
                case "4":
                    guardarCSV(dispositivos);
                    salir = true;
                    break;
                default://Si mete un valor invalido
                    System.out.println("Ingrese un valor numerico valido");
            }
        }
    }

    public static void registrarDispositivo(ArrayList<DispositivoElectronico> dispositivos){
        Scanner scan = new Scanner(System.in);
        boolean anException = false;
        String input;

        System.out.println("\n=== Registro de dispositivos ===");
        System.out.println("¿Que tipo dispositivo deseas registrar? 1. Computadora, 2. Telefono");        
        input = scan.nextLine();

        switch(input){
            case "1":
                System.out.println("\nIngrese la marca de la computadora: ");
                input = scan.nextLine();
                Computadora computadora = new Computadora(input);
                
                System.out.println("\n¿Desea encender el dispositivo? 1. Si, 2. No");
                input = scan.nextLine();
                switch(input){
                    case "1":
                        computadora.encender();
                        break;
                    case "2":
                        computadora.apagar();
                        break;
                    default:
                        System.out.println("\nIngrese un valor numerico valido, por defecto, el dispositivo estara apagado"); 
                        break;
                }

                dispositivos.add(computadora);
                System.out.println("\nComputadora registrada correctamente... ");
                break;
            case "2":
                System.out.println("\nIngrese el modelo del telefono: ");
                input = scan.nextLine();
                Telefono telefono = new Telefono(input);
                
                System.out.println("\n¿Desea encender el dispositivo? 1. Si, 2. No");
                input = scan.nextLine();
                switch(input){
                    case "1":
                        telefono.encender();
                        break;
                    case "2":
                        telefono.apagar();
                        break;
                    default:
                        System.out.println("\nIngrese un valor numerico valido, por defecto, el dispositivo estara apagado"); 
                        break;
                }

                dispositivos.add(telefono);
                System.out.println("\nTelefono registrado correctamente... ");
                break;
            default:
                System.out.println("Ingrese un valor numerico valido");  
                break;
        }
    }

    public static void mostrarDispositivos(ArrayList<DispositivoElectronico> dispositivos){
        for(DispositivoElectronico dispositivo : dispositivos)
            System.out.println(dispositivo);
    }

    public static void verEncendidosApagados(ArrayList<DispositivoElectronico> dispositivos){
        System.out.println("\n=== Dispositivos que se encuentran apagados ===");
        for(DispositivoElectronico dispositivo : dispositivos){
            if(dispositivo.verificarEncendido().equals("Apagado"))
                System.out.println(dispositivo);
        }

        System.out.println("\n=== Dispositivos que se encuentran encendidos ===");
        for(DispositivoElectronico dispositivo : dispositivos){
            if(dispositivo.verificarEncendido().equals("Encendido"))
                System.out.println(dispositivo);
        }
    }

    public static ArrayList<DispositivoElectronico> cargarCSV(String path){
        ArrayList<DispositivoElectronico> dispositivos = new ArrayList<DispositivoElectronico>();

        try{
            Scanner scan = new Scanner(new File(path));
            scan.useDelimiter(";");

            if(scan.hasNextLine()){
                scan.nextLine();
            }

            while(scan.hasNextLine()){
                String[] dispositivosData = scan.nextLine().split(";");

                switch(dispositivosData[0]){
                    case "Computadora":
                        Computadora computadora = new Computadora(dispositivosData[1]);
                        if(dispositivosData[3].equals("Encendido"))
                            computadora.encender();
                        
                        dispositivos.add(computadora);
                        break;
                    case "Telefono":
                        Telefono telefono = new Telefono(dispositivosData[2]);
                        if(dispositivosData[3].equals("Encendido"))
                            telefono.encender();

                        dispositivos.add(telefono);
                        break;
                }
            }

        System.out.println("Archivo de dispositivos cargados correctamente...");

        }catch(Exception e){
            System.out.println("\nNo se ha podido cargar el archivo, puede que todavia no exista...");
            System.out.println("Motivo: " + e);
        }

        return dispositivos;
    }

    public static void guardarCSV(ArrayList<DispositivoElectronico> dispositivos){
        File archivoCSV = new File("dispositivos_electrotech.csv");

        try{
            PrintWriter out = new PrintWriter(archivoCSV);

            String[] titulos = {"Dispositivo", "Marca", "Modelo","Estado"};

            for(String titulo : titulos)
                out.print(titulo + ";");

            out.println();

            for(DispositivoElectronico dispositivo : dispositivos){
                out.println(dispositivo.getDatos());
            }

            out.close();

            System.out.println("\nArchivo csv guardado correctamente....");
        }catch(FileNotFoundException e){
            System.out.println("No se ha encontrado el archivo");
        }
    }
}