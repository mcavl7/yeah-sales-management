package com.amigos.yeah.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.amigos.yeah.domain.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    @Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();
}
