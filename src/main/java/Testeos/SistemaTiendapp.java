package Testeos;

import AppTienda.Carrito;
import AppTienda.Coordenada;
import AppTienda.MailEstructura;
import AppTienda.Pedido;
import AppTienda.Product;
import AppTienda.ProductosProveedor;
import AppTienda.SistemaGPS;
import AppTiendaComp.Comprador;
import AppTiendaComp.DataCharger;
import AppTiendaComp.Proveedor;
import MetodosdePago.MetodoPago;
import MetodosdePago.PagoPaypal;
import MetodosdePago.PagoTarjeta;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HP PAVILION
 */
public class SistemaTiendapp {

    public DataCharger sistema;
    public Scanner sc;

    public SistemaTiendapp() {
        this.sistema = new DataCharger();
        this.sc = new Scanner(System.in);
    }

    public void firstMenu() {
        String opcion = "";
        while (!opcion.equals("3")) {
            System.out.println("\n********* Bienvenido al menu principal *********");
            System.out.println("1. Registro");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir\n");
            System.out.print("Ingrese una opcion: ");
            opcion = sc.next();
            if (opcion.equals("1")) {
                this.registrarUsuario();
            }else if (opcion.equals("2")) {
                this.iniciarSesion();
            }else if (opcion.equals("3")) {
                System.out.print("Saliendo... ");
            } else {
                System.out.print("Ingrese algo valido");
            }
        }
    }

    public void registrarUsuario() {
        String op = "";
        System.out.println("\n----- Bienvenido al menu de registro -----");
        System.out.println("1. Cliente");
        System.out.println("2. Proveedor");
        System.out.println("3. Regresar");
        System.out.print("Ingrese una opcion de Registro : ");
        op = sc.next();
        if (op.equals("3")) {
            this.firstMenu();
        } else if (op.equalsIgnoreCase("1") || op.equalsIgnoreCase("2")) {
            System.out.println("\nIngrese sus datos");
            sc.nextLine();
            System.out.print("Username: ");
            String user = sc.nextLine();
            System.out.print("Password: ");
            String word = sc.nextLine();
            System.out.println("Nombre (1er nombre y apellido): ");
            String name = sc.nextLine();
            System.out.println("ID: ");
            String id = sc.nextLine();
            System.out.println("-----Ingrese su direccion-----");
            String resp = "";
            String direccion = null;
            Coordenada ubicacion = null;
            while (!(resp.equalsIgnoreCase("coordenada") || resp.equalsIgnoreCase("direccion"))) {
                System.out.println("¿Desea Ingresar Coordenada o una direccion?:");
                resp = sc.nextLine();
                if ("coordenada".equals(resp.toLowerCase())) {
                    System.out.print("Latitud: ");
                    double lat = sc.nextDouble();
                    System.out.print("Longitud: ");
                    double lon = sc.nextDouble();
                    ubicacion = new Coordenada(lat, lon);
                    direccion = SistemaGPS.consultarUbicacion(ubicacion);
                } else if ("direccion".equals(resp.toLowerCase())) {
                    System.out.print("Ingresa direccion separada por coma  (Lugar,ciudad) :");
                    direccion = sc.nextLine();
                    ubicacion = SistemaGPS.consultarUbicacion(direccion);
                } else {
                    System.out.println("Ingresa una direccion valida :) ");
                }
            }
            System.out.print("Correo: ");
            String correo = sc.next();
            if (op.equals("1")) {
                System.out.print("Ingrese su forma de pago preferida: ");
                String pago = sc.next();
                sistema.clientes.add(new Comprador(id, name, ubicacion, correo, pago, user, word));
                sistema.cambiaDireccion(new Comprador(id, name, ubicacion, correo, pago, user, word), direccion);
                
                for (Comprador clientes : sistema.clientes) {
                    System.out.print("cliente " + clientes);
                }
            } else if (op.equals("2")) {
                System.out.print("Numero de contacto: ");
                String telf = sc.next();
                sistema.proveedores.add(new Proveedor(user, word, id, name, ubicacion, telf, correo));
                for (Proveedor prov : sistema.proveedores) {
                    System.out.print("Proveedores" + prov);
                }
            } else {
                System.out.println("Intente nuevamente por favor");
            }
        }
    }

