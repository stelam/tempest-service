package com.tempest.service.model;

/**
 * Created by stelam on 2017-11-09.
 */

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "members")
public class Member implements UserDetails {
    static final long serialVersionUID = 1L;

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
    @Column(name = "member_id", nullable = false, updatable = false)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "member")
    private Set<GuildArrival> guildArrivals = new HashSet<GuildArrival>();

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "member_attendances",
            joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "node_war_id", referencedColumnName = "node_war_id"))
    private Set<NodeWar> node_wars = new HashSet<NodeWar>();

    @Column(nullable = false)
    private String password;

    public void grantAuthority(Role authority) {
        if ( roles == null ) roles = new ArrayList<>();
        roles.add(authority);
    }

    @Override
    public List<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        return authorities;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
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

