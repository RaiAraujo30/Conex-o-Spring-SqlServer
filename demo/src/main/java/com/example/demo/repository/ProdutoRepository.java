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

    //consulta 8
    // Para cada produto, liste o preço de tabela do produto, o preço mínimo de venda
    //  do produto e o valor de mínimo que o produto já foi vendido em 2024 ou em 2023.
    @Query(value = "SELECT pr.preco_venda AS preco_tabela, " +
                        "MIN(pr.preco_venda_min) AS preco_minimo_venda, " +
                        "MIN(CASE WHEN YEAR(p.data_prazo) IN (2023, 2024) " +
                        "THEN pr.preco_venda_min END) AS preco_minimo_2023_2024 " +
                    "FROM produto pr " +
                    "LEFT JOIN " +
                        "item_pedido ip ON pr.id_produto = ip.id_produto " +
                    "LEFT JOIN " +
                        "pedido p ON ip.id_pedido = p.id_pedido " +
                    "GROUP BY pr.preco_venda", nativeQuery = true)
    List<Object[]> findProductsByPrecoVenda();


}
