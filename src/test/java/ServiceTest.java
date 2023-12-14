import org.example.dto.UsuarioDTOInput;
import org.example.service.UsuarioService;
import org.junit.Assert;
import org.junit.Test;

public class ServiceTest {
    @Test
    public void Teste() {
        UsuarioService service = new UsuarioService();


        UsuarioDTOInput usuarioTeste = new UsuarioDTOInput();
        usuarioTeste.setId(1);
        usuarioTeste.setNome("joaquim barbosa");
        usuarioTeste.setSenha("Vasco se safou");
        service.inserirUsuario(usuarioTeste);

        Assert.assertEquals(1, service.listarUsuarios().size());
    }
}
