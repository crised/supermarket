package cl.telematic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Electrical {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NotNull
    private RemoteXml originXml;

    private Double energyReading;

    private Double powerReading;

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
}
