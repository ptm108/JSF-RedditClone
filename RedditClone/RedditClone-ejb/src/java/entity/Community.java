/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 *
 * @author p.tm
 */
@Entity
public class Community implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private String name;
  private String title;
  private String description;

  @ManyToMany
  @JoinTable(name = "member_community", joinColumns = @JoinColumn(name = "community_id"),
          inverseJoinColumns = @JoinColumn(name = "member_id"))
  private List<Redditor> members;

  @ManyToMany
  @JoinTable(name = "moderator_community", joinColumns = @JoinColumn(name = "community_id"),
          inverseJoinColumns = @JoinColumn(name = "moderator_id"))
  private List<Redditor> moderators;

  @OneToMany(mappedBy = "community")
  @OrderBy("timeCreated DESC")
  private List<Post> posts;

  // helper methods
  public void addMember(Redditor r) {
    this.members.add(r);
  }

  public void removeMember(Redditor r) {
    this.members.remove(r);
  }

  public void addPost(Post p) {
    this.posts.add(0, p);
  }

  public void removePost(Post p) {
    this.posts.remove(p);
  }

  // getters and setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Redditor> getMembers() {
    return members;
  }

  public void setMembers(List<Redditor> members) {
    this.members = members;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Redditor> getModerators() {
    return moderators;
  }

  public void setModerators(List<Redditor> moderators) {
    this.moderators = moderators;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Community)) {
      return false;
    }
    Community other = (Community) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entity.Community[ id=" + id + " ]";
  }

}
