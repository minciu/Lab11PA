package java.rest.services;

import java.rest.trans.GameTrans;
import java.rest.trans.PlayerTrans;
import java.rest.trans.PlayerGameTrans;
import java.rest.baza.Game;
import java.rest.baza.Player;
import java.rest.baza.PlayerGame;
import java.rest.exceptions.InvalidNumberOfPlayersForGameExceptions;
import java.rest.exceptions.NotFoundExceptions;
import java.rest.repositories.GameRepository;
import java.rest.repositories.PlayerGameRepository;
import java.rest.repositories.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PlayerGameService {

	@Autowired
    private PlayerGameRepository playerGameRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    private boolean checkIfGameExists(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.isPresent();
    }

    public List<PlayerTrans> getPlayersByGame(Long gameId) {
        if (!checkIfGameExists(gameId)) {
            throw new NotFoundExceptions("Game with id " + gameId + " not found");
        }

        return (playerGameRepository.findPlayersByGame(gameId))
                .stream()
                .map(player -> modelMapper.map(player, PlayerTrans.class))
                .collect(Collectors.toList());
    }

    private boolean checkIfPlayerExists(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.isPresent();
    }

    public List<GameTrans> getGamesByPlayer(Long playerId) {
        if (!checkIfPlayerExists(playerId)) {
            throw new NotFoundExceptions("Player with id " + playerId + " not found");
        }

        return (playerGameRepository.findGamesByPlayer(playerId))
                .stream()
                .map(game -> modelMapper.map(game, GameTrans.class))
                .collect(Collectors.toList());
    }

    private int getNumberOfPlayersOfGame(Long gameId) {
        return playerGameRepository.findPlayersByGame(gameId).size();
    }

    public void addPlayerGame(PlayerGameTrans playerGameDto) {
        Long playerId = playerGameDto.getPlayer().getId();
        if (playerId != null && !checkIfPlayerExists(playerId)) {
            throw new NotFoundExceptions("Player with id " + playerId + " not found");
        }

        Long gameId = playerGameDto.getGame().getId();
        if (gameId != null && !checkIfGameExists(gameId)) {
            throw new NotFoundExceptions("Game with id " + gameId + " not found");
        }

        if (getNumberOfPlayersOfGame(gameId) > 1) {
            throw new InvalidNumberOfPlayersForGameExceptions("The same game cannot have more than two players");
        }

        playerGameRepository.save(modelMapper.map(playerGameDto, PlayerGame.class));
    }
}
