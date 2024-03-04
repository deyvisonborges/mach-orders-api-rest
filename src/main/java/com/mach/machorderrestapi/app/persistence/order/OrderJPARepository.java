package com.mach.machorderrestapi.app.persistence.order;

//import com.mach.machorderrestapi.app.database.repository.order.entity.OrderItemJPAEntity;
import com.mach.machorderrestapi.app.persistence.order.entity.OrderJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;
import java.util.UUID;

@Repository
public interface OrderJPARepository extends JpaRepository<OrderJPAEntity, UUID> {
//	List<OrderItemJPAEntity> findOrderItemsByOrderId(UUID orderId);
}
