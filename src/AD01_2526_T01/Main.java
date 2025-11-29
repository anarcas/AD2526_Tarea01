/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AD01_2526_T01;

import java.util.Scanner;

/**
 * Clase Principal
 *
 * @author Antonio Naranjo Castillo
 */
public class Main {

    /**
     * Método Principal ejecución del programa
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // DECLARACIÓN DE VARIABLES
        Scanner teclado;
        int opcion;
        Usuario user;
        String id;
        String password;
        String address;
        int yearbirth;
        String mensaje;
        String nombreFichero;
        String nombreFicheroTXT;
        boolean existenciaArchivo;
        String salidaPermitida = "N";
        String restaurarDatos = "N";

        // Solicitar el nombre del archivo para grabar los datos al inciar el aplicativo
        // Luego, comprobar si existe el archivo al iniciar por primera vez el programa y antes de mostrar el menú
        System.out.println("Introduzca el nombre del archivo para grabar la lista de usuarios, por defecto introducir 'user.dat'");
        teclado = new Scanner(System.in);
        nombreFichero = teclado.nextLine();
        existenciaArchivo = Usuario.comprobarExistenciaArchivo(nombreFichero);

        // Si el archivo no existe se le notificará al usuario que la aplicación no tiene datos grabados
        // Y si existe, se mostrarán los datos
        if (existenciaArchivo) {
            System.out.println(String.format("Existe el archivo '%s' a continuación se muestran los datos grabados.", nombreFichero));
            Usuario.cargarLista(nombreFichero);
            System.out.println("\nDatos grabados en disco:\n");
            Usuario.mostrarListaUsuarios();
        } else {
            System.out.println(String.format("El archivo '%s' no existe, no se mostrarán datos grabados.", nombreFichero));
        }

        // Se implementa un bucle do-while para reproducir el menú tantas veces como opción distinta de "S" (Salida permitida = SI) se presenten por el usuario
        // De esta manera se deberá insertar la letra S para salir del programa intencionadamente, evitando errores de salida del programa al pulsar accidentalmente cualquier otra tecla del teclado
        do {

            // Menú
            System.out.println("\n----");
            System.out.println("MENÚ");
            System.out.println("----\n");
            System.out.println("\t1. Agregar un usuario a la lista de usuarios.");
            System.out.println("\t2. Borrar un usuario a la lista de usuarios.");
            System.out.println("\t3. Guardar la lista de usuarios en un archivo (serialización).");
            System.out.println("\t4. Cargar la lista de usuarios desde el archivo (deserialización).");
            System.out.println("\t5. Mostrar los usuarios en la consola.");
            System.out.println("\t6. Exportar a fichero .txt la lista de usuarios.\n");
            System.out.println("\t0. Salir de la aplicación.\n");
            System.out.print("\t\t---> Opción seleccionada por el usuario: ");

            // Recoger opción seleccionada por el usuario
            opcion = Integer.parseInt(teclado.nextLine().trim());

            // Estructura selectiva para ejecutar una determinada acción demandada por el usuario
            switch (opcion) {

                // Salir de la aplicación
                case 0:
                    mensaje="Salir de la aplicación";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion,mensaje));

                    Usuario.cargarListaGrabada(nombreFichero);
                    if (!Usuario.compararListas()) {
                        System.out.println("Ha habido cambios en el programa que todavía no se han guardado. Si desea guardarlos ejecute la opción correspondiente del menú. Si sale ahora no se guardarán. ¿Está seguro de que desea salir sin guardar? (S/N)");
                        salidaPermitida = teclado.nextLine().trim();
                    } else{
                        salidaPermitida="S";
                    }
                    break;

                // Agregar un usuario a la lista de usuarios
                case 1:
                    mensaje="Agregar un usuario a la lista de usuarios";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion,mensaje));

                    System.out.println("Introduzca el identificador del usuario");
                    id = teclado.nextLine();
                    System.out.println("Introduzca la contraseña del usuario");
                    password = teclado.nextLine();
                    System.out.println("Introduzca el dirección del usuario");
                    address = teclado.nextLine();
                    System.out.println("Introduzca el año de nacimiento del usuario");
                    yearbirth = Integer.parseInt(teclado.nextLine());

                    // Instanciación del objeto user de la clase Usuario
                    user = new Usuario(id, password, address, yearbirth);
                    // Se agrega el usuario a la lista de usuarios
                    user.agregarUsuario(user);
                    break;

                // Borrar un usuario a la lista de usuarios
                case 2:
                    mensaje="Borrar un usuario a la lista de usuarios";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion,mensaje));
                    // Se solicita al usuario el identificador del usuario a eliminar
                    System.out.println("Introduzca el identificador del usuario a eliminar");
                    id = teclado.nextLine();
                    // Se llama al método estático de la clase Usuario para eliminar el usuario solicitado
                    Usuario.borrarUsuario(id);
                    break;

                // Guardar la lista de usuarios en un archivo (serialización)
                case 3:
                    mensaje="Guardar la lista de usuarios en un archivo (serialización)";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion,mensaje));
                    //System.out.println("Introduzca el nombre del archivo para guardar la lista de usuarios, por defecto introducir 'user.dat'");
                    //nombreFichero = teclado.nextLine();
                    Usuario.guardarLista(nombreFichero);
                    break;

                // Cargar la lista de usuarios desde el archivo (deserialización)
                case 4:
                    mensaje="Cargar la lista de usuarios desde el archivo (deserialización)";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion,mensaje));
                    //System.out.println("Introduzca el nombre del archivo del cual cargar la lista de usuarios, por defecto introducir 'user.dat'");
                    //nombreFichero = teclado.nextLine();
                    Usuario.cargarListaGrabada(nombreFichero);
                    if (!Usuario.compararListas()) {
                        System.out.println("Ha realizado cambios que no se ha guardado en disco. Si continúa la carga del archivo se restaurarán los datos de disco y se perderán los cambios no guardados. ¿Desea continuar con la carga y restaurar los datos del archivo? (S/N)");
                        restaurarDatos = teclado.nextLine();
                    }
                    if (restaurarDatos.equals("S")) {
                        Usuario.cargarLista(nombreFichero);
                    }
                    break;

                // Mostrar los usuarios en la consola
                case 5:
                    mensaje="Mostrar los usuarios en la consola";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion,mensaje));
                    // Se muestran en consola los usuarios de la lista de usuarios
                    Usuario.mostrarListaUsuarios();
                    break;

                // Exportar a fichero .txt la lista de usuarios
                case 6:
                    mensaje="Exportar a fichero .txt la lista de usuarios";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion,mensaje));
                    System.out.println("Introduzca el nombre del archivo de texto txt para exportar la lista de usuarios, por defecto introducir 'user.txt'");
                    nombreFicheroTXT = teclado.nextLine();
                    // Se escribe el contenido de la lista de usuario en un archivo TXT
                    Usuario.escribirTXT(nombreFicheroTXT);

                    break;

                // Mostrar un mensaje de error en la consola en caso de introducir una opción no permitida
                default:
                    if (opcion < 0 || opcion > 6) {
                        System.err.println(String.format("\nEl usuario ha introducido la opción '%d' incorrecta, debe ingresar una opción comprendida entre 0 y 6.", opcion));
                    }
            }

        } while (!salidaPermitida.equals("S"));

        // Se cierra el flujo de entrada del objeto teclado de la clase Scanner
        teclado.close();

    }

}
