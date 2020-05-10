package java.rest.repositories;

import java.rest.baza.Player;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PlayerRepository extends CrudRepository<Player, Long> {

	@Transactional
    @Modifying
    @Query("update Player p set p.name = :name where p.id = :id")
    void updateName(@Param("id") Long id, @Param("name") String name);
}
