// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package adminorder.tars.rpc.order;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class TicketTars {

	@TarsStructProperty(order = 0, isRequire = false)
	public int seatNo = 0;
	@TarsStructProperty(order = 1, isRequire = false)
	public String startStation = "";
	@TarsStructProperty(order = 2, isRequire = false)
	public String destStation = "";

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getDestStation() {
		return destStation;
	}

	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	public TicketTars() {
	}

	public TicketTars(int seatNo, String startStation, String destStation) {
		this.seatNo = seatNo;
		this.startStation = startStation;
		this.destStation = destStation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(seatNo);
		result = prime * result + TarsUtil.hashCode(startStation);
		result = prime * result + TarsUtil.hashCode(destStation);
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
		if (!(obj instanceof TicketTars)) {
			return false;
		}
		TicketTars other = (TicketTars) obj;
		return (
			TarsUtil.equals(seatNo, other.seatNo) &&
			TarsUtil.equals(startStation, other.startStation) &&
			TarsUtil.equals(destStation, other.destStation) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		_os.write(seatNo, 0);
		if (null != startStation) {
			_os.write(startStation, 1);
		}
		if (null != destStation) {
			_os.write(destStation, 2);
		}
	}


	public void readFrom(TarsInputStream _is) {
		this.seatNo = _is.read(seatNo, 0, false);
		this.startStation = _is.readString(1, false);
		this.destStation = _is.readString(2, false);
	}

}
