package org.example;
public class Main {
    public static void main(String[] args) {
        try {
            ConexaoEntidades retorno = new ConexaoEntidades();

            //pegar todas entidades
            StringBuilder entidades = retorno.getEntidades();
            System.out.println("Lista entidades:\n");
            System.out.println(entidades);

            //pegar entidade pelo ID
            System.out.println("\nDados da entidade especifica:\n");
            StringBuilder entidade1 = retorno.getEntendidadeId(1);
            System.out.println(entidade1);
            StringBuilder entidade2 = retorno.getEntendidadeId(2);
            System.out.println(entidade2);
            StringBuilder entidade3 = retorno.getEntendidadeId(3);
            System.out.println(entidade3);
            StringBuilder entidade4 = retorno.getEntendidadeId(4);
            System.out.println(entidade4);
            StringBuilder entidade5 = retorno.getEntendidadeId(5);
            System.out.println(entidade5);
            StringBuilder entidade6 = retorno.getEntendidadeId(6);
            System.out.println(entidade6);
            StringBuilder entidade7 = retorno.getEntendidadeId(7);
            System.out.println(entidade7);
            StringBuilder entidade8 = retorno.getEntendidadeId(8);
            System.out.println(entidade8);

            //Id não encontrado
            StringBuilder entidade13 = retorno.getEntendidadeId(13);
            System.out.println(entidade13);

            //URL Fake
            StringBuilder urlManual = retorno.UrlFake("/entities?categoria=teste&limite=5");
            System.out.println(urlManual);




        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}