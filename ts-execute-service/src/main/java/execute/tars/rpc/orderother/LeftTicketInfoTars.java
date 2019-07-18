// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package execute.tars.rpc.orderother;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class LeftTicketInfoTars {

	@TarsStructProperty(order = 0, isRequire = false)
	public java.util.List<TicketTars> soldTickets = null;

	public java.util.List<TicketTars> getSoldTickets() {
		return soldTickets;
	}

	public void setSoldTickets(java.util.List<TicketTars> soldTickets) {
		this.soldTickets = soldTickets;
	}

	public LeftTicketInfoTars() {
	}

	public LeftTicketInfoTars(java.util.List<TicketTars> soldTickets) {
		this.soldTickets = soldTickets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(soldTickets);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LeftTicketInfoTars)) {
			return false;
		}
		LeftTicketInfoTars other = (LeftTicketInfoTars) obj;
		return (
			TarsUtil.equals(soldTickets, other.soldTickets) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		if (null != soldTickets) {
			_os.write(soldTickets, 0);
		}
	}

	static java.util.List<TicketTars> cache_soldTickets;
	static { 
		cache_soldTickets = new java.util.ArrayList<TicketTars>();
		TicketTars var_1 = new TicketTars();
		cache_soldTickets.add(var_1);
	}

	public void readFrom(TarsInputStream _is) {
		this.soldTickets = (java.util.List<TicketTars>) _is.read(cache_soldTickets, 0, false);
	}

}
