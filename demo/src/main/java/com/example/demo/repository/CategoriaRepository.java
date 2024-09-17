package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    //consulta5 //consulta com problemas
    // Liste as 8 categorias de produto que produziram o maior faturamento para a empresa
    // nos últimos seis meses de cada um dos últimos 5 anos.
    @Query(value = "SELECT " +
                        "faturamento.ano, " +
                        "faturamento.nome_categoria, " +
                        "faturamento.faturamento_total " +
                    "FROM   (SELECT " +
                                "YEAR(p.data_prazo) AS ano, " +
                                "cat.nome AS nome_categoria, " +
                            "SUM(ip.quantidade * ip.preco) AS faturamento_total " +
                            "FROM " +
                                "pedido p " +
                            "JOIN " +
                                "item_pedido ip ON p.id_pedido = ip.id_pedido " +
                            "JOIN " +
                                "produto pr ON ip.id_produto = pr.id_produto " +
                            "JOIN " +
                                "categoria cat ON pr.id_categoria = cat.id_categoria " +
                            "WHERE " +
                                "p.status = 'Finalizado' " +
                                "AND p.data_prazo >= DATE_SUB(CURDATE(), INTERVAL 5 YEAR) " + //CURDATE não funciona no sqlserver
                                "AND MONTH(p.data_prazo) IN (7, 8, 9, 10, 11, 12) " + //DATE_SUB não funciona no sqlserver
                            "GROUP BY " +
                                "ano, cat.id_categoria " +
                            ") AS faturamento " +
                    "WHERE " +
                        "(faturamento.ano, faturamento.faturamento_total) IN ( " +
                        "SELECT " +
                            "sub.ano, " +
                            "sub.faturamento_total " +
                        "FROM ( " +
                            "SELECT " +
                                "YEAR(p.data_prazo) AS ano, " +
                                "SUM(ip.quantidade * ip.preco) AS faturamento_total, " +
                                "cat.id_categoria " +
                            "FROM " +
                                "pedido p " +
                            "JOIN " +
                                "item_pedido ip ON p.id_pedido = ip.id_pedido " +
                            "JOIN " +
                                "produto pr ON ip.id_produto = pr.id_produto " +
                            "JOIN " +
                                "categoria cat ON pr.id_categoria = cat.id_categoria " +
                            "WHERE " +
                                "p.status = 'Finalizado' " +
                                "AND p.data_prazo >= DATE_SUB(CURDATE(), INTERVAL 5 YEAR) " + //CURDATE e DATE_SUB
                                "AND MONTH(p.data_prazo) IN (7, 8, 9, 10, 11, 12) " +
                            "GROUP BY " +
                                "ano, cat.id_categoria " +
                            "ORDER BY " +
                                "ano, faturamento_total DESC " +
                                "LIMIT 8 " +
                            ") AS sub " +
                        ") " +
                    "ORDER BY " +
                        "faturamento.ano DESC, faturamento.faturamento_total DESC", 
       nativeQuery = true)
    List<Object[]> findCategoriaByFaturamento();

}