package br.com.jordan.consultaFipe.service;

import br.com.jordan.consultaFipe.model.Marca;
import br.com.jordan.consultaFipe.model.Modelo;
import br.com.jordan.consultaFipe.model.Veiculo;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class FipeApiService {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Marca> getMarcas(String tipoVeiculo) {
        String url = "https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas";
        Marca[] marcas = restTemplate.getForObject(url, Marca[].class);
        return List.of(marcas);
    }

    public List<Modelo> getModelos(String tipoVeiculo, String marcaCodigo) {
        String url = "https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas/" + marcaCodigo + "/modelos";
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        List<Modelo> modelos = new ArrayList<>();
        response.get("modelos").forEach(node -> modelos.add(new Modelo(node.get("codigo").asText(),node.get("nome").asText())));
        return modelos;
    }

    public List<Veiculo> getVeiculos(String tipoVeiculo, String marcaCodigo, String modeloCodigo) {
        String url = "https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas/" + marcaCodigo + "/modelos/" + modeloCodigo + "/anos";
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        List<Veiculo> veiculos = new ArrayList<>();
        response.forEach(node -> {
            String anoCodigo = node.get("codigo").asText();
            Veiculo veiculo = getVeiculo(tipoVeiculo, marcaCodigo, modeloCodigo, anoCodigo);
            veiculos.add(veiculo);
        });
        return veiculos;
    }

    public Veiculo getVeiculo(String tipoVeiculo, String marcaCodigo, String modeloCodigo, String anoCodigo) {
        String url = "https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas/" + marcaCodigo + "/modelos/" + modeloCodigo + "/anos/" + anoCodigo;
        return restTemplate.getForObject(url, Veiculo.class);
    }

}
