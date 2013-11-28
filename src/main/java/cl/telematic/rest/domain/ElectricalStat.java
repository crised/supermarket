package cl.telematic.rest.domain;

public class ElectricalStat {

    private Double energy;
    private Double power;

    public ElectricalStat(Double energy, Double power) {
        this.energy = energy;
        this.power = power;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }
}
