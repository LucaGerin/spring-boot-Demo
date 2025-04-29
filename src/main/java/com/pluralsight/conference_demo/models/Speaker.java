package com.pluralsight.conference_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity(name="speakers") //Same name as in DB
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Speaker {

    //Attributes have the same name as columns in the DB (If it was otherwise, we would have needed to annotate them according to JPA syntax)
    @Id //To say it is a primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY) //To say how it is generated
    private Long speaker_id;

    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;

    @Lob //LargeOBject
    //@Type(type="org.hibernate.type.BinaryType") //Not needed anymore with @Lob from hibernate 6
    private byte[] speaker_photo;

    @ManyToMany(mappedBy = "speakers") //It is the other side of the Many2Many
    @JsonIgnore //to prevent serialization to loop back infinitely
    private List<Session> sessions;

    //Constructor
    public Speaker() {
    }

    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }

    public void setSpeaker_photo(byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Long getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(Long speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpeaker_bio() {
        return speaker_bio;
    }

    public void setSpeaker_bio(String speaker_bio) {
        this.speaker_bio = speaker_bio;
    }
}
