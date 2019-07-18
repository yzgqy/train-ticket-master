// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package config.tars.config;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class ConfigTars {

	@TarsStructProperty(order = 0, isRequire = false)
	public String name = "";
	@TarsStructProperty(order = 1, isRequire = false)
	public String value = "";
	@TarsStructProperty(order = 2, isRequire = false)
	public String description = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ConfigTars() {
	}

	public ConfigTars(String name, String value, String description) {
		this.name = name;
		this.value = value;
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(name);
		result = prime * result + TarsUtil.hashCode(value);
		result = prime * result + TarsUtil.hashCode(description);
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
		if (!(obj instanceof ConfigTars)) {
			return false;
		}
		ConfigTars other = (ConfigTars) obj;
		return (
			TarsUtil.equals(name, other.name) &&
			TarsUtil.equals(value, other.value) &&
			TarsUtil.equals(description, other.description)
		);
	}

	public void writeTo(TarsOutputStream _os) {
		if (null != name) {
			_os.write(name, 0);
		}
		if (null != value) {
			_os.write(value, 1);
		}
		if (null != description) {
			_os.write(description, 2);
		}
	}


	public void readFrom(TarsInputStream _is) {
		this.name = _is.readString(0, false);
		this.value = _is.readString(1, false);
		this.description = _is.readString(2, false);
	}

}