    public void iniciarSesion() {
        String option= "";
        while(!option.equals("3")){
            System.out.println("===== Escoger =====");
            System.out.println("1. Iniciar Sesion como Proveedor");
            System.out.println("2. Inicar Sesion como Comprador");
            System.out.println("3. Regresar");
            System.out.print("\nIngresa una opcion: ");
            option = sc.next();
            if (option.equals("3")) {
                System.out.println("Regresando al menu principal....");
            }else if (option.equals("1") || option.equals("2")) {
                System.out.println("Iniciar Sesion");
                System.out.println("Usuario: ");
                String user = sc.next();
                System.out.println("Contraseña: ");
                String contra = sc.next();
                if (option.equals("1")) {
                    boolean coincido = sistema.checkListPro(user, contra);
                    if (coincido) {
                        System.out.println("Inicio Sesion Corectamente\n");
                        this.secondMenuProveedor(sistema.buscarProveedor(user, contra));
                    } else {
                        System.out.println("Uno de los datos ingresados no es correcto");
                        this.iniciarSesion();
                    }
                } else if (option.equals("2")) {
                    boolean coincido = sistema.checkListCli(user, contra);
                    if (coincido) {
                        System.out.println("Inicio Sesion Corectamente");
                        this.secondMenuComprador(sistema.buscarComprador(user, contra));
                    } else {
                        System.out.println("Uno de los datos ingresados no es correcto o usted aun no esta registrado");
                        this.iniciarSesion();
                    }
                }
            }else{
                System.out.println("Ingresa una opcion valida");
            }
        }
    }

    public void opcionesProducto(Comprador c){
        System.out.println("-----Bienvenido a Tiendapp-----");
        System.out.println("¿Desea consultar sus productos? (S/N) ");
        String resp = sc.next();
        if (resp.equalsIgnoreCase("S")) {
            System.out.println("¿Desea filtrar sus productos? (S/N) ");
            String ans = sc.next();
            if (ans.equalsIgnoreCase("S")) {
                System.out.println("Escriba la opcion del tipo de filtro a usar: ");
                System.out.println("1. Categoria");
                System.out.println("2. Nombre");
                System.out.println("3. Precio");
                System.out.println("4. Nombre y Categoria");
                System.out.println("5. Categoria y Precio");
                System.out.println("6. Nombre y Precio");
                String filtra = sc.next();
                String filtro = null;
                if (filtra.equals("1")) {
                    System.out.println("Ingrese una Categoria para filtrar: ");
                    filtro = sc.next();
                } else if (filtra.equals("2")) {
                    System.out.println("Ingrese un Nombre para filtrar: ");
                    filtro = sc.next();
                } else if (filtra.equals("3")) {
                    System.out.println("Ingrese un rango de Precios para filtrar (ejemplo: 'precio1,precio2'): ");
                    filtro = sc.next();
                } else if (filtra.equals("4")) {
                    System.out.println("Ingrese un Nombre y una Categoria separados por coma para filtrar (ejemplo: 'nombre,categoria'): ");
                    filtro = sc.next();
                } else if (filtra.equals("5")) {
                    System.out.println("Ingrese una Categoria y un rango de Precios separados por coma para filtrar (ejemplo: 'categoria,precio1,precio2'): ");
                    filtro = sc.next();
                } else if (filtra.equals("6")) {
                    System.out.println("Ingrese un Nombre y un rango de Precios separados por coma para filtrar (ejemplo: 'nombre,precio1,precio2'): ");
                    filtro = sc.next();
                }else{
                    System.out.println("Ingrese una de las opciones ");
                }
                ArrayList<Product> prodClie = sistema.ubiCincuenta(c.getUbicacion(), filtra, filtro);
                sistema.pro.mostrarFiltrados(prodClie);
                System.out.println("¿Desea consultar uno en especifico o realizar otra busqueda?");
                System.out.println("opcion 1");
                System.out.println("opcion 2");
                System.out.println("opcion 3: Salir");
                String opc = sc.next();
                if (opc.equalsIgnoreCase("1")) {
                    System.out.println("Ingrese el codigo del producto.");
                    String code = sc.next();
                    System.out.println(sistema.ubicarProducto(code));
                    System.out.println("¿Desea agregar al carrito? (S/N)");
                    String siono = sc.next();
                    if (siono.equalsIgnoreCase("S")) {
                        ArrayList<Product> vendidos = new ArrayList<>();
                        vendidos.add(sistema.ubicarProducto(code));
                        sistema.carros.add(new Carrito());
                        sistema.carros.get(0).agregarProducto(vendidos);
                        System.out.print("Ingresa la cantidad de productos para agregar: ");
                        String cant = sc.next();
                        double canti = Double.parseDouble(cant);
                        while (canti > 0) {
                            canti -= 1;
                            sistema.pro.mostrarFiltrados(prodClie);
                            System.out.println("Ingrese el codigo del producto.");
                            String codigo = sc.next();
                            vendidos.add(sistema.ubicarProducto(codigo));
                            sistema.carros.get(0).agregarProducto(vendidos);
                        }
                        this.secondMenuComprador(c);
                    } else {
                        this.secondMenuComprador(c);
                    }
                }else if (opc.equals("2")){
                    this.opcionesProducto(c);
                }else {
                    System.out.println("Ingrese una respuesta valida -_-");
                    this.secondMenuComprador(c);
                }
            } else {
                ArrayList<Product> todos = sistema.pro.getProductosProov();
                sistema.pro.mostrarFiltrados(todos);
                System.out.println("¿Desea consultar uno en especifico o realizar otra busqueda?");
                System.out.println("opcion 1");
                System.out.println("opcion 2");
                System.out.println("opcion 3: Salir");
                String opc = sc.next();
                if (opc.equals("1")) {
                    System.out.println("Ingrese el codigo del producto.");
                    String code = sc.next();
                    System.out.println(sistema.ubicarProducto(code));
                    System.out.println("¿Desea agregar al carrito? (S/N)");
                    String siono = sc.next();
                    if (siono.equalsIgnoreCase("S")) {
                        ArrayList<Product> vendidos = new ArrayList<>();
                        vendidos.add(sistema.ubicarProducto(code));
                        sistema.carros.add(new Carrito());
                        sistema.carros.get(0).agregarProducto(vendidos);
                        System.out.print("Ingresa la cantidad de productos para agregar");
                        String cant = sc.next();
                        double canti = Double.parseDouble(cant);
                        while (canti > 0) {
                            canti -= 1;
                            sistema.pro.mostrarFiltrados(todos);
                            System.out.println("Ingrese el codigo del producto: ");
                            String codigo = sc.next();
                            vendidos.add(sistema.ubicarProducto(codigo));
                            sistema.carros.get(0).agregarProducto(vendidos);
                        }
                        this.secondMenuComprador(c);
                    } else {
                        this.secondMenuComprador(c);
                    }
                } else if (opc.equals("2")) {
                    this.opcionesProducto(c);
                } else {
                    this.secondMenuComprador(c);
                }
            }
        } else {
            this.secondMenuComprador(c);
        }
    }

