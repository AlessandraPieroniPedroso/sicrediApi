import jdk.jfr.Description;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ConsultaUsuarioTest {

    @Test
    @Description("Consulta o Status da aplicação, retorno esperado Status Code 200")
    public void testDadoConsultarStatusAplicacaoQuandoConsultoAplicacaoEntaoRetornaStatusCode200(){
        //configurar o caminho para acesso da api
        baseURI = "https://dummyjson.com";
        basePath = "/test";

        //Configurar os dados da requisição da API
        given()
                .get("/test")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);


    }

    @Test
    @Description("Consulta um usuário, retorno esperado Status Code 200")
    public void testDadoQueDesejoConsultarUsuarioQuandoSubmetoDadosEntaoRetornaStatusCode200(){
        //configurar o caminho para acesso da API
        baseURI = "https://dummyjson.com";
        basePath = "/users";

        //Configurar os dados da requisição da API
        given()
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(equalTo(200));

    }


}


