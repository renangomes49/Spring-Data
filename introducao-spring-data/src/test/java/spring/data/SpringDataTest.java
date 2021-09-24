package spring.data;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.data.dao.InterfaceSpringDataUser;
import spring.data.model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class SpringDataTest {
	
	@Autowired
	private InterfaceSpringDataUser interfaceSpringDateUser;
	
	@Test
	public void testInserir() {
		
		Usuario usuario = new Usuario("renan","123","Renan Ferreira","fernando@email.com",33);
		interfaceSpringDateUser.save(usuario);
	}
	@Test
	public void testConsultar() {
		
		Optional<Usuario> usuario = interfaceSpringDateUser.findById(1L);
		
		System.out.println(usuario.get());

	}
	
	@Test
	public void consultarTodos() {
			
		Iterable<Usuario> listaUsuarios = interfaceSpringDateUser.findAll();
		
		for (Usuario usuario : listaUsuarios) {
			System.out.println(usuario);
		}
		
	}


	@Test
	public void testAtualizar() {
			
		Optional<Usuario> usuario = interfaceSpringDateUser.findById(1L);
		Usuario usarioAuxiliar = usuario.get();
		
		usarioAuxiliar.setNome("Roberto");
		
		interfaceSpringDateUser.save(usarioAuxiliar);
				
	}
	
	@Test
	public void testDelete() {
			
	//	interfaceSpringDateUser.deleteById(2L);
		
		// outra forma de delete
		 Optional<Usuario> usuario = interfaceSpringDateUser.findById(1L);
		interfaceSpringDateUser.delete(usuario.get());
 		
	}

	@Test
	public void testBuscarTodosPorNome() {
		
		// Query customizada
		List<Usuario> list = interfaceSpringDateUser.buscarTodosPorNome("Renan");
		
		for (Usuario usuario : list) {
			System.out.println(usuario);
		}
	}	
	
	@Test
	public void testBuscarPorNomeParam() {
		
		// Query customizada
		Usuario usuario = interfaceSpringDateUser.buscarPorNomeParam("Renan Ferreira");
		System.out.println(usuario);
		
	}	
	
	@Test
	public void testDeletePorNome() {
		
		// Query customizada
		interfaceSpringDateUser.deletePorNome("Renan");
		
	}	
	
	@Test
	public void testUpdateEmailPorNome() {
		
		// Query customizada
		interfaceSpringDateUser.updateEmailPorNome("renan@email.com","Renan Ferreira");
		
	}	
}




