import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import org.testng.reporters.XMLReporter;

public class CadastroConsultaProdutoSemAutorizacaoTest {


    @BeforeClass
    public void setUp() {

        RestAssured.baseURI = "https://dummyjson.com";
    }

    @Test

    @Description("Cadastrar um produto, esperdo Status Code 200")
    public void testDadoDesejoCadastrarUmProdutoQuandoSubmetoOsDadosEntaoTenhoRetornoStatusCode200(){

        //configurar o caminho para acesso da minha api
        basePath = "/products/add";

        // Submeter os dados do Produto
        String jsonBody = "{\n" +
                "    \"title\": \"Perfume Oil\",\n" +
                "    \"description\": \"Mega Discount, Impression of A...\",\n" +
                "    \"price\": 13,\n" +
                "    \"discountPercentage\": 8.4,\n" +
                "    \"rating\": 4.26,\n" +
                "    \"stock\": 65,\n" +
                "    \"brand\": \"Impression of Acqua Di Gio\",\n" +
                "    \"category\": \"fragrances\",\n" +
                "    \"thumbnail\": \"https://i.dummyjson.com/data/products/11/thumbnail.jpg\"\n" +
                "}";
        Response response =
                given()
                        .body(jsonBody)
                        .header("Content-Type", "application/json")
                        .when()
                        .post()
                        .then()
                        .log().all()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();




    }


    @Test
    @Description("Consultar uma lista de produtos, espero o Status Code 200")
    public void testDadoDesejoConsultarUmProdutoQuandoSubmetoDadosProdutoEntaoRetornaStatusCode200(){
        //configurar o caminho para acesso da minha api
        basePath = "/products";

        //Realizar a requisição na API
        given()
                .when()
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

    @Test
    @Description("Consultar um produto especifico pelo ID, espero Status Code 200")
    public void testDadoDesejoConsultarUmProdutoPeloIdQuandoInformoIdDadosProdutoEntaoRetornaStatusCode200(){
        //configurar o caminho para acesso da minha api
        basePath = "/products";

        //Realizar a requisição na API
        given()
                .when()
                .get("/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

    @Test
    @Description("Consultar um produto não cadastrado pelo ID, espero o Status Code 404")
    public void testDadoDesejoConsultarUmProdutoPeloIdQuandoInformoIdDadosProdutoEntaoRetornaStatusCode404(){
        //configurar o caminho para acesso da minha api
        basePath = "/products";

        //Realizar a requisição na API
        given()
                .when()
                .get("/1000")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404);

    }
}
