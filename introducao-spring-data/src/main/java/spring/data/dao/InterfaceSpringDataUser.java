package spring.data.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.hibernate.LockMode;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.data.model.Usuario;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<Usuario, Long>{

	@Query(value = "select u from Usuario u where u.nome like %?1%")
	public List<Usuario> buscarTodosPorNome(String nome);
	
	@Lock(LockModeType.READ) // bloqueia o objeto para que outra pessoa não possa fazer operação com ele
	@Query(value = "select u from Usuario u where u.nome = :paramnome")
	public Usuario buscarPorNomeParam(@Param("paramnome") String paramnome);

	
	default <S extends Usuario> S saveAtual(S entity) {
		// Processar algo e no final salva
		
		return save(entity);
	}
	
	@Modifying
	@Transactional // import do Spring - abrir uma transação para fazer a operação do delete
	@Query(value = "delete from Usuario u where u.nome = ?1")
	public void deletePorNome(String nome);
	
	@Modifying
	@Transactional
	@Query("update Usuario u set u.email = ?1 where u.nome = ?2")
	public void updateEmailPorNome(String email, String nome);
	
}


