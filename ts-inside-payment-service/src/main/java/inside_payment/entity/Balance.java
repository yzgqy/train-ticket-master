package inside_payment.entity;

import inside_payment.tars.insidepayment.BalanceTars;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class Balance {
    @Valid
    @NotNull
    private String userId;

    @Valid
    @NotNull
    private String balance;

    public Balance(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public BalanceTars toTars(){
        BalanceTars balanceTars = new BalanceTars();
        balanceTars.setBalance(this.balance);
        balanceTars.setUserId(this.userId);
        return balanceTars;
    }
}
