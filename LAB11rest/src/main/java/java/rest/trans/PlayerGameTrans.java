package java.rest.trans;

public class PlayerGameTrans {

	private Long id;
    private PlayerTrans player;
    private GameTrans game;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PlayerTrans getPlayer() {
		return player;
	}
	public void setPlayer(PlayerTrans player) {
		this.player = player;
	}
	public GameTrans getGame() {
		return game;
	}
	public void setGame(GameTrans game) {
		this.game = game;
	}
}
