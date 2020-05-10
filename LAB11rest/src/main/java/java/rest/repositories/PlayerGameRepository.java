package java.rest.repositories;

import java.rest.baza.Game;
import java.rest.baza.Player;
import java.rest.baza.PlayerGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerGameRepository extends CrudRepository<PlayerGame, Long> {
	 @Query("select pg.player from PlayerGame pg where pg.game.id = :gameId")
	    List<Player> findPlayersByGame(@Param("gameId") Long gameId);

	    @Query("select pg.game from PlayerGame pg where pg.player.id = :playerId")
	    List<Game> findGamesByPlayer(@Param("playerId") Long playerId);
}
