package com.fiap.techchallenge.application.port.out;

import com.fiap.techchallenge.domain.document.ClientDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<ClientDocument, ObjectId> {

    ClientDocument findByCpf(String cpf);

}
