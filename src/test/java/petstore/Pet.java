// 1 - Pacote: grupo de classes
package petstore;

// 2 - Bibliotecas: onde definimos

import io.restassured.specification.Argument;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.defaultParser;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

// 3 - Classe: o que queremos manter informação e interagir
public class Pet {
    // 3.1 - Atributos: adjetivos, características dos objetos
    String uri = "https://petstore.swagger.io/v2/pet"; //endereço da entidade PET no Swagger


    // 3.2 - Métodos ação que não retorna nenhum valor e Funções são quando fazem e trazem um retorno, um resultado
        // método para leitura do arquivo Json onde estamos passando os dados
    public String lerJson(String caminhoJson) throws IOException { // essa estrutura toda lê o arquivo JSon e devolve o conteúdo
        return new String (Files.readAllBytes(Paths.get(caminhoJson))); // a leitura sempre é da direita pra esquerda como uma expressão matemática. Então primeiro ele pega o arquivo do Json "Paths.get(caminhoJson)", depois ele lê o arquivo "Files.readAllBytes" e depois ele transforma em uma string jogando numa variável local "return new String "
    }

    // Incluir - Create - na API é Post
    @Test // é preciso sempre incluir essa notação para identificar que aqui começa o seu teste. Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("data/pet1.json");


        // Sintaxe Gherklin
        // Comunicação estruturada: Dado, Quando, Então // Given, When, Then

        //dado
        given()
                .contentType("application/json") // comum em API REST - antigas APIs eram em text/xml
                .log().all()
                .body(jsonBody)
        //quando
        .when()
                .post(uri)
        //então
        .then()
                .log().all()
                .statusCode(200)
                .body("category.name", is ("Dog")) //aqui tem um valor exclusivo
                .body("tags.name", contains ("STAKarol")) //aqui pode ter  multiplas opções
                .body("name", is("Duda Ferraz"))
                .body("status", is("available"))
        ;
    }

}
