package teste;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ConsultaProdutoComAutorizacaoTest {

    private static String token;

    @Before
    public void setup() {
        // Configure the base URI for accessing the API
        baseURI = "https://dummyjson.com";
        basePath = "/auth/login";

        // Inform the login credentials in a JSON format
        String requestBody = "{\r\n    \"username\": \"kminchelle\",\r\n  " +
                                    "  \"password\": \"0lelplR\"\r\n}";

        String token = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200) // Assuming 200 is the expected status code for a successful login
                .extract()
                .path("data.token");
    }

    @Test
    public void testDadoDesejoGerarOTokenQuandoSubmetoLoginEntaoTenhoOToken(){
        //configurar o caminho para acesso da minha api
        baseURI = "https://dummyjson.com";
        basePath = "/auth/login";

        // Informe as credenciais de login em formato JSON
        String requestBody = "{\r\n    \"username\": \"kminchelle\",\r\n    \"password\": \"0lelplR\"\r\n}";

        String token = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200) // Supondo que 200 seja o código de status esperado para um login bem-sucedido
                .extract()
                .path("data.token");



    }

    @Test
    public void testDadoDesejoConsultarUmProdutoQuandoSubmetoDadosProdutoEntaoRetornaStatusCode200(){
        //configurar o caminho para acesso da minha api
        baseURI = "https://dummyjson.com";
        basePath = "auth/products";

        //Informar dados do produto
        if (token != null) {
            given()
                    .header("Authorization", token)
                    .header("Content-Type", "application/json")
                    .when()
                    .get()
                    .then()
                    .log().all()
                    .assertThat()
                    .statusCode(200);
        }
    }

    @Test
    public void testDadoDesejoConsultarUmProdutoQuandoSubmetoDadosProdutoEntaoRetornaStatusCode401(){
        //configurar o caminho para acesso da minha api
        baseURI = "https://dummyjson.com";
        basePath = "auth/products";

        //Informar dados do produto

        given()
                .header("Authorization", token+1)
                .header("Content-Type", "application/json")
                .when()
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(401);

    }


}
