package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com o caminho do arquivo: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Sale> saleList = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");

                saleList.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]),
                        fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));

                line = br.readLine();
            }

            Comparator<Sale> comp = Comparator.comparing(Sale::averagePrice);

            List<Sale> top5Sale = saleList.stream().filter(sale -> sale.getYear() == 2016)
                    .sorted(comp.reversed()).limit(5).collect(Collectors.toList());

            System.out.println("Cinco primeiras vendas de 2016 de maior preço médio");
            top5Sale.forEach(System.out::println);

            saleList.removeIf(sale -> sale.getMonth() != 1 && sale.getMonth() != 7);

            Double loganSeller = saleList.stream().filter(sale -> Objects.equals(sale.getSeller(), "Logan"))
                    .map(Sale::getTotal)
                    .reduce(0.00, Double::sum);

            System.out.printf("%nValor total vendido pelo vendedor Logan nos meses 1 e 7 = %.2f",loganSeller);

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}