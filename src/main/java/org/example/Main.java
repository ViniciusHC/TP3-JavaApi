package org.example;
public class Main {
    public static void main(String[] args) {
        try {
            Connection retorno = new Connection();

            StringBuilder entidades = retorno.getEntidades();
            System.out.println("Lista entidades:\n");
            System.out.println(entidades);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}