package cl.telematic.model;

import javax.persistence.*;
import javax.persistence.metamodel.Type;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class RemoteXml {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length=10000)
    //@Lob
    //@org.hibernate.annotations.Type(type="org.hibernate.type.MaterializedClobType")
     private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date parsed;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getParsed() {
        return parsed;
    }

    public void setParsed(Date parsed) {
        this.parsed = parsed;
    }
}
