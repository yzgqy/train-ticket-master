// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package fdse.microservice.tars.rpc.priceconfig;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class PriceConfigTars {

	@TarsStructProperty(order = 0, isRequire = false)
	public String id = "";
	@TarsStructProperty(order = 1, isRequire = false)
	public String trainType = "";
	@TarsStructProperty(order = 2, isRequire = false)
	public String routeId = "";
	@TarsStructProperty(order = 3, isRequire = false)
	public double basicPriceRate = 0;
	@TarsStructProperty(order = 4, isRequire = false)
	public double firstClassPriceRate = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public double getBasicPriceRate() {
		return basicPriceRate;
	}

	public void setBasicPriceRate(double basicPriceRate) {
		this.basicPriceRate = basicPriceRate;
	}

	public double getFirstClassPriceRate() {
		return firstClassPriceRate;
	}

	public void setFirstClassPriceRate(double firstClassPriceRate) {
		this.firstClassPriceRate = firstClassPriceRate;
	}

	public PriceConfigTars() {
	}

	public PriceConfigTars(String id, String trainType, String routeId, double basicPriceRate, double firstClassPriceRate) {
		this.id = id;
		this.trainType = trainType;
		this.routeId = routeId;
		this.basicPriceRate = basicPriceRate;
		this.firstClassPriceRate = firstClassPriceRate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(id);
		result = prime * result + TarsUtil.hashCode(trainType);
		result = prime * result + TarsUtil.hashCode(routeId);
		result = prime * result + TarsUtil.hashCode(basicPriceRate);
		result = prime * result + TarsUtil.hashCode(firstClassPriceRate);
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
		if (!(obj instanceof PriceConfigTars)) {
			return false;
		}
		PriceConfigTars other = (PriceConfigTars) obj;
		return (
			TarsUtil.equals(id, other.id) &&
			TarsUtil.equals(trainType, other.trainType) &&
			TarsUtil.equals(routeId, other.routeId) &&
			TarsUtil.equals(basicPriceRate, other.basicPriceRate) &&
			TarsUtil.equals(firstClassPriceRate, other.firstClassPriceRate) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		if (null != id) {
			_os.write(id, 0);
		}
		if (null != trainType) {
			_os.write(trainType, 1);
		}
		if (null != routeId) {
			_os.write(routeId, 2);
		}
		_os.write(basicPriceRate, 3);
		_os.write(firstClassPriceRate, 4);
	}


	public void readFrom(TarsInputStream _is) {
		this.id = _is.readString(0, false);
		this.trainType = _is.readString(1, false);
		this.routeId = _is.readString(2, false);
		this.basicPriceRate = _is.read(basicPriceRate, 3, false);
		this.firstClassPriceRate = _is.read(firstClassPriceRate, 4, false);
	}

}
