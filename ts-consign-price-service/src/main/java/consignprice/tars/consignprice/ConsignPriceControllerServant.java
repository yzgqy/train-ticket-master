// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package consignprice.tars.consignprice;

import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.annotation.*;
import com.qq.tars.common.support.Holder;

@Servant
public interface ConsignPriceControllerServant {

	public String home();

	public ResponseDouble getPriceByWeightAndRegion(String weight, String isWithinRegion);

	public ResponseString getPriceInfo();

	public ResponseConsignPrice getPriceConfig();

	public ResponseConsignPrice modifyPriceConfig(ConsignPriceTars priceConfig);
}
