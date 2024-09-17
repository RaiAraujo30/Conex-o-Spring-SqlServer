package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

// consulta9
// Qual é a quantidade e o percentual de clientes que realizaram compras no ano 2023,
//  que possuem limite de crédito maior que 700 e residem nos estados unidos? 
//  (a porcentagem deve ser calculada em relação a quantidade total de clientes que realizaram compras em 2023)
    @Query(value = "SELECT COUNT(DISTINCT c.id_cliente) AS quantidade_clientes, " +
                   "CASE " +
                        "WHEN (SELECT COUNT(DISTINCT c2.id_cliente) " +
                              "FROM cliente c2 " +
                              "JOIN pedido p2 ON c2.id_cliente = p2.id_cliente " +
                              "WHERE YEAR(p2.data_prazo) = 2023) = 0 THEN 0 " +
                        "ELSE (COUNT(DISTINCT c.id_cliente) * 100.0 / " +
                             "(SELECT COUNT(DISTINCT c2.id_cliente) " +
                              "FROM cliente c2 " +
                              "JOIN pedido p2 ON c2.id_cliente = p2.id_cliente " +
                              "WHERE YEAR(p2.data_prazo) = 2023)) " +
                              "END AS percentual_clientes " +
                  "FROM cliente c " +
                  "JOIN pedido p ON c.id_cliente = p.id_cliente " +
                  "WHERE YEAR(p.data_prazo) = 2023 " +
                  "AND c.limite_credito > 700 " +
                  "AND c.pais = 'Estados Unidos'", nativeQuery = true)
     List<Object[]> findClientesByPedidos();


    // consulta2
    //  Liste o nome, limite de crédito, cidade e estado dos clientes americanos que já realizaram
    //   mais de 20 pedidos com o valor total de cada pedido maior do que 10.000 e que nunca compraram
    //    produtos da categoria “Smartphone”.
    @Query(value = "SELECT c.nome, c.limite_credito, c.cidade, c.estado " +
            "FROM cliente c " +
            "JOIN pedido p ON c.id_cliente = p.id_cliente " +
            "JOIN item_pedido ip ON p.id_pedido = ip.id_pedido " +
            "JOIN produto pr ON ip.id_produto = pr.id_produto " +
            "JOIN categoria cat ON pr.id_categoria = cat.id_categoria " +
            "WHERE c.pais = 'Estados Unidos' " +
            "AND ip.preco > 10000 " +
            "AND cat.nome != 'Smartphone' " +
            "GROUP BY c.id_cliente, c.nome, c.limite_credito, c.cidade, c.estado " +
            "HAVING COUNT(p.id_pedido) > 20", nativeQuery = true)
    List<Object[]> findClientesByCategoria();

    // consulta10
    // Liste o nome completo e o endereço dos clientes americanos que realizaram um total de compras
    // superior a 50 mil em cada um dos seguintes anos: 2024, 2023 e 2021 e que nunca realizaram compras
    // de produtos de mais de uma categoria em uma mesma compra.
    @Query(value = "SELECT " +
                        "c.nome AS nome_cliente, " +
                        "c.rua, " +
                        "c.numero, " +
                        "c.complemento, " +
                        "c.bairro, " +
                        "c.cidade, " +
                        "c.estado, " +
                        "c.pais " +
                    "FROM " +
                        "cliente c " +
                    "JOIN " +
                        "pedido p ON c.id_cliente = p.id_cliente " +
                    "JOIN " +
                        "item_pedido ip ON p.id_pedido = ip.id_pedido " +
                    "JOIN " +
                        "produto pr ON ip.id_produto = pr.id_produto " +
                    "JOIN " +
                        "categoria cat ON pr.id_categoria = cat.id_categoria " +
                    "WHERE " +
                        "c.pais = 'Estados Unidos' " +
                        "AND p.status = 'Finalizado' " +
                        "AND p.data_prazo IN ('2024', '2023', '2021') " +
                    "GROUP BY " +
                        "c.id_cliente, c.nome, c.rua, c.numero, c.complemento, c.bairro, c.cidade, c.estado, c.pais " +
                    "HAVING " +
                        "SUM(ip.preco * ip.quantidade) > 50000 " +
                        "AND COUNT(DISTINCT pr.id_categoria) = 1 " +
                        "AND COUNT(DISTINCT p.data_prazo) = 3", nativeQuery = true)
    List<Object[]> findClientesAmericanos();

}
