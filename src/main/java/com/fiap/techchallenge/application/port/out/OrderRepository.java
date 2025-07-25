package com.fiap.techchallenge.application.port.out;

import com.fiap.techchallenge.domain.document.OrderDocument;
import com.fiap.techchallenge.domain.enumerate.OrderStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderDocument, ObjectId> {

    List<OrderDocument> findByStatus(OrderStatus status);
}
