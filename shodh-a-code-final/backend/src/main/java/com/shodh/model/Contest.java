
package com.shodh.model;
import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name="contest")
public class Contest {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(unique=true) private String contestId;
  // getters/setters
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getName(){return name;}
  public void setName(String name){this.name=name;}
  public String getContestId(){return contestId;}
  public void setContestId(String contestId){this.contestId=contestId;}
}
