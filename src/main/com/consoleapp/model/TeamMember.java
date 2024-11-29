package main.com.consoleapp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // Use logical names for subtypes
        include = JsonTypeInfo.As.PROPERTY, // Include the type information as a property
        property = "@type" // The property name to store the type information
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TeamManager.class, name = "teamManager"),
        @JsonSubTypes.Type(value = Driver.class, name = "driver"),
        @JsonSubTypes.Type(value = Engineer.class, name = "engineer")
})
public class TeamMember extends Person {

    private int teamId;

    @JsonProperty("@type")
    private final String type = "teamMember";
    public TeamMember(int id, String name, int age, int experience, float salary, String username,
                      String password, int teamId) {
        super(id, name, age, experience, salary, username, password);
        this.teamId = teamId;
    }
    public TeamMember(){}

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
