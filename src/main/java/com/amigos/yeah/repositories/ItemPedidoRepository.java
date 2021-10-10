package com.amigos.yeah.repositories;

import com.amigos.yeah.domain.ItemPedido;
import com.amigos.yeah.domain.ItemPedidoPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {
    
}
