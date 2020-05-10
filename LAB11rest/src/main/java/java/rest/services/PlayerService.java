package java.rest.services;


import java.rest.trans.PlayerTrans;
import java.rest.baza.Player;
import java.rest.exceptions.DuplicateExceptions;
import java.rest.exceptions.NotFoundExceptions;
import java.rest.repositories.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

	@Autowired
    private PlayerRepository playerRepository;
    private final ModelMapper modelMapper = new ModelMapper();


    public List<PlayerTrans> getAllPlayers() {
        return ((List<Player>) playerRepository.findAll())
                .stream()
                .map(player -> modelMapper.map(player, PlayerTrans.class))
                .collect(Collectors.toList());
    }

    public void addPlayer(PlayerTrans playerTrans) {
        if (playerTrans.getId() != null && checkIfPlayerExists(playerTrans.getId())) {
            throw new DuplicateExceptions("Player with id " + playerTrans.getId() + " already exists");
        }
        playerRepository.save(modelMapper.map(playerTrans, Player.class));
    }

    private boolean checkIfPlayerExists(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.isPresent();
    }

    public void updatePlayerName(Long id, String name) {
        if (!checkIfPlayerExists(id)) {
            throw new NotFoundExceptions("Player with id " + id + " not found");
        }
        playerRepository.updateName(id, name);
    }

    public void deletePlayer(Long id) {
        if (!checkIfPlayerExists(id)) {
            throw new NotFoundExceptions("Player with id " + id + " not found");
        }
        playerRepository.deleteById(id);
    }
	
}
