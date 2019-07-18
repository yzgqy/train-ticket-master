// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package assurance.tars.assurance;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class AssuranceTypeTars {

	@TarsStructProperty(order = 0, isRequire = false)
	public int index = "";
	@TarsStructProperty(order = 1, isRequire = false)
	public String name = "";
	@TarsStructProperty(order = 2, isRequire = false)
	public double price = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public AssuranceTypeTars() {
	}

	public AssuranceTypeTars(int index, String name, double price) {
		this.index = index;
		this.name = name;
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(index);
		result = prime * result + TarsUtil.hashCode(name);
		result = prime * result + TarsUtil.hashCode(price);
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
		if (!(obj instanceof AssuranceTypeTars)) {
			return false;
		}
		AssuranceTypeTars other = (AssuranceTypeTars) obj;
		return (
			TarsUtil.equals(index, other.index) &&
			TarsUtil.equals(name, other.name) &&
			TarsUtil.equals(price, other.price) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		_os.write(index, 0);
		if (null != name) {
			_os.write(name, 1);
		}
		_os.write(price, 2);
	}


	public void readFrom(TarsInputStream _is) {
		this.index = _is.read(index, 0, false);
		this.name = _is.readString(1, false);
		this.price = _is.read(price, 2, false);
	}

}
