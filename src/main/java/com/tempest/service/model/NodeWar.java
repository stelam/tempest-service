package com.tempest.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by stelam on 2017-11-14.
 */

@Entity
@Table(name = "node_wars")
public class NodeWar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "node_war_id")
    private long id;

    @Column(name = "node_war_date")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date date;

    @ManyToMany
    @JoinTable(name = "member_attendances",
            joinColumns = @JoinColumn(name = "node_war_id", referencedColumnName = "node_war_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id", referencedColumnName = "member_id"))
    private Set<Member> members = new HashSet<Member>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
