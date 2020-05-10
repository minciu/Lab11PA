package java.rest.control;

import java.rest.trans.PlayerTrans;
import java.rest.services.PlayerService;
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
@RequestMapping("/players")
@Validated

public class PlayerControl {

	@Autowired
    private PlayerService playerService;

    @GetMapping
    @ApiOperation(value = "Retrieve all players",
            response = PlayerTrans.class,
            responseContainer = "List")
    public List<PlayerTrans> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping
    @ApiOperation(value = "Add a new player")
    public ResponseEntity<String> addPlayer(@RequestBody PlayerTrans playerDto) {
        playerService.addPlayer(playerDto);
        return new ResponseEntity<>("Player created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing player name")
    public ResponseEntity<String> updatePlayerName(@ApiParam(value = "id of the player you want to update", required = true, example = "5")
                                     @PathVariable @Valid @Min(0) Long id, @RequestParam String name) {
        playerService.updatePlayerName(id, name);
        return new ResponseEntity<>("Player updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a player")
    public ResponseEntity<String> deletePlayer(@ApiParam(value = "id of the player you want to delete", required = true, example = "5")
            @PathVariable @Valid @Min(0) Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>("Player deleted", HttpStatus.OK);
    }
}
