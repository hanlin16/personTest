package conf;

import org.springframework.stereotype.Component;

@Component
public class Customer {

    private String custName = "Ross";
    private String custGender = "female";
    private Integer custAge = 17;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustGender() {
        return custGender;
    }

    public void setCustGender(String custGender) {
        this.custGender = custGender;
    }

    public Integer getCustAge() {
        return custAge;
    }

    public void setCustAge(Integer custAge) {
        this.custAge = custAge;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custName='" + custName + '\'' +
                ", custGender='" + custGender + '\'' +
                ", custAge=" + custAge +
                '}';
    }
}
