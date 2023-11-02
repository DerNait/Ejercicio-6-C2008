/*
Kevin Josué Villagrán Mérida - 23584
Ejercicio #6 
Fecha de creación: 1/11/2023 14:15
Fecha de ultima modificación: 1/11/2023 18:06
*/

import java.util.*;//Importamos las librerias
import java.io.*;

public class ElectroTech{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        ArrayList<DispositivoElectronico> dispositivos;

        dispositivos = cargarCSV("dispositivos_electrotech.csv");//Cargamos el CSV en una unica lista de dispositivos electronicos

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
        String input;

        System.out.println("\n=== Registro de dispositivos ===");
        System.out.println("¿Que tipo dispositivo deseas registrar? 1. Computadora, 2. Telefono");        
        input = scan.nextLine();

        switch(input){//Segun el dispositivo que el usuario desea registrar, se hace lo siguiente
            case "1":
                System.out.println("\nIngrese la marca de la computadora: ");
                input = scan.nextLine();
                Computadora computadora = new Computadora(input);
                
                System.out.println("\n¿Desea encender el dispositivo? 1. Si, 2. No");
                input = scan.nextLine();
                switch(input){//En caso de que desee encenderlo, se encendera, de lo contrario pues no
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

                dispositivos.add(computadora);//Se agrega el dispositivo a la lista
                System.out.println("\nComputadora registrada correctamente... ");
                break;
            case "2":
                System.out.println("\nIngrese el modelo del telefono: ");
                input = scan.nextLine();
                Telefono telefono = new Telefono(input);
                
                System.out.println("\n¿Desea encender el dispositivo? 1. Si, 2. No");
                input = scan.nextLine();
                switch(input){//En caso de que desee encenderlo, se encendera, de lo contrario pues no
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

                dispositivos.add(telefono);//Se agrega el dispositivo a la lista
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
        for(DispositivoElectronico dispositivo : dispositivos){//Recorre la lista de dispositivos
            if(dispositivo.verificarEncendido().equals("Apagado"))//Busca todos los que esten apagados y los muestra en pantalla
                System.out.println(dispositivo);
        }

        System.out.println("\n=== Dispositivos que se encuentran encendidos ===");
        for(DispositivoElectronico dispositivo : dispositivos){//Recorre la lista de dispositivos
            if(dispositivo.verificarEncendido().equals("Encendido"))//Busca todos los que esten encendidos y los muestra en pantalla
                System.out.println(dispositivo);
        }
    }

    public static ArrayList<DispositivoElectronico> cargarCSV(String path){
        ArrayList<DispositivoElectronico> dispositivos = new ArrayList<DispositivoElectronico>();

        try{
            Scanner scan = new Scanner(new File(path));//Se lee el archivo CSV
            scan.useDelimiter(";");

            if(scan.hasNextLine()){//Se salta la linea de titulos
                scan.nextLine();
            }

            while(scan.hasNextLine()){a
                String[] dispositivosData = scan.nextLine().split(";");//Se separa cada casill

                switch(dispositivosData[0]){
                    case "Computadora"://En caso de que el dispositivo sea una computadora
                        Computadora computadora = new Computadora(dispositivosData[1]);
                        if(dispositivosData[3].equals("Encendido"))//Si esta encendido en el CSV, instanciamos el objeto y lo encendemos
                            computadora.encender();
                        
                        dispositivos.add(computadora);//Se agrega el dispositivo a la lista
                        break;
                    case "Telefono":
                        Telefono telefono = new Telefono(dispositivosData[2]);
                        if(dispositivosData[3].equals("Encendido"))//Si esta encendido en el CSV, instanciamos el objeto y lo encendemos
                            telefono.encender();

                        dispositivos.add(telefono);//Se agrega el dispositivo a la list
                        break;
                }
            }

        System.out.println("Archivo de dispositivos cargados correctamente...");

        }catch(Exception e){//Si no se puede cargar el archivo, se mostrara el siguiente mensaje
            System.out.println("\nNo se ha podido cargar el archivo, puede que todavia no exista...");
            System.out.println("Motivo: " + e);
        }

        return dispositivos;//Retornamos el valor de la lista, el cual se le asigna a la lista general
    }

    public static void guardarCSV(ArrayList<DispositivoElectronico> dispositivos){
        File archivoCSV = new File("dispositivos_electrotech.csv");//Se prepara el archivo que se creara

        try{
            PrintWriter out = new PrintWriter(archivoCSV);//Para escribir en el archivo

            String[] titulos = {"Dispositivo", "Marca", "Modelo","Estado"};

            for(String titulo : titulos)
                out.print(titulo + ";");//Escribimos los titulos

            out.println();

            for(DispositivoElectronico dispositivo : dispositivos){
                out.println(dispositivo.getDatos());//Por cada dispositivo en la lista, se escriben sus respectivos datos en el CSV
            }

            out.close();//Cerramos y guardamos el archivo

            System.out.println("\nArchivo csv guardado correctamente....");
        }catch(FileNotFoundException e){//En caso de que no se pueda crear
            System.out.println("No se ha encontrado el archivo");
        }
    }
}