    public void consulCarrito(Comprador c) {
        System.out.println("Estos son los productos que tiene actualmente en su carrito");
        System.out.println("-------------------------------------------------------------");
        sistema.carros.get(0).consultarCarrito();
        System.out.println("\n¿Que desea hacer?");
        System.out.println("1. Quitar Producto");
        System.out.println("2. Proceder a Compra");
        System.out.print("Ingrese una opcion: ");
        String var = sc.next();
        if (var.equals("1")) {
            System.out.print("Ingrese el codigo del producto a elimiar: ");
            String cod = sc.next();
            sistema.carros.get(0).quitarProducto(cod);
            System.out.println("Su producto se ha eliminado con exito");
            System.out.println("Carrito actual");
            sistema.carros.get(0).consultarCarrito();
            System.out.println("-------------------------------------------------------------");
            this.consulCarrito(c);
        } else {
            System.out.println("¿Como desea realizar el Pago?");
            System.out.println("1. Tarjeta de Credito");
            System.out.println("2. PayPal");
            System.out.print("Ingrese una opcion: ");
            String op1 = sc.next();
            if (op1.equals("1")) {
                ArrayList<String> caractTarjetas = new ArrayList<>();
                System.out.print("Ingrese el tipo de tarjeta (visa,diners,mastercard): ");
                String tipoTar = sc.next();
                System.out.print("Ingrese el numero de la tarjeta: ");
                String num = sc.next();
                System.out.print("Ingrese el nombre del titular de la tarjeta");
                String titular = sc.nextLine();
                MetodoPago pagoTar = new PagoTarjeta();
                int pagoEnt = (int) pagoTar.procesarPago();
                String mensaje = String.valueOf(pagoEnt);
                MailEstructura mail = new MailEstructura("nelsona449@gmail.com", "nelsongabriel", c.getCorreo(), "Codigo de verificacion", mensaje);
                mail.enviarCorreo();
                System.out.println("Hemos enviado un codigo de verificacion a su correo " + c.getCorreo());
                System.out.print("Ingrese el codigo de verificacion: ");
                String verificar = sc.nextLine();
                if (mensaje.equals(verificar)) {
                    caractTarjetas.add(tipoTar);
                    caractTarjetas.add(num);
                    caractTarjetas.add(titular);
                    sistema.agregarPedidos(sistema.carros.get(0).generarPedidos(c));
                    sistema.carros.get(0).generarCorreo(c);
                    System.out.println("Su compra se ha generado exitosamente.");
                    System.out.println("");
                    sistema.carros.clear();
                    this.secondMenuComprador(c);
                } else {
                    System.out.println("Codigo de verificacion invalido");
                }

            } else {
                System.out.print("Ingrese su usuario de PayPal: ");
                String usuario = sc.next();
                System.out.println("Ingrese su contraseña: ");
                String contrasenia = sc.next();
                MetodoPago metodoPago = new PagoPaypal(usuario, contrasenia);
                double pago = metodoPago.procesarPago();
                double totPago = sistema.carros.get(0).calcularTotal();
                if (pago >= totPago) {
                    sistema.agregarPedidos(sistema.carros.get(0).generarPedidos(c));
                    sistema.carros.get(0).generarCorreo(c);
                    System.out.println("Su compra se ha generado exitosamente.");
                    System.out.println("");
                    sistema.carros.clear();
                    this.secondMenuComprador(c);
                }

            }
        }
    }

