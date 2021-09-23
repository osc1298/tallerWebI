package ar.edu.unlam.tallerweb1;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


public class ConexionBaseDeDatosTest extends SpringTest{

    @Test
    @Transactional @Rollback(true)
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }
}
