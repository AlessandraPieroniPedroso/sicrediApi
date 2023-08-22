import jdk.jfr.Description;
import org.junit.Before;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ConsultaProdutoComAutorizacaoTest {

    private static String token;

    @Before
    public void setup() {
        // Configurar o caminho para acesso a API
        baseURI = "https://dummyjson.com";
        basePath = "/auth/login";

        // nforme as credenciais de login em formato JSON
        String requestBody = "{\r\n    \"username\": \"kminchelle\",\r\n  " +
                "  \"password\": \"0lelplR\"\r\n}";

        //Configurar a requisição da API
        String token = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("data.token");
    }

    @Test
    @Description("Realizar um login fornecendo um token, retorno esperado Status Code 200")
    public void testDadoDesejoGerarOTokenQuandoSubmetoLoginEntaoTenhoOToken(){
        //configurar o caminho para acesso da api
        baseURI = "https://dummyjson.com";
        basePath = "/auth/login";

        // Informe as credenciais de login em formato JSON
        String requestBody = "{\r\n    \"username\": \"kminchelle\",\r\n    \"password\": \"0lelplR\"\r\n}";

        //Configurar a requisição da API
        String token = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("data.token");



    }

    @Test
    @Description("Realiza a consulta de um produto, passando um token válido, retorno esperado Status Code 200")
    public void testDadoDesejoConsultarUmProdutoQuandoSubmetoDadosProdutoEntaoRetornaStatusCode200(){
        //configurar o caminho para acesso da api
        basePath = "auth/products";

        //Configurar a requisição da API
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
    @Description("Realiza a consulta de um produto, passando um token inválido, retorno esperado Status Code 401")
    public void testDadoDesejoConsultarUmProdutoQuandoSubmetoDadosProdutoEntaoRetornaStatusCode401(){
        //configurar o caminho para acesso da api
        baseURI = "https://dummyjson.com";
        basePath = "auth/products";

        //Configurar a requisição da API
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
