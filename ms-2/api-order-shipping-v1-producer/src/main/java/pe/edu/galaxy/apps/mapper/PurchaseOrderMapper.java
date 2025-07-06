package pe.edu.galaxy.apps.mapper;

import pe.edu.galaxy.apps.dto.PurchaseOrderRequestDto;
import pe.edu.galaxy.apps.dto.PurchaseOrderResponseDto;
import pe.edu.galaxy.apps.entity.PurchaseOrderEntity;

public class PurchaseOrderMapper {

    public static PurchaseOrderResponseDto entityToResponse(PurchaseOrderEntity entity) {
        return new PurchaseOrderResponseDto(
                entity.getPurchaseOrderId(),
                entity.getClientId(),
                entity.getAddressShipperId(),
                entity.getDatePurchaseOrder(),
                entity.getSubtotal(),
                entity.getIva(),
                entity.getAmountTotal(),
                entity.getStatusOrder().name(),
                entity.getPaymentMethod().name()
        );
    }


    public static PurchaseOrderEntity requestToEntity(PurchaseOrderRequestDto request) {
        return new PurchaseOrderEntity(
                request.purchaseOrderId(),
                request.clintId(),
                request.addressShipperId(),
                request.datePurchaseOrder(),
                request.subtotal(),
                request.iva(),
                request.amountTotal(),
                PurchaseOrderEntity.StatusOrder.valueOf(request.statusOrder()),
                PurchaseOrderEntity.PaymentMethod.valueOf(request.paymentMethod())
        );
    }

    public static PurchaseOrderResponseDto requestToResponse(PurchaseOrderRequestDto request) {
        return new PurchaseOrderResponseDto(
                request.purchaseOrderId(),
                request.clintId(),
                request.addressShipperId(),
                request.datePurchaseOrder(),
                request.subtotal(),
                request.iva(),
                request.amountTotal(),
                request.statusOrder(),
                request.paymentMethod()
        );
    }

}