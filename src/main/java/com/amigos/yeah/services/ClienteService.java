package com.amigos.yeah.services;

import java.util.List;
import java.util.Optional;

import com.amigos.yeah.domain.Cidade;
import com.amigos.yeah.domain.Cliente;
import com.amigos.yeah.domain.Endereco;
import com.amigos.yeah.domain.enums.TipoCliente;
import com.amigos.yeah.dto.ClienteDTO;
import com.amigos.yeah.dto.ClienteNewDTO;
import com.amigos.yeah.repositories.ClienteRepository;
import com.amigos.yeah.repositories.EnderecoRepository;
import com.amigos.yeah.services.exceptions.DataIntegrityException;
import com.amigos.yeah.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        
        // Retorna o objeto ou gerará uma excessão personalizada
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Cliente que possui pedidos!");
        }

    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    // Método para paginação
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    // Método auxiliar para transformar um ClienteDTO em Cliente
    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

}
