package gr.uom.port.simulation;

import gr.uom.port.model.CargoShip;

import java.util.Arrays;

public class SimReport {

    private Simulation simulation;
    private int totalShips = 0;
    private double totalTime = 0;
    private double evaluationIndex = 0;

    public SimReport(final Simulation simulation) {
        this.simulation = simulation;
    }

    public void addShipServiced(final CargoShip ship) {
        totalShips++;
        updateEvaluationIndex(ship);
    }

    public void setTotalTime(final double totalTime) {
        this.totalTime = totalTime;
    }

    private void updateEvaluationIndex(final CargoShip ship) {
        evaluationIndex += ship.getLength() * ship.getPriority().getValue();
    }

    public int getTotalShips() {
        return totalShips;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public double getEvaluationIndex() {
        return evaluationIndex * totalShips;
    }

    public String getSmallReport() {
        return this.toString();
    }

    public String getCurrentStatus() {
        return simulation.toString();
    }

    @Override
    public String toString() {
        return "----------------------------------------------------------------------------------------------------" + "\n" +
                "Simulation Parameters: " + Arrays.toString(simulation.getParameters()) + "\n" +
                "Time Limit (hours): " + simulation.getTimeLimit() + "\n" +
                "Evaluation Index: " + (long) getEvaluationIndex() + "\n" +
                "Total Ships: " + getTotalShips() + "\n" +
                "----------------------------------------------------------------------------------------------------";
    }
}
