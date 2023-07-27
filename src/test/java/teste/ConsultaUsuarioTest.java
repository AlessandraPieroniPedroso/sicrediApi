package teste;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ConsultaUsuarioTest {

    @Test
    public void testDadoConsultarStatusAplicacaoQuandoConsultoAplicacaoEntaoRetornaStatusCode200(){
        //configurar o caminho para acesso da minha api
        baseURI = "https://dummyjson.com";
        basePath = "/test";

        //retorno do status code
        given()
                .get("/test")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);


    }

    @Test
    public void testDadoQueDesejoConsultarUsuarioQuandoSubmetoDadosEntaoRetornaStatusCode200(){
        //configurar o caminho para acesso da minha api
        baseURI = "https://dummyjson.com";
        basePath = "/users";



        //informo os dados do usuário
        given()
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(equalTo(200));

    }


}