    public void consultPedido(Comprador c) {
        ArrayList<Pedido> peds = sistema.pedidos;
        System.out.println("Estos son sus pedidos actualmente");
        for (Pedido p : peds) {
            p.ConsultarInfoPedido();
        }
        this.secondMenuComprador(c);
    }

    public void secondMenuComprador(Comprador c) {
        String option = "";
        while (!option.equals("4")) {
            System.out.println("\n=======================================");
            System.out.println("Bienvenido al menu de comprador");
            System.out.println("=======================================");
            System.out.println("1. Consulta y venta de productos");
            System.out.println("2. Ir al Carrito");
            System.out.println("3. Consultar Carrito");
            System.out.println("4. Salir");
            System.out.print("\nIngresa una opcion: ");
            option = sc.next();
            if (option.equals("1")) {
                this.opcionesProducto(c);
            } else if (option.equals("2")) {
                this.consulCarrito(c);
            } else if (option.equals("3")) {
                this.consultPedido(c);
            } else if (option.equals("4")) {
                System.out.println("Gracias por visitarnos :)");
            } else {
                System.out.println("Ingrese algo valido.");
            }
        }
    }

    public void secondMenuProveedor(String idProveedor) {
        String opti = "";
        while (!opti.equals("5")) {
            System.out.println("\n=======================================");
            System.out.println("Bienvenido al menu de proveedor");
            System.out.println("=======================================");
            System.out.println("1. Registro de Productos");
            System.out.println("2. Consultar informacion de pedido");
            System.out.println("3. Gestionar estado de pedido");
            System.out.println("4. Consulta y edicion de productos");
            System.out.println("5. Salir");
            System.out.print("\nIngresa una opcion: ");
            opti = sc.next();
            if (opti.equals("1")) {
                this.registerProduct(idProveedor);
            } else if (opti.equals("2")) {
                this.consulPedido(idProveedor);
            } else if (opti.equals("3")) {
                this.gestionPedido(idProveedor);
            } else if (opti.equals("4")) {
                this.consultoeditoProd(idProveedor);
            }else if(opti.equals("5")){
                System.out.println("Saliendo del menu del proveedor ....\n");
            }
        }
    }

    public void registerProduct(String id) {
        System.out.println("** Registre su producto **");
        sc.nextLine();
        System.out.print("Ingrese el id del producto: ");
        String code = sc.nextLine();
        System.out.print("Ingrese el nombre: ");
        String name = sc.nextLine();
        System.out.print("Descripcion del producto: ");
        String descri = sc.nextLine();
        System.out.print("Categoria(s): ");
        String categorias = sc.next();
        boolean dividono = Product.dividoCategoria(categorias);
        ArrayList<String> cateLista = sistema.formaCat(categorias, dividono);
        System.out.println("Precio: ");
        double precio = sc.nextDouble();
        sistema.pro.getProductosProov().add(new Product(code, name, cateLista,precio, descri, id));
    }

