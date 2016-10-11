package gr.uom.port.simulation;

import gr.uom.port.model.CargoShip;

public interface IEvent {

    public static final int UNDEFINED_TIME = -1;

    public int getId();
    public CargoShip getShip();
    public double getStartTime();
    public double getWaitTime();
    public double getFinishTime();
}
