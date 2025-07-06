package pe.edu.galaxy.apps.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrderEntity {

    @Id
    @Column(name = "purchase_order_id", nullable = false)
    private String purchaseOrderId;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "address_shipper_id", nullable = false)
    private String addressShipperId;

    @Column(name = "date_purchase_order", nullable = false)
    private LocalDateTime datePurchaseOrder;

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @Column(name = "iva", nullable = false)
    private BigDecimal iva;

    @Column(name = "amounttotal", nullable = false)
    private BigDecimal amountTotal;

    @Column(name = "status_order", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @Column(name = "payment_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    // Getters and Setters

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAddressShipperId() {
        return addressShipperId;
    }

    public void setAddressShipperId(String addressShipperId) {
        this.addressShipperId = addressShipperId;
    }

    public LocalDateTime getDatePurchaseOrder() {
        return datePurchaseOrder;
    }

    public void setDatePurchaseOrder(LocalDateTime datePurchaseOrder) {
        this.datePurchaseOrder = datePurchaseOrder;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public PurchaseOrderEntity(String purchaseOrderId, String clientId, String addressShipperId, LocalDateTime datePurchaseOrder, BigDecimal subtotal, BigDecimal iva, BigDecimal amountTotal, StatusOrder statusOrder, PaymentMethod paymentMethod) {
        this.purchaseOrderId = purchaseOrderId;
        this.clientId = clientId;
        this.addressShipperId = addressShipperId;
        this.datePurchaseOrder = datePurchaseOrder;
        this.subtotal = subtotal;
        this.iva = iva;
        this.amountTotal = amountTotal;
        this.statusOrder = statusOrder;
        this.paymentMethod = paymentMethod;
    }


    public PurchaseOrderEntity() {
        // Default constructor
    }

    // Enums for status_order and payment_method
    public enum StatusOrder {
        PENDIENTE, PROCESANDO, ENVIADO, COMPLETADO, CANCELADO
    }

    public enum PaymentMethod {
        TARJETA, TRANSFERENCIA, EFECTIVO, PAYPAL
    }
}
