package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

            Comparator<Double> comp = Double::compareTo;

           List<Sale> saleList1 = saleList.stream().filter(x -> x.getYear() == 2016).collect(Collectors.toList());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}