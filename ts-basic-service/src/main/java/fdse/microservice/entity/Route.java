package fdse.microservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fdse.microservice.tars.rpc.route.RouteTars;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

    private String id;

    private ArrayList<String> stations;

    private ArrayList<Integer> distances;

    private String startStationId;

    private String terminalStationId;

    public Route() {
        //Default Constructor
    }
    public Route(RouteTars routeTars) {
        //Default Constructor
        this.id = routeTars.getId();
        this.stations = (ArrayList<String>)routeTars.getStations();
        this.distances = (ArrayList<Integer>)routeTars.getDistances();
        this.startStationId = routeTars.getStartStationId();
        this.terminalStationId = routeTars.getTerminalStationId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getStations() {
        return stations;
    }

    public void setStations(ArrayList<String> stations) {
        this.stations = stations;
    }

    public ArrayList<Integer> getDistances() {
        return distances;
    }

    public void setDistances(ArrayList<Integer> distances) {
        this.distances = distances;
    }

    public String getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(String startStationId) {
        this.startStationId = startStationId;
    }

    public String getTerminalStationId() {
        return terminalStationId;
    }

    public void setTerminalStationId(String terminalStationId) {
        this.terminalStationId = terminalStationId;
    }

    public RouteTars toTars(){
        RouteTars routeTars = new RouteTars();
        routeTars.setId(this.id);
        routeTars.setStations(this.stations);
        routeTars.setDistances(this.distances);
        routeTars.setStartStationId(this.startStationId);
        routeTars.setTerminalStationId(this.terminalStationId);
        return routeTars;
    }
}
