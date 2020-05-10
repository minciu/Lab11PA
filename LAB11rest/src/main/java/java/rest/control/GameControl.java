package java.rest.control;


import java.rest.trans.GameTrans;
import java.rest.services.GameService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@Validated
public class GameControl {

	@Autowired
    private GameService gameService;

    @GetMapping
    @ApiOperation(value = "Retrieve all finished games",
            response = GameTrans.class,
            responseContainer = "List")
    public List<GameTrans> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping
    @ApiOperation(value = "Add a new game")
    public ResponseEntity<String> addGame(@RequestBody GameTrans gameTrans) {
        gameService.addGame(gameTrans);
        return new ResponseEntity<>("Game created", HttpStatus.CREATED);
    }
}
