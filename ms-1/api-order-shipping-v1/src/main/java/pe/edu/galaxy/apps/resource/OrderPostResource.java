package pe.edu.galaxy.apps.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import pe.edu.galaxy.apps.dto.PurchaseOrderRequestDto;
import pe.edu.galaxy.apps.service.OrderPostService;

@Path("/v1/order-post")
public class OrderPostResource {

  private static final Logger LOG = Logger.getLogger(OrderPostResource.class);

  @Inject
  OrderPostService orderPostService;

  @POST
  public Uni<Response> create(PurchaseOrderRequestDto request) {
    return orderPostService
        .create(request)
            .invoke(result -> {
              var message = result.getValue() ? "pedido  creado" : "pedido ya existente";
              LOG.info(message);
            })
            .map(response ->
                    Response
                            .status(response.getValue() ? Response.Status.CREATED : Response.Status.OK)
                            .entity(response.getKey())
                            .build()
            )
            .onFailure(RuntimeException.class)
            .recoverWithItem(error ->
                    Response.serverError().entity(error.getMessage()).build()
            );
  }
}
