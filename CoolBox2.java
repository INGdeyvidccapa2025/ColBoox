import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

            public class CoolBox2 {
                static Scanner C=new Scanner(System.in);
                static ArrayList<String> productos = new ArrayList<>();
                static ArrayList<Double> precios = new ArrayList<>();
                static final String admin_user ="pepe@mail.com";
                static final String admin_pass ="A564123*r";
                static String emailGuardado;
                static String passwordGuardado;
                public static void main (String[]args){
                    while(true){
                        System.out.println("Bienvenido al sistema CoolBox");
                        System.out.println("1. Administrador");
                        System.out.println("2. Vendedor");
                        System.out.println("3. Cliente");
                        System.out.println("4. Salir");
                        int user=C.nextInt();
                        C.nextLine();
                        if (user ==1){
                            if(login(admin_user,admin_pass)){
                                menuadministrador();
                            }
                        }else if(user ==2){
                            menuVendedor();
                        }else if(user ==3){
                            if (registrarCuenta());{
                                menuPrincipal();
                            }
                        }else if (user ==4){
                            if (registrarCuenta());{
                                menuPrincipal();
                            }
                            System.out.println("saliendo del sistema");
                            System.exit(0);
                        }
                        else{
                            System.out.println("Opción no válida");
                        }
                    }
                }
                private void crear() {
                    registrarCuenta();
                }

                public static boolean login(String usuarioCorrecto, String passCorrecto) {
                    System.out.print("Usuario: ");
                    String usuario = C.nextLine();
                    System.out.print("Contraseña: ");
                    String contraseña = C.nextLine();
                    if (usuario.equals(usuarioCorrecto) && contraseña.equals(passCorrecto)) {
                        System.out.println("Ingreso exitoso");
                        return true;
                    } else {
                        System.out.println("Usuario o contraseña incorrectos");
                        return false;
                    }
                }
                public static void menuadministrador() {
                    int opcion;
                    do {
                        System.out.println("---- Menú Administrador ----");
                        System.out.println("1. Agregar producto");
                        System.out.println("2. Modificar producto");
                        System.out.println("3. Eliminar producto");
                        System.out.println("4. Ordenar productos por marca");
                        System.out.println("5. Ordenar por Precio");
                        System.out.println("6. Mostrar productos");
                        System.out.println("7. Buscar productos");
                        System.out.println("8. Registrar nuevo Vendedor");
                        System.out.println("9. Salir");
                        opcion = C.nextInt();
                        C.nextLine();
                        switch(opcion){
                            case 1:
                                agregarProducto();
                                break;
                            case 2:
                                modificarProducto();
                                break;
                            case 3:
                                eliminarProducto();
                                break;
                            case 4:
                                ordenarProductos();
                                break;
                            case 5:
                                ordenarProductosPorPrecio();
                                break;
                            case 6:
                                mostrarProductos();
                                break;
                            case 7:
                                buscarProducto(productos,precios);
                                break;
                            case 8:
                                registrarVendedor();
                                break;
                            case 9:
                                System.out.println("Saliendo al menú principal");
                                break;
                            default:
                                System.out.println("Opción no válida");
                        }
                    } while (opcion != 9);
                }
                public static void agregarProducto() {
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = C.nextLine();
                    System.out.print("Ingrese el precio: ");
                    double precio = C.nextDouble();
                    C.nextLine();
                    productos.add(nombre);
                    precios.add(precio);
                    System.out.println("Producto agregado con exito");
                }
                public static void modificarProducto() {
                    System.out.print("Ingrese el nombre del producto a modificar: ");
                    String nombre = C.nextLine();
                    int indice = productos.indexOf(nombre);
                    if (indice != -1) {
                        System.out.print("Nuevo nombre del producto: ");
                        String nuevoNombre = C.nextLine();
                        System.out.print("Nuevo precio: ");
                        double nuevoPrecio = C.nextDouble();
                        C.nextLine();
                        productos.set(indice, nuevoNombre);
                        precios.set(indice, nuevoPrecio);
                        System.out.println("Modificacion de producto Exitosa");
                    } else {
                        System.out.println("No se encontro ese producto");
                    }
                }
                public static void eliminarProducto() {
                    System.out.print("Ingrese el nombre del producto a eliminar: ");
                    String nombre = C.nextLine();
                    int indice = productos.indexOf(nombre);
                    if (indice != -1) {
                        productos.remove(indice);
                        precios.remove(indice);
                        System.out.println("Producto eliminado con exito");
                    } else {
                        System.out.println("No se encontro ese producto");
                    }
                }
                public static void ordenarProductos() {
                    for (int i = 0; i < productos.size() - 1; i++) {
                        for (int j = 0; j < productos.size() - i - 1; j++) {
                            if (productos.get(j).compareToIgnoreCase(productos.get(j + 1)) > 0) {
                                String Producto = productos.get(j);
                                productos.set(j, productos.get(j + 1));
                                productos.set(j + 1, Producto);
                                double Precio = precios.get(j);
                                precios.set(j, precios.get(j + 1));
                                precios.set(j + 1, Precio);
                            }
                        }
                    }
                    System.out.println("---Productos ordenados---");
                    mostrarProductos();
                }
                public static void ordenarProductosPorPrecio() {
                    for (int i = 0; i < precios.size() - 1; i++) {
                        for (int j = 0; j < precios.size() - i - 1; j++) {
                            if (precios.get(j) > precios.get(j + 1)) {
                                double Precio = precios.get(j);
                                precios.set(j, precios.get(j + 1));
                                precios.set(j + 1, Precio);
                                String Producto = productos.get(j);
                                productos.set(j, productos.get(j + 1));
                                productos.set(j + 1, Producto);
                            }
                        }
                    }

                    System.out.println("---Productos ordenados por precio (menor a mayor)---");
                    mostrarProductos();
                }
                public static void mostrarProductos() {
                    if (productos.isEmpty()) {
                        System.out.println("Tienda vacia por el momento");
                    } else {
                        System.out.println("--- Lista de productos ---");
                        for (int i = 0; i < productos.size(); i++) {
                            System.out.println(productos.get(i) + " - S/ " + precios.get(i));
                        }
                    }
                }
                public static void buscarProducto(ArrayList<String> productos , ArrayList<Double> precios){
                    System.out.println("ingrese el nombre del producto");
                    String nombre = C.nextLine();
                    int indice =productos.indexOf(nombre);
                    if(indice!=-1){
                        System.out.println(": "+nombre+" :" +precios.get(indice));
                    }
                    else{
                        System.out.println("no se crea habil");
                    }
                }
                public static void registrarVendedor(){

                }
                public static void menuVendedor(){
                    int opcion;
                    do{
                        System.out.println("Bienvenido al menu de ventas");
                        System.out.println("1. Vender produtos");
                        System.out.println("2. ");
                        opcion =C.nextInt();
                        C.nextLine();
                        switch (opcion){

                        }
                    }while(opcion!=0);
                }
                private static boolean validarCorreo(String email) {
                    String regex = "^[\\w.-]+@[\\w.-]+\\.com$";
                    return Pattern.compile(regex).matcher(email).matches();
                }
                private static boolean validarPassword(String password) {
                    String regex =
                            "^(?=.[A-Za-z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%*?&]{8,}$";
                    return Pattern.compile(regex).matcher(password).matches();
                }
                public static boolean registrarCuenta() {
                    System.out.println("\n---- REGISTRO DE CUENTA ----");
                    String email, password;

                    do {
                        System.out.print("Ingrese su correo electrónico: ");
                        email = C.nextLine();
                        if (!validarCorreo(email)) {
                            System.out.println(
                                    "❌ Correo inválido. Debe contener '@' y terminar en '.com'."
                            );
                        }
                    } while (!validarCorreo(email));

                    do {
                        System.out.print(
                                "Ingrese su contraseña (mínimo 8 caracteres, debe incluir letras, números y caracteres especiales): "
                        );
                        password = C.nextLine();
                        if (!validarPassword(password)) {
                            System.out.println(
                                    "❌ Contraseña inválida. Debe incluir letras, números y caracteres especiales."
                            );
                        }
                    } while (!validarPassword(password));

                    emailGuardado = email;
                    passwordGuardado = password;
                    System.out.println("✅ Registro exitoso. Ahora puede iniciar sesión.");
                    return false;
                }
                public static boolean iniciarSesion() {
                    System.out.println("\n---- INICIO DE SESIÓN ----");
                    int intentos = 3;
                    while (intentos > 0) {
                        System.out.print("Ingrese su correo: ");
                        String emailIngresado = C.nextLine();
                        System.out.print("Ingrese su contraseña: ");
                        String passwordIngresado = C.nextLine();

                        if (
                                emailIngresado.equals(emailGuardado) &&
                                        passwordIngresado.equals(passwordGuardado)
                        ) {
                            System.out.println("✅ Inicio de sesión exitoso. Bienvenido!");
                            return true;
                        } else {
                            intentos--;
                            System.out.println(
                                    "❌ Correo o contraseña incorrectos. Intentos restantes: " + intentos
                            );
                        }
                    }
                    System.out.println("⛔ Se agotaron los intentos de inicio de sesión.");
                    return false;
                }
                public static void menuPrincipal(){
                    int opcion;
                    do {
                        System.out.println("---- Menú Principal----");
                        System.out.println("1. Mostrar productos");
                        System.out.println("2. comprar producto");
                        System.out.println("3. Buscar producto");
                        System.out.println("4. Exportrar productos");
                        System.out.println("5. Salir");
                        opcion = C.nextInt();
                        C.nextLine();
                        switch(opcion){
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                System.out.println("Saliendo al menú principal");
                                break;
                            default:
                                System.out.println("Opción no válida");
                        }
                    }while (opcion != 5);
                }
            }