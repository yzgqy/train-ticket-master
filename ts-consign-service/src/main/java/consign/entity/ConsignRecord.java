package consign.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import consign.tars.consign.ConsignTars;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "consign_record")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsignRecord {

    @Id
    private UUID id;
    private UUID orderId;
    private UUID accountId;
    private String handleDate;
    private String targetDate;
    private String from;
    private String to;
    private String consignee;
    private String phone;
    private double weight;
    private double price;

    public ConsignTars toTars(){
        ConsignTars consignTars = new ConsignTars();
        consignTars.setId(this.id.toString());
        consignTars.setOrderId(this.orderId.toString());
        consignTars.setAccountId(this.accountId.toString());
        consignTars.setHandleDate(this.handleDate);
        consignTars.setTargetDate(this.targetDate);
        consignTars.setFrom(this.from);
        consignTars.setTo(this.to);
        consignTars.setConsignee(this.consignee);
        consignTars.setPhone(this.phone);
        consignTars.setWeight(this.weight);
        consignTars.setPrice(this.price);
        return consignTars;
    }

}
