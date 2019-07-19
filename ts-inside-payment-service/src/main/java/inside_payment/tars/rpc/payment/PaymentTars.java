// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package inside_payment.tars.rpc.payment;

import com.qq.tars.protocol.util.*;
import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.*;
import com.qq.tars.protocol.tars.annotation.*;

@TarsStruct
public class PaymentTars {

	@TarsStructProperty(order = 0, isRequire = false)
	public String id = "";
	@TarsStructProperty(order = 1, isRequire = false)
	public String orderId = "";
	@TarsStructProperty(order = 2, isRequire = false)
	public String userId = "";
	@TarsStructProperty(order = 3, isRequire = false)
	public String price = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public PaymentTars() {
	}

	public PaymentTars(String id, String orderId, String userId, String price) {
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(id);
		result = prime * result + TarsUtil.hashCode(orderId);
		result = prime * result + TarsUtil.hashCode(userId);
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
		if (!(obj instanceof PaymentTars)) {
			return false;
		}
		PaymentTars other = (PaymentTars) obj;
		return (
			TarsUtil.equals(id, other.id) &&
			TarsUtil.equals(orderId, other.orderId) &&
			TarsUtil.equals(userId, other.userId) &&
			TarsUtil.equals(price, other.price) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		if (null != id) {
			_os.write(id, 0);
		}
		if (null != orderId) {
			_os.write(orderId, 1);
		}
		if (null != userId) {
			_os.write(userId, 2);
		}
		if (null != price) {
			_os.write(price, 3);
		}
	}


	public void readFrom(TarsInputStream _is) {
		this.id = _is.readString(0, false);
		this.orderId = _is.readString(1, false);
		this.userId = _is.readString(2, false);
		this.price = _is.readString(3, false);
	}

}
