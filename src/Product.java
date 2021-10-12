import java.util.Date;

public class Product {

    private Date date;
    private long proceed;

    public Product(Date date, long proceed) {
        this.date = date;
        this.proceed = proceed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getProceed() {
        return proceed;
    }

    public void setProceed(long proceed) {
        this.proceed = proceed;
    }
}
