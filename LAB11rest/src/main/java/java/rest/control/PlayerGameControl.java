package java.rest.control;

import java.rest.trans.GameTrans;
import java.rest.trans.PlayerTrans;
import java.rest.trans.PlayerGameTrans;
import java.rest.services.PlayerGameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/players_games")
@Validated

public class PlayerGameControl {

	@Autowired
    private PlayerGameService playerGameService;

    @GetMapping("/games/{id}")
    @ApiOperation(value = "Get all the players who played a game")
    public List<PlayerTrans> getPlayersByGame(@ApiParam(value = "id of the game for which you want to see the players", required = true, example = "5")
            @PathVariable @Valid @Min(0) Long id) {
        return playerGameService.getPlayersByGame(id);
    }

    @GetMapping("/players/{id}")
    @ApiOperation(value = "Get all the games played by a player")
    public List<GameTrans> getGamesByPlayer(@ApiParam(value = "id of the player you want to see which games he played", required = true, example = "5")
            @PathVariable @Valid @Min(0) Long id) {
        return playerGameService.getGamesByPlayer(id);
    }

    @PostMapping
    @ApiOperation(value = "Add a player to a game",
        notes = "A game cannot be player by more than two players.")
    public ResponseEntity<String> addPlayerGame(@RequestBody PlayerGameTrans playerGameTrans) {
        playerGameService.addPlayerGame(playerGameTrans);
        return new ResponseEntity<>("Player added to the game", HttpStatus.CREATED);
    }
}
