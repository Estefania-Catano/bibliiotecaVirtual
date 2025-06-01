import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int intentos = 0;
        System.out.println("Bienvenido a la biblioteca virtual");
        int dunePrestado = 0;
        int neuromantePrestado = 0;
        int fundacionPrestado = 0;
        int bajoEstrellaPrestado = 0;
        int corredorLaberintoPrestado = 0;
        int divergentePrestado = 0;
        int principitoPrestado = 0;
        int monstruosPrestado = 0;
        int harryPrestado = 0;
        while (intentos < 3) {
            System.out.print("Ingrese su nombre de usuario:");
            String usuario = sc.nextLine();
            System.out.print("Ingrese su contraseña:");
            String contrasena = sc.nextLine();
            if (usuario.equals("Andrea.Benitez") && contrasena.equals("1234")) {
                System.out.println("Bienvenido " + usuario);
                System.out.println("Ingreso exitoso");
                int cantidadLibros = 0;
                int opcionMenu = 0;
                int opcionCategoria = 0;
                int opcionLibro = 0;
                do {
                    System.out.println(
                            "Seleccione una opción: \n1. Realizar un prestamo de libro\n2. Realizar una Devolución de libro\n3. Consultar prestamos activos\n4. Consultar libros disponibles\n5. Salir");
                    opcionMenu = sc.nextInt();
                    switch (opcionMenu) {
/*============================================================= Caso 1 Menu ===============================================================================================================================================================*/
                        case 1:
                            System.out.println(
                                    "Las categorías de libros disponibles son: \n1. Ciencia ficción\n2. Juveniles\n3. Infantiles");
                            opcionCategoria = sc.nextInt();
                            switch (opcionCategoria) {
/*============================================================= Caso 1 Categoria===============================================================================================================================================================*/
                                case 1:
                                    System.out.println(
                                            "Ciencia ficción:\n0. Regresar \n1. Dune\n2. Neuromante\n3. Fundación");
                                    opcionLibro = sc.nextInt();
                                    switch (opcionLibro) {
                                        case 1:
                                        if (dunePrestado == 0) {
                                            System.out.println("Ha seleccionado el libro 'Dune'.");
                                            cantidadLibros++;
                                            dunePrestado = 1;
                                        } else {
                                            System.out.println("El libro 'Dune' ya está prestado.");
                                        }
                                            break;
                                        case 2:
                                        if (neuromantePrestado == 0) {
                                            System.out.println("Ha seleccionado el libro 'Neuromante'.");
                                            cantidadLibros++;
                                            neuromantePrestado++;
                                        } else {
                                            System.out.println("El libro 'Neuromante' ya está prestado.");
                                        }
                                            break;
                                        case 3:
                                        if (fundacionPrestado == 0) {
                                            System.out.println("Ha seleccionado el libro 'Fundación'.");
                                            cantidadLibros++;
                                            fundacionPrestado++;
                                        }else{
                                            System.out.println("El libro 'Fundación' ya está prestado.");
                                        }
                                            break;
                                        case 0:
                                            System.out.println("Regresando al menú principal.");
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                    }
                                    break;
/*============================================================= Caso 1 Categoria ===============================================================================================================================================================*/
/*============================================================= Caso 2 Categoria ===============================================================================================================================================================*/
                                case 2:
                                    System.out.println(
                                            "Juveniles :\n0. Regresar \n1. Bajo la misma estrella\n2. El corredor del laberinto\n3. Divergente");
                                    opcionLibro = sc.nextInt();
                                    switch (opcionLibro) {
                                        case 1:
                                        if (bajoEstrellaPrestado == 0) {
                                            System.out.println("Ha seleccionado el libro 'Bajo la misma estrella'.");
                                            cantidadLibros++;
                                            bajoEstrellaPrestado++;
                                        }else{
                                            System.out.println("El libro 'Bajo la misma estrella' ya está prestado.");
                                        }
                                            break;
                                        case 2:
                                        if (corredorLaberintoPrestado == 0) {
                                            System.out.println("Ha seleccionado el libro 'El corredor del laberinto'.");
                                            cantidadLibros++;
                                            corredorLaberintoPrestado++;
                                        }else{
                                            System.out.println("El libro 'El corredor del laberinto' ya está prestado.");
                                        }
                                            break;
                                        case 3:
                                        if (divergentePrestado == 0) {
                                            System.out.println("Ha seleccionado el libro 'Divergente'.");
                                            cantidadLibros++;
                                            divergentePrestado++;
                                        }else{
                                            System.out.println("El libro 'Divergente' ya está prestado.");
                                        }
                                            break;
                                        case 0:
                                            System.out.println("Regresando al menú principal.");
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                    }
                                    break;
/*============================================================= Caso 2 Categoria ===============================================================================================================================================================*/
/*============================================================= Caso 3 Categoria ===============================================================================================================================================================*/
                                case 3:
                                    System.out.println(
                                            "Infantiles:\n0. Regresar \n1. El principito\n2. Donde viven los monstruos\n3. Harry Potter y la piedra filosofal");
                                    opcionLibro = sc.nextInt();
                                    switch (opcionLibro) {
                                        case 1:
                                        if (principitoPrestado == 0) {
                                            System.out.println("Ha seleccionado el libro 'El principito'.");
                                            cantidadLibros++;
                                            principitoPrestado++;
                                        }else{
                                            System.out.println("El libro 'El principito' ya está prestado.");
                                        }
                                            break;
                                        case 2:
                                        if (monstruosPrestado == 0) {
                                            System.out.println("Ha seleccionado el libro 'Donde viven los monstruos'.");
                                            cantidadLibros++;
                                            monstruosPrestado++;
                                        }else{
                                            System.out.println("El libro 'Donde viven los monstruos' ya está prestado.");
                                        }
                                            break;
                                        case 3:
                                        if (harryPrestado == 0) {
                                            System.out.println(
                                                    "Ha seleccionado el libro 'Harry Potter y la piedra filosofal'.");
                                            cantidadLibros++;
                                            harryPrestado++;
                                        }else{
                                            System.out.println("El libro 'Harry Potter y la piedra filosofal' ya está prestado.");
                                        }
                                        break;
                                        case 0:
                                            System.out.println("Regresando al menú principal.");
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                    }
                                    break;
/*============================================================= Caso 3 Categoria ===============================================================================================================================================================*/

                                default:
                                    break;
                            }
                            break;
/*============================================================= Caso 1 Menu===============================================================================================================================================================*/
/*============================================================= Caso 2 Menu===============================================================================================================================================================*/
                        case 2:
                            System.out.println(
                                    "Seleccione la categoría del libro que desea devolver: \n1. Ciencia ficción\n2. Juveniles\n3. Infantiles");
                            opcionCategoria = sc.nextInt();
                            switch (opcionCategoria) {
/*============================================================= Caso 1 Devolucion ===============================================================================================================================================================*/
                                case 1:
                                    System.out.println(
                                            "Ciencia ficción:\n0. Regresar \n1. Dune\n2. Neuromante\n3. Fundación");
                                    opcionLibro = sc.nextInt();
                                    switch (opcionLibro) {
                                        case 1:
                                        if (dunePrestado == 1) {
                                            System.out.println("Ha devuelto el libro 'Dune'.");
                                            cantidadLibros--;
                                            dunePrestado = 0;  
                                        } else {
                                            System.out.println("No ha tomado el libro 'Dune' Por favor, verifique el libro que desea devolver.");                                          
                                        }
                                            break;
                                        case 2:
                                            System.out.println("Ha devuelto el libro 'Neuromante'.");
                                            cantidadLibros--;
                                            neuromantePrestado--;
                                            break;
                                        case 3:
                                            System.out.println("Ha devuelto el libro 'Fundación'.");
                                            cantidadLibros--;
                                            fundacionPrestado--;
                                            break;
                                        case 0:
                                            System.out.println("Regresando al menú principal.");
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                    }
                                    break;
/*============================================================= Caso 1 Devolucion ===============================================================================================================================================================*/
/*============================================================= Caso 2 Devolucion ===============================================================================================================================================================*/
                                case 2:
                                    System.out.println(
                                            "Juveniles:\n0. Regresar \n1. Bajo la misma estrella\n2. El corredor del laberinto\n3. Divergente");
                                    opcionLibro = sc.nextInt();
                                    switch (opcionLibro) {
                                        case 1:
                                        if (bajoEstrellaPrestado == 1) {
                                            System.out.println("Ha devuelto el libro 'Bajo la misma estrella'.");
                                            cantidadLibros--;
                                            bajoEstrellaPrestado--;
                                        }else{
                                            System.out.println("No ha tomado el libro 'Bajo la misma estrella'. Por favor, verifique el libro que desea devolver.");
                                        }
                                            break;
                                        case 2:
                                        if (corredorLaberintoPrestado == 1) {
                                            System.out.println("Ha devuelto el libro 'El corredor del laberinto'.");
                                            cantidadLibros--;
                                            corredorLaberintoPrestado--;
                                        }else{
                                            System.out.println("No ha tomado el libro 'El corredor del laberinto'. Por favor, verifique el libro que desea devolver.");
                                        }
                                            break;
                                        case 3:
                                        if (divergentePrestado == 1) {
                                            System.out.println("Ha devuelto el libro 'Divergente'.");
                                            cantidadLibros--;
                                            divergentePrestado--;
                                        }else{
                                            System.out.println("No ha tomado el libro 'Divergente'. Por favor, verifique el libro que desea devolver.");
                                        }
                                            break;
                                        case 0:
                                            System.out.println("Regresando al menú principal.");
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                    }
                                    break;
/*============================================================= Caso 2 Devolucion =============================================================================================================================================================== */
/*============================================================= Caso 3 Devolucion =============================================================================================================================================================== */
                                case 3:
                                    System.out.println(
                                            "Infantiles:\n0. Regresar \n1. El principito\n2. Donde viven los monstruos\n3. Harry Potter y la piedra filosofal");
                                    opcionLibro = sc.nextInt();
                                    switch (opcionLibro) {
                                        case 1:
                                        if (principitoPrestado == 1) {
                                            System.out.println("Ha devuelto el libro 'El principito'.");
                                            cantidadLibros--;
                                            principitoPrestado--;
                                        } else {
                                            System.out.println("No ha tomado el libro 'El principito'. Por favor, verifique el libro que desea devolver.");
                                        }
                                            break;
                                        case 2:
                                        if (monstruosPrestado == 1) {
                                            System.out.println("Ha devuelto el libro 'Donde viven los monstruos'.");
                                            cantidadLibros--;
                                            monstruosPrestado--;
                                        } else {
                                            System.out.println("No ha tomado el libro 'Donde viven los monstruos'. Por favor, verifique el libro que desea devolver.");
                                        }
                                            break;
                                        case 3:
                                        if (harryPrestado == 1) {
                                            System.out.println(
                                                    "Ha devuelto el libro 'Harry Potter y la piedra filosofal'.");
                                            cantidadLibros--;
                                            harryPrestado--;
                                        } else {
                                            System.out.println("No ha tomado el libro 'Harry Potter y la piedra filosofal'. Por favor, verifique el libro que desea devolver.");
                                        }
                                            break;
                                        case 0:
                                            System.out.println("Regresando al menú principal.");
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                    }
                                    break;
/* ============================================================= Caso 3 Devolucion ===============================================================================================================================================================*/
                                default:
                                    break;
                            }
                            break;
/*============================================================= Caso 2 Menu ===============================================================================================================================================================*/
/*============================================================= Caso 3 Menu Sin definir ===============================================================================================================================================================*/
                        case 3:
                            System.out.println("Libros prestados:");
                            if (dunePrestado == 1) {
                                System.out.println("Dune");
                            }
                            if (neuromantePrestado == 1) {
                                System.out.println("Neuromante");
                            }
                            if (fundacionPrestado == 1) {
                                System.out.println("Fundación");
                            }
                            if (bajoEstrellaPrestado == 1) {
                                System.out.println("Bajo la misma estrella");
                            }
                            if (corredorLaberintoPrestado == 1) {
                                System.out.println("El corredor del laberinto");
                            }
                            if (divergentePrestado == 1) {
                                System.out.println("Divergente");
                            }
                            if (principitoPrestado == 1) {
                                System.out.println("El principito");
                            }
                            if (monstruosPrestado == 1) {
                                System.out.println("Donde viven los monstruos");
                            }
                            if (harryPrestado == 1) {
                                System.out.println("Harry Potter y la piedra filosofal");
                            }
                            break;
/*============================================================= Caso 3 Menu ===============================================================================================================================================================*/
/*============================================================= Caso 4 Menu ===============================================================================================================================================================*/
                        case 4:
                            System.out.println("Libros disponibles: \n1. Ciencia ficción\n2. Juveniles\n3. Infantiles");
                            opcionCategoria = sc.nextInt();
                            switch (opcionCategoria) {
                                case 1:
                                    System.out.println(
                                            "Ciencia ficción:\n1. Dune\n2. Neuromante\n3. Fundación");
                                    if (dunePrestado == 0) {
                                        System.out.println("Dune está disponible.");
                                    } else {
                                        System.out.println("Dune no está disponible.");
                                    }
                                    if (neuromantePrestado == 0) {
                                        System.out.println("Neuromante está disponible.");
                                    } else {
                                        System.out.println("Neuromante no está disponible.");
                                    }
                                    if (fundacionPrestado == 0) {
                                        System.out.println("Fundación está disponible.");
                                    } else {
                                        System.out.println("Fundación no está disponible.");
                                    }
                                    break;
                                case 2:
                                    System.out.println(
                                            "Juveniles:\n1. Bajo la misma estrella\n2. El corredor del laberinto\n3. Divergente");
                                    if (bajoEstrellaPrestado == 0) {
                                        System.out.println("Bajo la misma estrella está disponible.");
                                    } else {
                                        System.out.println("Bajo la misma estrella no está disponible.");
                                    }
                                    if (corredorLaberintoPrestado == 0) {
                                        System.out.println("El corredor del laberinto está disponible.");
                                    } else {
                                        System.out.println("El corredor del laberinto no está disponible.");
                                    }
                                    if (divergentePrestado == 0) {
                                        System.out.println("Divergente está disponible.");
                                    } else {
                                        System.out.println("Divergente no está disponible.");
                                    }
                                    break;
                                case 3:
                                    System.out.println(
                                            "Infantiles:\n1. El principito\n2. Donde viven los monstruos\n3. Harry Potter y la piedra filosofal");
                                    if (principitoPrestado == 0) {
                                        System.out.println("El principito está disponible.");
                                    } else {
                                        System.out.println("El principito no está disponible.");
                                    }
                                    if (monstruosPrestado == 0) {
                                        System.out.println("Donde viven los monstruos está disponible.");
                                    } else {
                                        System.out.println("Donde viven los monstruos no está disponible.");
                                    }
                                    if (harryPrestado == 0) {
                                        System.out.println("Harry Potter y la piedra filosofal está disponible.");
                                    } else {
                                        System.out.println("Harry Potter y la piedra filosofal no está disponible.");
                                    }
                                    break;
                                default:
                                        System.out.println("Categoría no válida.");
                                    break;
                                }
                            break;
/*============================================================= Caso 4 Menu ===============================================================================================================================================================*/
/*============================================================= Caso 5 Menu ===============================================================================================================================================================*/
                        case 5:
                            System.out.println("Saliendo del sistema. Gracias por usar la biblioteca virtual.");
                            break;
/*============================================================= Caso 5 Menu ===============================================================================================================================================================*/
                        default:
                            System.out.println(
                                    "Opción no válida. Por favor, seleccione una opción del menú.");
                            break;
                    }
                } while (opcionMenu != 5);
                System.out.println("Usted ha realizado " + cantidadLibros + " prestamos de libros.");
                break;
            } else {
                intentos++;
                System.out.println("Usuario o contraseña incorrectos. Intentos restantes: " + (3 - intentos));
            }

        }
        if (intentos == 3) {
            System.out.println("Ha superado el número máximo de intentos. Comuniquese con el administrador.");
        }
        sc.close();
    }
}