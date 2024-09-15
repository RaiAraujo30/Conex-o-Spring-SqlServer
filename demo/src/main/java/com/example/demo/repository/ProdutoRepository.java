package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Produto;

//consulta 7
// Liste o nome, a data de garantia e todas as descrições dos produtos cuja diferença
// entre o preço de venda e o preço de venda mínimo é menor que 2000 E que estão armazenados
// em pelo menos 5 armazéns distintos.
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query(value = "SELECT PT.nome, PT.descricao, P.data_garantia " +
                   "FROM produto P, produto_traducao PT " +
                   "WHERE (P.preco_venda - P.preco_venda_min)< 2000 AND " +
                   "P.id_produto = PT.id_produto AND " +
                   "P.id_produto IN ( SELECT E.id_produto " +
                                    "FROM estoque E " +
                                    "GROUP BY E.id_produto " +
                                    "HAVING COUNT(distinct E.id_armazem)>=5)", nativeQuery = true)
                   List<Object[]> findProductsByarmazens();

}
