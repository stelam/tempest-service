package com.tempest.service.model;

/**
 * Created by stelam on 2017-11-09.
 */

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {
    @GenericGenerator(
        name = "usersSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "usersSequence"),
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "1")
        }
    )

    @Id
    @GeneratedValue(generator = "usersSequenceGenerator")
    @Column(name = "member_id")
    private long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "member")
    private Set<GuildArrival> guildArrivals = new HashSet<GuildArrival>();

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "member_attendances",
            joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "node_war_id", referencedColumnName = "node_war_id"))
    private Set<NodeWar> node_wars = new HashSet<NodeWar>();

    private String password;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<GuildArrival> getGuildArrivals() {
        return guildArrivals;
    }

    public void setGuildArrivals(Set<GuildArrival> guildArrivals) {
        this.guildArrivals = guildArrivals;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Set<NodeWar> getNode_wars() {
        return node_wars;
    }

    public void setNode_wars(Set<NodeWar> node_wars) {
        this.node_wars = node_wars;
    }
}

