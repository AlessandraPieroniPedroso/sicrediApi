package teste;

import org.junit.Test;
import static io.restassured.RestAssured.*;


public class CadastroConsultaProdutoSemAutorizacaoTest {

    @Test
    public void testDadoDesejoCadastrarUmProdutoQuandoSubmetoOsDadosEntaoTenhoRetornoStatusCode200(){
        //configurar o caminho para acesso da minha api
        baseURI = "https://dummyjson.com";
        basePath = "/products/add";

        // Submitter the data of the Product
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

        given()
                .body(jsonBody)
                .header("Content-Type", "application/json")
                .when()
                .post()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testDadoDesejoCadastrarUmProdutoQuandoSubmetoOsDadosEntaoTenhoRetornoStatusCode400(){
        //configurar o caminho para acesso da minha api
        baseURI = "https://dummyjson.com";
        basePath = "/products/add";

        // Submitter the data of the Product
        String jsonBody = "{\n" +
                "    \"title\": \"Perfume\",\n" +
                "    \"description\": \"Dior\",\n" +
                "    \"price\": 20,\n" +
                "    \"discountPercentage\": 10 ,\n" +
                "    \"rating\": ,\n" +
                "    \"stock\": 100,\n" +
                "    \"brand\": \"Impression of Acqua Di Gio\",\n" +
                "    \"category\": \"fragrances\",\n" +
                "    \"thumbnail\": \"https://i.dummyjson.com/data/products/11/thumbnail.jpg\"\n" +
                "}";

        given()
                .body(jsonBody)
                .header("Content-Type", "application/json")
                .when()
                .post()
                .then()
                .log().all()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void testDadoDesejoConsultarUmProdutoQuandoSubmetoDadosProdutoEntaoRetornaStatusCode200(){
        //configurar o caminho para acesso da minha api
        baseURI = "https://dummyjson.com";
        basePath = "/products";

        //Informar dados do produto

        given()
                .when()
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void testDadoDesejoConsultarUmProdutoPeloIdQuandoInformoIdDadosProdutoEntaoRetornaStatusCode200(){
        //configurar o caminho para acesso da minha api
        baseURI = "https://dummyjson.com";
        basePath = "/products";

        //Informar dados do produto

        given()
                .when()
                .get("/1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void testDadoDesejoConsultarUmProdutoPeloIdQuandoInformoIdDadosProdutoEntaoRetornaStatusCode404(){
        //configurar o caminho para acesso da minha api
        baseURI = "https://dummyjson.com";
        basePath = "/products";

        //Informar dados do produto

        given()
                .when()
                .get("/1000")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404);

    }
}
