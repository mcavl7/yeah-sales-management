package com.amigos.yeah.repositories;

import com.amigos.yeah.domain.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    
}
