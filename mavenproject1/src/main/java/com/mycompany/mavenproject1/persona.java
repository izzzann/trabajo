 package com.mycompany.mavenproject1;

 import java.util.ArrayList;

 public class persona {
     private String nombre;
     private String dni;


     public persona(String nombre, String dni) {
         this.nombre = nombre;
         this.dni = dni;
     }

     public String getNombre() {
         return nombre;
     }
     public void setNombre(String nombre) {
         this.nombre = nombre;
     }
     public String getDni() {
         return dni;
     }
     public void setDni(String dni) {
         this.dni = dni;
     }
 }
