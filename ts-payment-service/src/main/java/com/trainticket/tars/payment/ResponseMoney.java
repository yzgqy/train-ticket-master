// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package com.trainticket.tars.payment;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class ResponseMoney {

	@TarsStructProperty(order = 0, isRequire = false)
	public int status = 0;
	@TarsStructProperty(order = 1, isRequire = false)
	public String msg = "";
	@TarsStructProperty(order = 2, isRequire = false)
	public MoneyTars data = null;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public MoneyTars getData() {
		return data;
	}

	public void setData(MoneyTars data) {
		this.data = data;
	}

	public ResponseMoney() {
	}

	public ResponseMoney(int status, String msg, MoneyTars data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(status);
		result = prime * result + TarsUtil.hashCode(msg);
		result = prime * result + TarsUtil.hashCode(data);
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
		if (!(obj instanceof ResponseMoney)) {
			return false;
		}
		ResponseMoney other = (ResponseMoney) obj;
		return (
			TarsUtil.equals(status, other.status) &&
			TarsUtil.equals(msg, other.msg) &&
			TarsUtil.equals(data, other.data) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		_os.write(status, 0);
		if (null != msg) {
			_os.write(msg, 1);
		}
		if (null != data) {
			_os.write(data, 2);
		}
	}

	static MoneyTars cache_data;
	static { 
		cache_data = new MoneyTars();
	}

	public void readFrom(TarsInputStream _is) {
		this.status = _is.read(status, 0, false);
		this.msg = _is.readString(1, false);
		this.data = (MoneyTars) _is.read(cache_data, 2, false);
	}

}
