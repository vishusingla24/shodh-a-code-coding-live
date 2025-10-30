
package com.shodh.model;
import jakarta.persistence.*;
import java.time.*;
@Entity
@Table(name="submission")
public class Submission {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String submissionUuid;
  private String username;
  private String language;
  @Column(columnDefinition="text") private String sourceCode;
  private String status;
  private Integer score;
  private Instant createdAt;
  // getters/setters
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getSubmissionUuid(){return submissionUuid;}
  public void setSubmissionUuid(String s){this.submissionUuid=s;}
  public String getUsername(){return username;}
  public void setUsername(String u){this.username=u;}
  public String getLanguage(){return language;}
  public void setLanguage(String l){this.language=l;}
  public String getSourceCode(){return sourceCode;}
  public void setSourceCode(String s){this.sourceCode=s;}
  public String getStatus(){return status;}
  public void setStatus(String s){this.status=s;}
  public Integer getScore(){return score;}
  public void setScore(Integer sc){this.score=sc;}
  public Instant getCreatedAt(){return createdAt;}
  public void setCreatedAt(Instant t){this.createdAt=t;}
}
