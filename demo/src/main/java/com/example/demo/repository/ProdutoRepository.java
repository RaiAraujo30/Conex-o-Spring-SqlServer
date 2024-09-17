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

    // consulta1  //consulta com problema
    // Listar nome, descrição e preço mínimo de produtos que são fornecidos por
    // fornecedores do Japão com mais de 120 em todos os armazéns.
    @Query(value = "SELECT " +
                        "pt.nome AS nome_produto, " +
                        "pt.descricao AS descricao_produto, " +
                        "p.preco_venda_min AS preco_minimo " +
                    "FROM " +
                        "produto p " +
                    "INNER JOIN " +
                        "fornecedor f ON p.id_fornecedor = f.id_fornecedor " +
                    "INNER JOIN " +
                        "estoque e ON p.id_produto = e.id_produto " +
                    "INNER JOIN " +
                        "produto_traducao pt ON p.id_produto = pt.id_produto " +
                    "WHERE " +
                        "f.pais = 'Japão' " +
                    "GROUP BY " +
                        "pt.nome, pt.descricao, p.preco_venda_min " +
                    "HAVING " +
                        "MIN(e.quantidade) > 120", 
nativeQuery = true)

    List<Object[]> findProductsByFornecedor();

    //consulta 4 //testar consulta
    // Liste a quantidade e a média da quantidade de produtos em cada estoque que possui 
    // pelo menos 200 produtos, agrupados pelo armazém, código do estoque e categoria do produto.
    @Query(value = "SELECT " +
                        "a.nome AS nome_armazem, " +
                        "e.codigo AS codigo_estoque, " +
                        "c.nome AS nome_categoria, " +
                        "SUM(e.quantidade) AS total_produtos, " +  // soma da quantidade total de produtos no estoque
                        "AVG(e.quantidade) AS media_produtos " +   // média da quantidade de produtos no estoque
                    "FROM " +
                        "estoque e " +
                    "INNER JOIN " +
                        "armazem a ON e.id_armazem = a.id_armazem " +
                    "INNER JOIN " +
                        "produto p ON e.id_produto = p.id_produto " +
                    "INNER JOIN " +
                        "categoria c ON p.id_categoria = c.id_categoria " +
                    "WHERE " +
                        "e.quantidade >= 200 " +  // estoques com pelo menos 200 produtos
                    "GROUP BY " +
                        "a.nome, e.codigo, c.nome " +
                    "HAVING " +
                        "SUM(e.quantidade) >= 200", 
       nativeQuery = true)

    List<Object[]> findProductsByEstoque();

}
