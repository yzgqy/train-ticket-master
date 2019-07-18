package assurance.entity;

import assurance.tars.assurance.AssuranceTars;
import assurance.tars.assurance.AssuranceTypeTars;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Document(collection = "assurance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Assurance {

    @Id
    private UUID id;

    @NotNull
    //which order the assurance is related to
    private UUID orderId;

    @NotNull
    //the type of assurance
    private AssuranceType type;

    public Assurance(){

    }

    public Assurance(AssuranceTars assuranceTars){
        this.id = UUID.fromString(assuranceTars.getId());
        this.orderId = UUID.fromString(assuranceTars.getOrderId());
        AssuranceTypeTars assuranceTypeTars = assuranceTars.getType();
        if(assuranceTypeTars!=null)
            this.type = AssuranceType.TRAFFIC_ACCIDENT;
    }

    public Assurance(UUID id, UUID orderId, AssuranceType type){
        this.id = id;
        this.orderId = orderId;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public AssuranceType getType() {
        return type;
    }

    public void setType(AssuranceType type) {
        this.type = type;
    }

    public AssuranceTars toTars(){
        AssuranceTars assuranceTars = new AssuranceTars();
        assuranceTars.setId(this.id.toString());
        assuranceTars.setOrderId(this.orderId.toString());
        if(this.type!=null)
            assuranceTars.setType(this.type.toTars());
        return assuranceTars;
    }

}
