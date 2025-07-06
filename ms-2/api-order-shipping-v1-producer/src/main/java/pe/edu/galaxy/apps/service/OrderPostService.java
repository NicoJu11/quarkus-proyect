package pe.edu.galaxy.apps.service;

import io.smallrye.mutiny.Uni;
import pe.edu.galaxy.apps.dto.PurchaseOrderRequestDto;
import pe.edu.galaxy.apps.dto.PurchaseOrderResponseDto;

import java.util.Map;

public interface OrderPostService {

    Uni<Map.Entry<PurchaseOrderResponseDto, Boolean>> create(PurchaseOrderRequestDto orderPostRequestDto);




}
