package cl.telematic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Temp {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NotNull
    private RemoteXml originXml;

    private Double temperatureReading;

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
}
