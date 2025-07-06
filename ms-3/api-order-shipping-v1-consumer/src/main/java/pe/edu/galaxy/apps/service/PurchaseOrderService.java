package pe.edu.galaxy.apps.service;


import org.eclipse.microprofile.reactive.messaging.Message;
import pe.edu.galaxy.apps.model.OrderShipping;
import java.util.concurrent.CompletionStage;

public interface PurchaseOrderService {

  CompletionStage<Void> processPurchaseOrderEvent(Message<OrderShipping> orderShippingEvent);
}
