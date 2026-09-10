package Exercicio2;

public class Main {
    static void main() {
        Loja loja = new Loja("Tech & Games Store");

        Categoria catEletronicos = new Categoria(1, "Eletrônicos");
        Categoria catInformatica = new Categoria(2, "Informática");
        Categoria catGames = new Categoria(3, "Games");

        Produto prod1 = new Produto(101, "Smartphone Galaxy", 2500.0, catEletronicos);
        Produto prod2 = new Produto(102, "Smart TV 55", 3200.0, catEletronicos);
        Produto prod3 = new Produto(103, "Fone Bluetooth", 250.0, catEletronicos);
        catEletronicos.adicionarProduto(prod1);
        catEletronicos.adicionarProduto(prod2);
        catEletronicos.adicionarProduto(prod3);

        Produto prod4 = new Produto(201, "Notebook Gamer", 4800.0, catInformatica);
        Produto prod5 = new Produto(202, "Teclado Mecânico", 350.0, catInformatica);
        Produto prod6 = new Produto(203, "Mouse Sem Fio", 120.0, catInformatica);
        catInformatica.adicionarProduto(prod4);
        catInformatica.adicionarProduto(prod5);
        catInformatica.adicionarProduto(prod6);

        Produto prod7 = new Produto(301, "Console PlayStation 5", 3800.0, catGames);
        Produto prod8 = new Produto(302, "Jogo de Corrida", 200.0, catGames);
        catGames.adicionarProduto(prod7);
        catGames.adicionarProduto(prod8);

        loja.adicionarProduto(prod1);
        loja.adicionarProduto(prod2);
        loja.adicionarProduto(prod3);
        loja.adicionarProduto(prod4);
        loja.adicionarProduto(prod5);
        loja.adicionarProduto(prod6);
        loja.adicionarProduto(prod7);
        loja.adicionarProduto(prod8);

        Cliente cli1 = new Cliente("Carlos Silva", "111.222.333-44", "carlos@email.com");
        Cliente cli2 = new Cliente("Beatriz Souza", "555.666.777-88", "beatriz@email.com");
        Cliente cli3 = new Cliente("Lucas Lima", "999.888.777-66", "lucas@email.com");

        loja.adicionarCliente(cli1);
        loja.adicionarCliente(cli2);
        loja.adicionarCliente(cli3);

        Pedido ped1 = new Pedido(1, cli1);
        ped1.adicionarProduto(prod1, 1);
        ped1.adicionarProduto(prod3, 2);
        cli1.adicionarPedido(ped1);

        Pedido ped2 = new Pedido(2, cli1);
        ped2.adicionarProduto(prod8, 1);
        cli1.adicionarPedido(ped2);

        Pedido ped3 = new Pedido(3, cli2);
        ped3.adicionarProduto(prod4, 1);
        ped3.adicionarProduto(prod5, 1);
        cli2.adicionarPedido(ped3);

        Pedido ped4 = new Pedido(4, cli3);
        ped4.adicionarProduto(prod7, 1);
        ped4.adicionarProduto(prod2, 1);
        cli3.adicionarPedido(ped4);

        System.out.println("=== VALOR DE CADA PEDIDO ===");
        System.out.printf("Pedido #%d (Cliente: %s): R$ %.2f%n", ped1.getNumero(), ped1.getCliente().getNome(), ped1.calcularTotal());
        System.out.printf("Pedido #%d (Cliente: %s): R$ %.2f%n", ped2.getNumero(), ped2.getCliente().getNome(), ped2.calcularTotal());
        System.out.printf("Pedido #%d (Cliente: %s): R$ %.2f%n", ped3.getNumero(), ped3.getCliente().getNome(), ped3.calcularTotal());
        System.out.printf("Pedido #%d (Cliente: %s): R$ %.2f%n", ped4.getNumero(), ped4.getCliente().getNome(), ped4.calcularTotal());
        System.out.println();

        System.out.println("=== TOTAL GASTO POR CLIENTE ===");
        for (Cliente c : loja.listarClientes()) {
            System.out.printf("Cliente: %s | Total Gasto: R$ %.2f%n", c.getNome(), c.calcularTotalGasto());
        }
        System.out.println();

        System.out.println("=== PRODUTO MAIS CARO DE CADA CATEGORIA ===");
        Produto maisCaroEletronicos = catEletronicos.buscarProdutoMaisCaro();
        Produto maisCaroInformatica = catInformatica.buscarProdutoMaisCaro();
        Produto maisCaroGames = catGames.buscarProdutoMaisCaro();

        System.out.printf("%s: %s (R$ %.2f)%n", catEletronicos.getNome(), maisCaroEletronicos.getNome(), maisCaroEletronicos.getPreco());
        System.out.printf("%s: %s (R$ %.2f)%n", catInformatica.getNome(), maisCaroInformatica.getNome(), maisCaroInformatica.getPreco());
        System.out.printf("%s: %s (R$ %.2f)%n", catGames.getNome(), maisCaroGames.getNome(), maisCaroGames.getPreco());
    }
}