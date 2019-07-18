// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.0.1.
// **********************************************************************

package fdse.microservice.tars.rpc.train;

import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.annotation.*;
import com.qq.tars.common.support.Holder;

@Servant
public interface TrainControllerPrx {

	public String home();

	public String home(@TarsContext java.util.Map<String, String> ctx);

	public void async_home(@TarsCallback TrainControllerPrxCallback callback);

	public void async_home(@TarsCallback TrainControllerPrxCallback callback, @TarsContext java.util.Map<String, String> ctx);

	public ResponseTrainType create(TrainTypeTars trainType);

	public ResponseTrainType create(TrainTypeTars trainType, @TarsContext java.util.Map<String, String> ctx);

	public void async_create(@TarsCallback TrainControllerPrxCallback callback, TrainTypeTars trainType);

	public void async_create(@TarsCallback TrainControllerPrxCallback callback, TrainTypeTars trainType, @TarsContext java.util.Map<String, String> ctx);

	public ResponseTrainType retrieve(String id);

	public ResponseTrainType retrieve(String id, @TarsContext java.util.Map<String, String> ctx);

	public void async_retrieve(@TarsCallback TrainControllerPrxCallback callback, String id);

	public void async_retrieve(@TarsCallback TrainControllerPrxCallback callback, String id, @TarsContext java.util.Map<String, String> ctx);

	public ResponseTrainType update(TrainTypeTars trainType);

	public ResponseTrainType update(TrainTypeTars trainType, @TarsContext java.util.Map<String, String> ctx);

	public void async_update(@TarsCallback TrainControllerPrxCallback callback, TrainTypeTars trainType);

	public void async_update(@TarsCallback TrainControllerPrxCallback callback, TrainTypeTars trainType, @TarsContext java.util.Map<String, String> ctx);

	public ResponseTrainType delete(String id);

	public ResponseTrainType delete(String id, @TarsContext java.util.Map<String, String> ctx);

	public void async_delete(@TarsCallback TrainControllerPrxCallback callback, String id);

	public void async_delete(@TarsCallback TrainControllerPrxCallback callback, String id, @TarsContext java.util.Map<String, String> ctx);

	public ResponseTrainTypes query();

	public ResponseTrainTypes query(@TarsContext java.util.Map<String, String> ctx);

	public void async_query(@TarsCallback TrainControllerPrxCallback callback);

	public void async_query(@TarsCallback TrainControllerPrxCallback callback, @TarsContext java.util.Map<String, String> ctx);
}
