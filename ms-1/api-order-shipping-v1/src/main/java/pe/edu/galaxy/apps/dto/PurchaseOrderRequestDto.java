package pe.edu.galaxy.apps.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PurchaseOrderRequestDto(
        String purchaseOrderId,
        String clintId,
        String addressShipperId,
        LocalDateTime datePurchaseOrder,
        BigDecimal subtotal,
        BigDecimal iva,
        BigDecimal amountTotal,
        String statusOrder,
        String paymentMethod
) {
}