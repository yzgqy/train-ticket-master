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
public class OrderSecurityTars {

	@TarsStructProperty(order = 0, isRequire = false)
	public int orderNumInLastOneHour = 0;
	@TarsStructProperty(order = 1, isRequire = false)
	public int orderNumOfValidOrder = 0;

	public int getOrderNumInLastOneHour() {
		return orderNumInLastOneHour;
	}

	public void setOrderNumInLastOneHour(int orderNumInLastOneHour) {
		this.orderNumInLastOneHour = orderNumInLastOneHour;
	}

	public int getOrderNumOfValidOrder() {
		return orderNumOfValidOrder;
	}

	public void setOrderNumOfValidOrder(int orderNumOfValidOrder) {
		this.orderNumOfValidOrder = orderNumOfValidOrder;
	}

	public OrderSecurityTars() {
	}

	public OrderSecurityTars(int orderNumInLastOneHour, int orderNumOfValidOrder) {
		this.orderNumInLastOneHour = orderNumInLastOneHour;
		this.orderNumOfValidOrder = orderNumOfValidOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TarsUtil.hashCode(orderNumInLastOneHour);
		result = prime * result + TarsUtil.hashCode(orderNumOfValidOrder);
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
		if (!(obj instanceof OrderSecurityTars)) {
			return false;
		}
		OrderSecurityTars other = (OrderSecurityTars) obj;
		return (
			TarsUtil.equals(orderNumInLastOneHour, other.orderNumInLastOneHour) &&
			TarsUtil.equals(orderNumOfValidOrder, other.orderNumOfValidOrder) 
		);
	}

	public void writeTo(TarsOutputStream _os) {
		_os.write(orderNumInLastOneHour, 0);
		_os.write(orderNumOfValidOrder, 1);
	}


	public void readFrom(TarsInputStream _is) {
		this.orderNumInLastOneHour = _is.read(orderNumInLastOneHour, 0, false);
		this.orderNumOfValidOrder = _is.read(orderNumOfValidOrder, 1, false);
	}

}