    public void consultoeditoProd(String id) {
        System.out.println("¿Desea consultar o editar sus productos?");
        System.out.println("1. Consultar");
        System.out.println("2. Editar");
        System.out.println("3. Regresar");
        String decision = sc.next();
        if (decision.equals("1") || decision.equals("2")) {
            System.out.println("¿Desea filtrar?(S/N) ");
            String sino = sc.next();
            if (sino.equalsIgnoreCase("S")) {
                System.out.println("Seleccione los filtros: ");
                System.out.println("1. Categoria");
                System.out.println("2. Nombre");
                System.out.println("3. Ambos");
                System.out.print("\nIngrese su opcion: ");
                String filtra = sc.next();
                if (filtra.equals("1")) {
                    System.out.println("Ingresa el filtro: ");
                    String cate = sc.next();
                    ArrayList<Product> filtrados = sistema.pro.buscarPorCat(cate, id);
                    ProductosProveedor.revisoFiltro(filtrados);
                    sistema.pro.mostrarFiltrados(filtrados);
                    
                } else if (filtra.equalsIgnoreCase("2")) {
                    System.out.println("Ingresa el filtro: ");
                    String name = sc.next();
                    ArrayList<Product> filtrados = sistema.pro.buscarPorNom(name, id);
                    ProductosProveedor.revisoFiltro(filtrados);
                    sistema.pro.mostrarFiltrados(filtrados);

                } else if (filtra.equals("3")) {
                    System.out.println("Ingresa el primer filtro: ");
                    String name = sc.next();
                    System.out.println("Ingresa el segundo filtro: ");
                    String cate = sc.next();
                    ArrayList<Product> filtrados = sistema.pro.buscarProducto(name, cate, id);
                    ProductosProveedor.revisoFiltro(filtrados);
                    sistema.pro.mostrarFiltrados(filtrados);
                }
                
                if (decision.equals("2")) {
                    sistema.seteoTodo();
                }
            } else if (sino.equalsIgnoreCase("N")) {
                ArrayList<Product> todos = new ArrayList<>();
                todos = sistema.pro.buscarPro(id);
                sistema.pro.mostrarFiltrados(todos);
                System.out.print(todos);
                if (decision.equals("2")) {
                    sistema.seteoTodo();
                }
            }
        }else if (decision.equals("3")) {
            System.out.println("Regresando al menu proveedor...");
        }
    }

    public void consulPedido(String id) {
        boolean pedProv = sistema.checkPed(id);
        ArrayList<Pedido> pedPro = sistema.pedidosProveedor(id);
        if (pedProv) {
            for (Pedido p : pedPro) {
                p.ConsultarInfoPedido();
                System.out.println("¿Desea Regresar?");
                String repo = sc.next();
                while (!repo.equalsIgnoreCase("S")) {
                    System.out.println("¿Desea Regresar?");
                    repo = sc.next();
                }
            }
        } else {
            System.out.print("No tienes pedidos\n");
        }
    }

    public void gestionPedido(String id) {
        System.out.println("\nBienvenido a la gestion de tus pedidos");
        boolean pedProv = sistema.checkPed(id);
        ArrayList<Pedido> pedPro = sistema.pedidosProveedor(id);
        if (pedProv) {
            for (Pedido p : pedPro) {
                p.ConsultarInfoPedido();
            }
            System.out.println("¿Desea editar el estado de algun pedido? (S/N)");
            String respo = sc.next();
            if (respo.equals("S")) {
                System.out.print("Ingrese el codigo del pedido a editar: ");
                String code = sc.next();
                System.out.println("¿A que estado desea cambiarlo?");
                String state = sc.next();
                sistema.cambiarEstado(code, state);
                this.secondMenuProveedor(id);
            } else if (respo.equals("N")) {
                System.out.println("Saliendo del gestionamiento de pedidos\n");
            }
        }else{
            System.out.println("Lo sentimos,No hay pedidos que podamos gestionar");
        }
    }

    public static void main(String[] args) {
        SistemaTiendapp s = new SistemaTiendapp();
        s.firstMenu();

    }

}
