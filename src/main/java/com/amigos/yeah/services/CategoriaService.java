package com.amigos.yeah.services;

import java.util.List;
import java.util.Optional;

import com.amigos.yeah.domain.Categoria;
import com.amigos.yeah.dto.CategoriaDTO;
import com.amigos.yeah.repositories.CategoriaRepository;
import com.amigos.yeah.services.exceptions.DataIntegrityException;
import com.amigos.yeah.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        
        // Retorna o objeto ou gerará uma excessão personalizada
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
        }

    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    // Método para paginação
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    // Método auxiliar para transformar um CategoriaDTO em Categoria 
    public Categoria fromDTO(CategoriaDTO objDTO) {
        return new Categoria(objDTO.getId(), objDTO.getNome());
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
    }

}
