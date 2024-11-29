package main.com.consoleapp.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * Base class for all classes to include the id
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // Use logical names for subtypes
        include = JsonTypeInfo.As.PROPERTY, // Include the type information as a property
        property = "@type" // The property name to store the type information
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Person.class, name = "person"),
        @JsonSubTypes.Type(value = Location.class, name = "location"),
        @JsonSubTypes.Type(value = Race.class, name = "race"),
        @JsonSubTypes.Type(value = Sponsor.class, name = "sponsor"),
        @JsonSubTypes.Type(value = TeamSponsor.class, name = "teamSponsor"),
        @JsonSubTypes.Type(value = TeamSponsorRace.class, name = "teamSponsorRace"),
        @JsonSubTypes.Type(value = Team.class, name = "team")
})
public abstract class Entity{
    private int id;

    public Entity(int id)
    {
        this.id = id;
    }

    public Entity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Id: " + id;
    }
}
