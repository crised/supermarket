package cl.telematic.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Electrical {

    public Electrical(RemoteXml remoteXml) {
        originXml = remoteXml;
        created = new Date();
    }

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NotNull
    private RemoteXml originXml;

    @NotNull
    private Double energyReading;

    @NotNull
    private Double powerReading;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date created;


    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
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

    public Double getEnergyReading() {
        return energyReading;
    }

    public void setEnergyReading(Double energyReading) {
        this.energyReading = energyReading;
    }

    public Double getPowerReading() {
        return powerReading;
    }

    public void setPowerReading(Double powerReading) {
        this.powerReading = powerReading;
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
