package order.entity;

import order.tars.order.LeftTicketInfoTars;
import order.tars.order.TicketTars;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LeftTicketInfo {
    @Valid
    @NotNull
    private Set<Ticket> soldTickets;

    public LeftTicketInfo(){

    }

    public Set<Ticket> getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(Set<Ticket> soldTickets) {
        this.soldTickets = soldTickets;
    }

    @Override
    public String toString() {
        return "LeftTicketInfo{" +
                "soldTickets=" + soldTickets +
                '}';
    }

    public LeftTicketInfoTars toTars(){
        LeftTicketInfoTars leftTicketInfoTars = new LeftTicketInfoTars();
        List<TicketTars> ticketTarsSet = new ArrayList<>();
        if(this.soldTickets!=null)
            for(Ticket ticket:this.soldTickets){
                ticketTarsSet.add(ticket.toTars());
            }
        leftTicketInfoTars.setSoldTickets(ticketTarsSet);
        return leftTicketInfoTars;
    }
}
