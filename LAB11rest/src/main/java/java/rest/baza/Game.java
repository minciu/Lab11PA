package java.rest.baza;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import lombok.ToString;

@ToString
public class Game {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<PlayerGame> getPlayerGames() {
		return playerGames;
	}

	public void setPlayerGames(List<PlayerGame> playerGames) {
		this.playerGames = playerGames;
	}

	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "content cannot be null")
    private String content;

    @NotNull(message = "result cannot be null")
    private String result;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<PlayerGame> playerGames = new ArrayList<>();
}
