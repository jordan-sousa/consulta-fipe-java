package br.com.jordan.consultaFipe.controller;

import br.com.jordan.consultaFipe.model.Marca;
import br.com.jordan.consultaFipe.model.Modelo;
import br.com.jordan.consultaFipe.model.Veiculo;
import br.com.jordan.consultaFipe.service.FipeApiService;

import java.util.List;
import java.util.Scanner;

public class FipeController {
    private FipeApiService fipeApiService = new FipeApiService();

    private final Scanner leitura = new Scanner(System.in);

    public void consultarFipe() {
        System.out.println("Digite o tipo de veiculo(carros, motos ou caminhoes)");
        String tipoVeiculo = leitura.nextLine();

        List<Marca> marcas = fipeApiService.getMarcas(tipoVeiculo);
        marcas.forEach(marca -> System.out.println(marca.getCodigo() + " - " + marca.getNome()));

        System.out.println("Escolha a marca pelo codigo: ");
        String marcaCodigo = leitura.nextLine();

        List<Modelo> modelos = fipeApiService.getModelos(tipoVeiculo, marcaCodigo);
        modelos.forEach(modelo -> System.out.println(modelo.getCodigo() + " - " + modelo.getNome()));

        System.out.println("Digite um trecho do modelo que desejar visuallizar: ");
        String trechoModelo = leitura.nextLine();
        modelos.stream()
                .filter(modelo -> modelo.getNome().contains(trechoModelo))
                .forEach(modelo -> System.out.println(modelo.getCodigo() + " - " + modelo.getNome()));

        System.out.println("Escolha o modelo pelo codigo: ");
        String modeloCodigo = leitura.nextLine();

        List<Veiculo> veiculos = fipeApiService.getVeiculos(tipoVeiculo, marcaCodigo, modeloCodigo);
        veiculos.forEach(veiculo -> System.out.println("Ano: " + veiculo.getAnoModelo() + " - valor " + veiculo.getValor()));
    }
}
