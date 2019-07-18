// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package fdse.microservice.tars.station;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class StationTars {

	@TarsStructProperty(order = 0, isRequire = false)
	public String id = "";
	@TarsStructProperty(order = 1, isRequire = false)
	public String name = "";
	@TarsStructProperty(order = 2, isRequire = false)
	public int stayTime = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStayTime() {
		return stayTime;
	}

	public void setStayTime(int stayTime) {
		this.stayTime = stayTime;
	}

	public StationTars() {
	}

	public StationTars(String id, String name, int stayTime) {
		this.id = id;
		this.name = name;
		this.stayTime = stayTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(id);
		result = prime * result + TarsUtil.hashCode(name);
		result = prime * result + TarsUtil.hashCode(stayTime);
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
		if (!(obj instanceof StationTars)) {
			return false;
		}
		StationTars other = (StationTars) obj;
		return (
			TarsUtil.equals(id, other.id) &&
			TarsUtil.equals(name, other.name) &&
			TarsUtil.equals(stayTime, other.stayTime) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		if (null != id) {
			_os.write(id, 0);
		}
		if (null != name) {
			_os.write(name, 1);
		}
		_os.write(stayTime, 2);
	}


	public void readFrom(TarsInputStream _is) {
		this.id = _is.readString(0, false);
		this.name = _is.readString(1, false);
		this.stayTime = _is.read(stayTime, 2, false);
	}

}
