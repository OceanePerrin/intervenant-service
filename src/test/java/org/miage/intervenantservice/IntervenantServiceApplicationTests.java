package org.miage.intervenantservice;


import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.miage.intervenantservice.boundary.IntervenantResource;
import org.miage.intervenantservice.entity.Intervenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.*;
import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntervenantServiceApplicationTests {

    @LocalServerPort
    int port;

    @Autowired
    IntervenantResource ir;

    @BeforeEach
    public void setupContext() {
        ir.deleteAll();
        RestAssured.port =port;
    }

    @Test
    void ping () {
        when().get("/intervenants").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    void getAll() {
        Intervenant i1 = new Intervenant(UUID.randomUUID().toString(),
            "Sawyer","Tom","Paris","75000");
        ir.save(i1);
        Intervenant i2 = new Intervenant(UUID.randomUUID().toString(),
        "Blanc","Robert","Les Arcs","73000");
    ir.save(i2);
        when().get("/intervenants").then().statusCode(HttpStatus.SC_OK)
            .and().assertThat().body("size()",equalTo(2));
    }
}
