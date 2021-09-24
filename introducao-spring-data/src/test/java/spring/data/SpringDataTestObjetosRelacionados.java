package spring.data;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.data.dao.InterfaceSpringDataTelefone;
import spring.data.dao.InterfaceSpringDataUser;
import spring.data.model.Telefone;
import spring.data.model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class SpringDataTestObjetosRelacionados {

	@Autowired
	InterfaceSpringDataTelefone interfaceSpringDateTelefone;
	
	@Autowired
	InterfaceSpringDataUser interfaceSprindDateUser;
	
	@Test
	public void insertTelefone() {
		
		
		Optional<Usuario> usuario = interfaceSprindDateUser.findById(7L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("celular");
		telefone.setNumero("90 9 9000 9000");
		telefone.setUsuario(usuario.get());
		
		interfaceSpringDateTelefone.save(telefone);
	}
	
	@Test
	public void consultar() {
		
		Optional<Usuario> usuario = interfaceSprindDateUser.findById(7L);
	
		for (Telefone telefone : usuario.get().getTelefones()) {
			System.out.println(telefone.getUsuario().getNome());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getNumero());
			System.out.println("_______________________________________");
		}
		
	}
}





