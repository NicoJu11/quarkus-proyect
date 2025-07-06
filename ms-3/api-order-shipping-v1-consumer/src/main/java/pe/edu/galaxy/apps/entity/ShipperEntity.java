package pe.edu.galaxy.apps.entity;



import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipper")
public class ShipperEntity {

    @Id
    @Column(name = "shipper_id", length = 10, nullable = false)
    private String shipperId;

    @Column(name = "purchase_order_id", length = 10, nullable = false)
    private String  purchaseOrder;

    @Column(name = "date_shipper")
    private LocalDateTime dateShipper;

    @Column(name = "date_estimated_delivery")
    private LocalDateTime dateEstimatedDelivery;

    @Column(name = "carrier", length = 50, nullable = false)
    private String carrier;

    @Column(name = "tracking_number", length = 50)
    private String trackingNumber;

    @Column(name = "amount_shipper", precision = 10, scale = 2, nullable = false)
    private BigDecimal amountShipper;


    @Column(name = "status_id", length = 10, nullable = false)
    private String status;

    @Column(name = "note_shipper", columnDefinition = "TEXT")
    private String noteShipper;

    // Getters and Setters
    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public LocalDateTime getDateShipper() {
        return dateShipper;
    }

    public void setDateShipper(LocalDateTime dateShipper) {
        this.dateShipper = dateShipper;
    }

    public LocalDateTime getDateEstimatedDelivery() {
        return dateEstimatedDelivery;
    }

    public void setDateEstimatedDelivery(LocalDateTime dateEstimatedDelivery) {
        this.dateEstimatedDelivery = dateEstimatedDelivery;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public BigDecimal getAmountShipper() {
        return amountShipper;
    }

    public void setAmountShipper(BigDecimal amountShipper) {
        this.amountShipper = amountShipper;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoteShipper() {
        return noteShipper;
    }

    public void setNoteShipper(String noteShipper) {
        this.noteShipper = noteShipper;
    }
}