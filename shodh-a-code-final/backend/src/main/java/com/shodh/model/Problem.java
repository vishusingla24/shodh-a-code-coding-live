
package com.shodh.model;
import jakarta.persistence.*;
@Entity
@Table(name="problem")
public class Problem {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String code;
  private String title;
  @Column(length=4000) private String statement;
  // getters/setters
  public Long getId(){return id;}
  public void setId(Long id){this.id=id;}
  public String getCode(){return code;}
  public void setCode(String code){this.code=code;}
  public String getTitle(){return title;}
  public void setTitle(String title){this.title=title;}
  public String getStatement(){return statement;}
  public void setStatement(String statement){this.statement=statement;}
}
