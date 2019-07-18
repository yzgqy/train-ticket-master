// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package order.tars.rpc.station;

import com.qq.tars.rpc.protocol.tars.support.TarsAbstractCallback;

public abstract class StationControllerPrxCallback extends TarsAbstractCallback {

	public abstract void callback_home(String ret);

	public abstract void callback_query(ResponseStations ret);

	public abstract void callback_create(ResponseStation ret);

	public abstract void callback_update(ResponseStation ret);

	public abstract void callback_delete(ResponseStation ret);

	public abstract void callback_queryForStationId(ResponseString ret);

	public abstract void callback_queryForIdBatch(ResponseStringList ret);

	public abstract void callback_queryById(ResponseString ret);

	public abstract void callback_queryForNameBatch(ResponseStringList ret);

}
