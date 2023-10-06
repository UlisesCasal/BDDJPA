package org.example;
import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {
        private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BDJPA");
        private static EntityManager em = emf.createEntityManager();
        private static final EntityTransaction emt = em.getTransaction();
        private static Scanner sc = new Scanner(System.in);
        private static void clearConsola() {
            for (int i = 0; i < 30; i++) {
                System.out.println();
            }
        }
        public static void menu(){
            boolean salir = false;
            while (!salir) {
                clearconsole();
                System.out.println("""
                        -----------[Menu Principal]--------------
                        
                        Ingrese una opcion:
                        1- Alta de Tuplas.
                        2- Modificar Tuplas.
                        3- Deletear Tuplas.
                        4- Listar Tuplas.
                        
                        Cualquier otra tecla para salir.
                        
                        Ingrese una opcion: """);
                String input = sc.nextLine().trim();
                switch (input) {
                    case "1" -> altaTupla();
                    case "2" -> modificarTupla();
                    case "3" -> deleteTupla();
                    case "4" -> mostrarTupla();
                    default -> {
                        clearConsola();
                        System.out.println("Nos vemos!");
                        salir = true;}
                }
            }
            sc.close();
        }
    private static void deleteTupla() {
        clearconsole();
        String smsg = "persist()";
        System.out.println("""
                Por favor Ingrese la tabla a la cual quiere realizar una baja:
                1. Cliente.
                2. Factura.
                3. Volver.
                """);
        String inp = sc.nextLine().trim();
        switch (inp) {
            case "1" -> deleteCliente();
            case "2" -> deleteFactura();
            case "3" -> {}
            default -> deleteTupla();
        }
        if (inp.equals("1") || (inp.equals("2"))) {
            try {
                System.out.println("Main:em.persist(c) hecho");
                smsg = "commit()";
                emt.commit();
                System.out.println("Main:emt.commit() hecho");
            } catch (IllegalArgumentException iae) {
                System.out.println("Main:Error en " + smsg + " persistiendo, posiblemente sea null");
            } catch (EntityExistsException eee) {
                System.out.println("Main:Error en " + smsg + " persistiendo, esta entidad ya existe");
            } catch (TransactionRequiredException tre) {
                System.out.println("Main:Error en " + smsg + " persistiendo, se requiere de una transaccion");
            } catch (Exception e) {
                System.out.println("Main:Error en " + smsg + " persistiendo, error: " + e.getMessage());
            }
        }
        System.out.println("Presione ENTER para continuar...");
        sc.nextLine();
        }
    private static void deleteCliente() {
        clearconsole();
        em.getTransaction().begin();
        System.out.println("Ingrese el codigo: ");
        String inp = sc.nextLine().trim();
        // Busca la entidad que deseas modificar
        Cliente c = em.find(Cliente.class, Integer.parseInt(inp));
        if (c == null) {
            System.out.println("No existe dicho cliente");
        }
        else {
            em.remove(c);
            System.out.println("Cliente borrado");
        }
    }
    private static void deleteFactura() {
        clearconsole();
        em.getTransaction().begin();
        System.out.println("Ingrese el numero: ");
        String inp = sc.nextLine().trim();
        // Busca la entidad que deseas modificar
        Factura f = em.find(Factura.class, Integer.parseInt(inp));
        if (f == null) {
            System.out.println("No existe dicha factura");
        }
        else {
            em.remove(f);
            System.out.println("Factura borrada");
        }
    }
    private static void modificarTupla() {
        clearconsole();
        String smsg = "persist()";
        System.out.println("""
                Por favor Ingrese la tabla a la cual quiere realizar una modificacion:
                1. Cliente.
                2. Factura.
                3. Volver.
                Ingrese su opcion:""");
        String inp = sc.nextLine().trim();
        switch (inp) {
            case "1" -> modificarCliente();
            case "2" -> modificarFactura();
            case "3" -> {}
            default -> modificarTupla();}
        if (inp.equals("1") || (inp.equals("2"))) {
            try {
                System.out.println("Main:em.persist(c) hecho");
                smsg = "commit()";
                emt.commit();
                System.out.println("Main:emt.commit() hecho");
            } catch (IllegalArgumentException iae) {
                System.out.println("Main:Error en " + smsg + " persistiendo, posiblemente sea null");
            } catch (EntityExistsException eee) {
                System.out.println("Main:Error en " + smsg + " persistiendo, esta entidad ya existe");
            } catch (TransactionRequiredException tre) {
                System.out.println("Main:Error en " + smsg + " persistiendo, se requiere de una transaccion");
            } catch (Exception e) {
                System.out.println("Main:Error en " + smsg + " persistiendo, error: " + e.getMessage());
            }
        }
        System.out.println("Presione ENTER para continuar...");
        sc.nextLine();
    }
    private static void modificarCliente() {
        clearconsole();
        em.getTransaction().begin();
        System.out.println("Ingrese el codigo: ");
        String inp = sc.nextLine().trim();
        // Busca el cliente que se desea modificar
        Cliente c = em.find(Cliente.class, Integer.parseInt(inp));
        if (c == null) {
            System.out.println("No existe dicho cliente");
            return;
        }
        // Modifica los atributos del cliente
        System.out.println("Ingrese el nombre (o deje en blanco para no cambiarlo): ");
        inp = sc.nextLine().trim();
        if (!inp.equals("")) c.setNombre(inp);
        System.out.println("Ingrese la direccion (o deje en blanco para no cambiarlo): ");
        inp = sc.nextLine().trim();
        if (!inp.equals("")) c.setDirec(inp);
        System.out.println("Ingrese el codigo postal (o deje en blanco para no cambiarlo): ");
        inp = sc.nextLine().trim();
        if (!inp.equals("")) c.setCodigo(Integer.parseInt(inp));
        System.out.println("Ingrese el telefono (o deje en blanco para no cambiarlo): ");
        inp = sc.nextLine().trim();
        if (!inp.equals("")) c.setTel(inp);

        Cliente c1 = em.find(Cliente.class, c.getCodigo());
        clearconsole();
        if (c1 == null) {
            System.out.println("Ha habido un error con el cliente :(");}
        else {
            System.out.println("Modificacion exitosa!");
            printCliente(c1);
        }
    }
    private static void modificarFactura() {
        clearconsole();
        em.getTransaction().begin();
        System.out.println("Ingrese el numero: ");
        String inp = sc.nextLine().trim();
        // Busca la entidad que deseas modificar
        Factura f = em.find(Factura.class, Integer.parseInt(inp));
        if (f == null) {
            System.out.println("No existe dicha factura");
            return;
        }
        System.out.println("Ingrese el id (o deje en blanco para no cambiarlo): ");
        if (!inp.equals("")) inp = sc.nextLine().trim();
        f.setCodigo(Integer.parseInt(inp));
        System.out.println("Ingrese la importe (o deje en blanco para no cambiarlo): ");
        if (!inp.equals("")) inp = sc.nextLine().trim();
        f.setImporte(Double.parseDouble(inp));

        Factura f1 = em.find(Factura.class, f.getNumero());
        if (f1 == null) {
            System.out.println("No se a podido dar de alta la factura :(");}
        else {
            System.out.println("Alta exitosa!");
            printFactura(f1);
        }
    }
    private static void altaTupla() {
        clearconsole();
        String smsg = "persist()";
        System.out.println("""
             Por favor Ingrese la tabla a la cual quiere realizar un alta:
             1. Cliente.
             2. Factura.
             3. Volver.
             Ingrese su opcion:""");
        String inp = sc.nextLine().trim();
        switch (inp) {
            case "1" -> altaCliente();
            case "2" -> altaFactura();
            case "3" -> {}
            default -> altaTupla();}
        if (inp.equals("1") || (inp.equals("2"))) {
            try {
                System.out.println("Main:em.persist(c) hecho");
                smsg = "commit()";
                emt.commit();
                System.out.println("Main:emt.commit() hecho");
            } catch (IllegalArgumentException iae) {
                System.out.println("Main:Error en " + smsg + " persistiendo, posiblemente sea null");
            } catch (EntityExistsException eee) {
                System.out.println("Main:Error en " + smsg + " persistiendo, esta entidad ya existe");
            } catch (TransactionRequiredException tre) {
                System.out.println("Main:Error en " + smsg + " persistiendo, se requiere de una transaccion");
            } catch (Exception e) {
                System.out.println("Main:Error en " + smsg + " persistiendo, error: " + e.getMessage());
            }
        }
        System.out.println("Presione ENTER para continuar...");
        sc.nextLine();
    }
    private static void altaCliente() {
        clearconsole();
        Cliente c = new Cliente();
        System.out.println("Ingrese el codigo: ");
        String inp = sc.nextLine().trim();
        c.setCodigo(Integer.parseInt(inp));
        System.out.println("Ingrese el nombre: ");
        inp = sc.nextLine().trim();
        c.setNombre(inp);
        System.out.println("Ingrese la direccion: ");
        inp = sc.nextLine().trim();
        c.setDirec(inp);
        System.out.println("Ingrese el codigo postal: ");
        inp = sc.nextLine().trim();
        c.setPostal(Integer.parseInt(inp));
        System.out.println("Ingrese el telefono: ");
        inp = sc.nextLine().trim();
        c.setTel(inp);
        emt.begin();
        em.persist(c);

        Cliente c1 = em.find(Cliente.class, c.getCodigo());
        clearconsole();
        if (c1 == null) {
            System.out.println("No se a podido dar de alta al cliente :(");}
        else {
            System.out.println("Alta exitosa!");
            printCliente(c1);}
    }
    private static void altaFactura() {
        clearconsole();
        Factura f = new Factura();
        System.out.println("Ingrese el numero: ");
        String inp = sc.nextLine().trim();
        f.setNumero(Integer.parseInt(inp));
        System.out.println("Ingrese el id: ");
        inp = sc.nextLine().trim();
        f.setCodigo(Integer.parseInt(inp));
        System.out.println("Ingrese la importe: ");
        inp = sc.nextLine().trim();
        f.setImporte(Double.parseDouble(inp));
        emt.begin();
        em.persist(f);

        Factura f1 = em.find(Factura.class, f.getNumero());
        if (f1 == null) {
            System.out.println("No se a podido dar de alta la factura :(");}
        else {
            System.out.println("Alta exitosa!");
            printFactura(f1);}
    }
    private static void printCliente(Cliente cliente) {
        System.out.println(
                "Codigo: " + cliente.getCodigo() + "\r\n" +
                "Nombre: " + cliente.getNombre() + "\r\n" +
                "Direccion: " + cliente.getDirec() + "\r\n" +
                "Cod. postal: " + cliente.getPostal() + "\r\n" +
                "Telefono: " + cliente.getTel());
        System.out.println("-------------------------------------------------------");
    }
    private static void printFactura(Factura factura) {
        System.out.println(
                "Numero: " + factura.getNumero() + "\r\n" +
                "Cod. cliente: " + factura.getCodigo() + "\r\n" +
                "Importe: " + factura.getImporte());
        System.out.println("-------------------------------------------------------");
    }
    public static void mostrarTupla() {
        clearconsole();
        System.out.println("""
                Por favor Ingrese la tabla a la cual quiere listar:
                1. Cliente.
                2. Factura
                3. Volver
                Ingrese su opcion: """);
        String opcion = sc.nextLine();
        switch (opcion){
            case "1":
                mostrarClientes();
                break;
            case "2":
                mostrarFacturas();
                break;
            case "3":
                break;
            default:
                mostrarTupla();
        }
    }
    public static void mostrarClientes() {
        clearconsole();
        Query query = em.createQuery("SELECT c FROM Cliente c");
        List<Cliente> Clientes = query.getResultList();
        if (Clientes.size() == 0) {
            System.out.println("No existen clientes :(");
        }
        else {
            System.out.println("-------------------[Clientes]---------------------------");
            for (Cliente cliente : Clientes) {
                printCliente(cliente);
            }
        }
        System.out.println("Presione ENTER para continuar...");
        sc.nextLine();
    }
    public static void mostrarFacturas() {
        clearconsole();
        Query query = em.createQuery("SELECT f FROM Factura f");
        List<Factura> Facturas = query.getResultList();
        if (Facturas.size() == 0) {
            System.out.println("No existen facturas :(");
        }
        else {
            System.out.println("-------------------[Facturas]---------------------------");
            for (Factura factura : Facturas) {
                printFactura(factura);
            }
        }
        System.out.println("Presione ENTER para continuar...");
        sc.nextLine();
    }
    public static void clearconsole(){
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
    }
    public static void main(String arg[]) {
            System.out.println("Main:inicio");
            menu();
            salir();
        }
        public static void salir() {
            if ( em != null )
                if (em.isOpen()) em.close();
            if ( emf != null )
                if (emf.isOpen()) emf.close();
        }
    }