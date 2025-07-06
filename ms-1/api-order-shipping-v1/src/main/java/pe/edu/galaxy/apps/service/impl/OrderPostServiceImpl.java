package pe.edu.galaxy.apps.service.impl;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;
import pe.edu.galaxy.apps.dto.PurchaseOrderRequestDto;
import pe.edu.galaxy.apps.dto.PurchaseOrderResponseDto;
import pe.edu.galaxy.apps.mapper.PurchaseOrderMapper;
import pe.edu.galaxy.apps.repository.OrderRepository;
import pe.edu.galaxy.apps.service.OrderPostService;

import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class OrderPostServiceImpl implements OrderPostService {


  @Inject
  OrderRepository orderRepository;


  private final static Logger log = Logger.getLogger(OrderPostService.class);


    /**
   * Metodo que crea un pedido y realiza in envio de la misma, si no existe, pero si existe la obtiene.
   *
   * @param orderPostRequestDto Objeto a recibir
   * @return Objeto creado u obtenido
   */
  @Override
  @WithTransaction
  public Uni<Map.Entry<PurchaseOrderResponseDto, Boolean>>  create(PurchaseOrderRequestDto orderPostRequestDto) {

      return orderRepository
              .findById(orderPostRequestDto.purchaseOrderId())
              .onItem().ifNotNull().transform(existing ->
                      Map.entry(PurchaseOrderMapper.entityToResponse(existing), Boolean.FALSE)
              )
              .onItem().ifNull().switchTo(() ->
                      orderRepository
                              .persist(PurchaseOrderMapper.requestToEntity(orderPostRequestDto))
                              .replaceWith(Map.entry(PurchaseOrderMapper.requestToResponse(orderPostRequestDto), Boolean.TRUE))
              )
              .onFailure(PersistenceException.class)
              .recoverWithUni(() ->
                      orderRepository.findById(orderPostRequestDto.purchaseOrderId())
                              .onItem().ifNotNull().transform(existing ->
                                      Map.entry(PurchaseOrderMapper.entityToResponse(existing), Boolean.FALSE))
                              .onItem().ifNull().switchTo(() ->
                                      Uni.createFrom().failure(() -> new RuntimeException("ZipCode not found after failure")))
              );

  }




}
