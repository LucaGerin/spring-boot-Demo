package com.pluralsight.conference_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity(name="sessions") //Same name as in DB
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session {

    //Attributes have the same name as columns in the DB (If it was otherwise, we would have needed to annotate them according to JPA syntax)
    @Id //To say it is a primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY) //To say how it is generated
    private Long session_id;

    private String session_name;
    private String session_description;
    private Integer session_length;

    //Many-to-Many relationship
    //Session is the owner of the relationship
    @ManyToMany //I have a Many2Many with a mapping Join table in the DB
    @JoinTable( //Define the Join table
            name="session_speakers",
            joinColumns = @JoinColumn(name="session_id"),
            inverseJoinColumns = @JoinColumn(name="speaker_id")
    )
    private List<Speaker> speakers;

    //Constructor
    public Session() {
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Long getSession_id() {
        return session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }
}
