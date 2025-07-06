package pe.edu.galaxy.apps.service.kafka;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;
import pe.edu.galaxy.apps.entity.ShipperEntity;
import pe.edu.galaxy.apps.model.OrderShipping;
import pe.edu.galaxy.apps.repository.PurchaseOrderRepository;
import pe.edu.galaxy.apps.service.PurchaseOrderService;

import java.math.BigDecimal;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class PurchaseOrderConsumerService implements PurchaseOrderService {

	private final static Logger log = Logger.getLogger(PurchaseOrderConsumerService.class);

	@Inject
	PurchaseOrderRepository purchaseOrderRepository;

	@Override
	@Incoming("order-shipping")
	public CompletionStage<Void> processPurchaseOrderEvent(Message<OrderShipping> orderShippingEvent) {
		var orderShipping = orderShippingEvent.getPayload();

		return Uni.createFrom().deferred(() ->
						Panache.withTransaction(() ->
								purchaseOrderRepository
										.findById(orderShipping.getShipperId())
										.onItem().ifNotNull().invoke(() ->
												log.infof("Shipping already exists %s", orderShipping.getShipperId())
										)
										.onItem().ifNull().switchTo(() -> {
											var entity = purchaseOrderRepository.persist(toEntity(orderShipping));
											log.infof("Shipping inserted: %s", orderShipping.getShipperId());
											return entity;
										})
						)
				)
				.onFailure().invoke(err -> {
					log.errorf("Error get event %s: %s", orderShipping.getShipperId(), err.getMessage());
				})
				.onItem().transformToUni(v ->
						Uni.createFrom().completionStage(orderShippingEvent.ack())
				)
				.onFailure().recoverWithUni(err ->
						Uni.createFrom().completionStage(orderShippingEvent.nack(err))
				)
				.replaceWithVoid()
				.subscribeAsCompletionStage();
	}

	private ShipperEntity toEntity(OrderShipping orderShipping) {
		ShipperEntity shipperEntity = new ShipperEntity();
		shipperEntity.setShipperId(orderShipping.getShipperId());
		shipperEntity.setPurchaseOrder(orderShipping.getPurchaseOrderId());
		shipperEntity.setCarrier(orderShipping.getCarrier());
		shipperEntity.setTrackingNumber(orderShipping.getTrackingNumber());
		shipperEntity.setAmountShipper(orderShipping.getAmountShipper() != null
				? new BigDecimal(orderShipping.getAmountShipper())
				: null);
		shipperEntity.setStatus(orderShipping.getStatusId());
		shipperEntity.setNoteShipper(orderShipping.getNoteShipper());
		return shipperEntity;
	}
}
