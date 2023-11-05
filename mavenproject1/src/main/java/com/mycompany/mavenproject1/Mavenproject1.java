package com.mycompany.mavenproject1;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Mavenproject1 {

    public static void main(String[] args) {
        File excel = new File("Productos.xlsx");
        Scanner scanner = new Scanner(System.in);
        Boolean salir = false;

        while (salir == false) {
            System.out.println(
                    "Bienvenido, elige que quieres hacer.\n 1.Mostrar productos.\n 2.Comprar productos.\n 3.Salir.");
            Integer eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1:
                    mostrarExcel(excel);
                    break;
                case 2:
                    mostrarExcel(excel);
                    comprar(excel);
                    break;
                case 3:
                    salir = true;
                    break;
            }
        }

    }

    public static void mostrarExcel(File excel) {
        ;
        try (FileInputStream archivo = new FileInputStream(excel);
                Workbook workBook = new XSSFWorkbook(archivo)) {
            Sheet hoja = workBook.getSheetAt(0);

            for (Row fila : hoja) {
                int columna = fila.getRowNum();
                if (columna > 0) {
                    for (Cell celda : fila) {
                        int columnas = celda.getColumnIndex();
                        if (columnas == 0 || columnas == 1) {
                            Cell valor = celda;
                            DataFormatter dataFormatter = new DataFormatter();
                            String id = dataFormatter.formatCellValue(celda);
                            System.out.print(id + " ");
                        }
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Ha fallado algo");
        }
    }

    public static List<String> obtenerIDsDeExcel(File excel) {
        List<String> ids = new ArrayList<>();

        try (FileInputStream archivo = new FileInputStream(excel);
                Workbook workBook = new XSSFWorkbook(archivo)) {
            Sheet hoja = workBook.getSheetAt(0);

            for (Row fila : hoja) {
                int columna = fila.getRowNum();
                if (columna > 0) {
                    for (Cell celda : fila) {
                        int columnas = celda.getColumnIndex();
                        if (columnas == 1 || columnas == 3) {
                            DataFormatter dataFormatter = new DataFormatter();
                            String id = dataFormatter.formatCellValue(celda);
                            ids.add(id);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Ha fallado algo al leer el Excel");
        }

        return ids;
    }

    public static void comprar(File excel) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hola, dime como te llamas: ");
        String nombre = scanner.next();

        System.out.println("");

        System.out.print("Dime tu DNI: ");
        String dni = scanner.next();

        List<String> idsDeExcel = obtenerIDsDeExcel(excel);
        List<String> productosComprados = new ArrayList<>();

        System.out.println("");

        while (true) {
            System.out.print("Ingrese el ID del producto que desea comprar (0 para finalizar la compra): ");
            String idProducto = scanner.next();

            if (idProducto.equals("0")) {
                break;
            }

            if (idsDeExcel.contains(idProducto)) {
                productosComprados.add(idProducto);
                System.out.println("Producto añadido al carrito: " + idProducto);
            } else {
                System.out.println("El producto con ID " + idProducto + " no existe");
            }
        }

        persona persona = new persona(nombre, dni);
        System.out.println(
                persona.getNombre() + ", con DNI: " + persona.getDni() + "\nHa comprado los siguientes productos: ");
        for (String producto : productosComprados) {
            System.out.println(producto);
        }
    }

}