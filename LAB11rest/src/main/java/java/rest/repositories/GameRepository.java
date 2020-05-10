package java.rest.repositories;


import java.rest.baza.Game;
import org.springframework.data.repository.CrudRepository;
public interface GameRepository extends CrudRepository<Game, Long> {

}
