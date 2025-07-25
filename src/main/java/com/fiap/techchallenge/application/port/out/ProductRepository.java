package com.fiap.techchallenge.application.port.out;

import com.fiap.techchallenge.domain.document.ProductDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductDocument, ObjectId> {
}
