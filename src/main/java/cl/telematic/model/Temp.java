package cl.telematic.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Temp {

    public Temp(RemoteXml remoteXml) {
        originXml = remoteXml;
        created = new Date();
    }

    public Temp() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NotNull
    private RemoteXml originXml;

    private Double temperatureReading;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date realRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RemoteXml getOriginXml() {
        return originXml;
    }

    public void setOriginXml(RemoteXml originXml) {
        this.originXml = originXml;
    }

    public Double getTemperatureReading() {
        return temperatureReading;
    }

    public void setTemperatureReading(Double temperatureReading) {
        this.temperatureReading = temperatureReading;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getRealRead() {
        return realRead;
    }

    public void setRealRead(Date realRead) {
        this.realRead = realRead;
    }
}
