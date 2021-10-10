package com.amigos.yeah.services;

import java.util.Optional;
import java.util.Date;

import com.amigos.yeah.domain.Pedido;
import com.amigos.yeah.repositories.PedidoRepository;
import com.amigos.yeah.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amigos.yeah.domain.ItemPedido;
import com.amigos.yeah.domain.PagamentoComBoleto;
import com.amigos.yeah.domain.enums.EstadoPagamento;
import com.amigos.yeah.repositories.ItemPedidoRepository;
import com.amigos.yeah.repositories.PagamentoRepository;

@Service
public class PedidoService {
    
    @Autowired
    PedidoRepository repository;

    @Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repository.findById(id);
        
        // Retorna o objeto ou gerará uma excessão personalizada
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

}
