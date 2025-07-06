package pe.edu.galaxy.apps.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import pe.edu.galaxy.apps.entity.PurchaseOrderEntity;

@ApplicationScoped
public class PurchaseOrderRepository implements PanacheRepositoryBase<PurchaseOrderEntity, String> {
}
