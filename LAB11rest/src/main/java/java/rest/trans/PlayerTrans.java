package java.rest.trans;


import io.swagger.annotations.ApiModel;
@ApiModel(description = "Details about the players." +
        " A player must have at least a name.")
public class PlayerTrans {

	private Long id;
    private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
