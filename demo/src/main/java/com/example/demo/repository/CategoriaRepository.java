package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    //consulta 5
    // Liste as 8 categorias de produto que produziram o maior faturamento para a empresa
    // nos últimos seis meses de cada um dos últimos 5 anos.

    @Query(value = "WITH Faturamento AS ( " +
            "SELECT " +
            "c.id_categoria, " +
            "c.nome AS categoria, " +
            "SUM(ip.quantidade * ip.preco) AS faturamento, " +
            "YEAR(p.data_prazo) AS ano, " +
            "MONTH(p.data_prazo) AS mes " +
            "FROM " +
            "item_pedido ip " +
            "JOIN " +
            "pedido p ON ip.id_pedido = p.id_pedido " +
            "JOIN " +
            "produto pr ON ip.id_produto = pr.id_produto " +
            "JOIN " +
            "categoria c ON pr.id_categoria = c.id_categoria " +
            "WHERE " +
            "p.data_prazo >= DATEADD(MONTH, -6, GETDATE()) " +
            "AND YEAR(p.data_prazo) BETWEEN YEAR(GETDATE()) - 4 AND YEAR(GETDATE()) " +
            "GROUP BY " +
            "c.id_categoria, c.nome, YEAR(p.data_prazo), MONTH(p.data_prazo) " +
            "), " +
            "RankedFaturamento AS ( " +
            "SELECT " +
            "categoria, " +
            "faturamento, " +
            "ano, " +
            "mes, " +
            "RANK() OVER (PARTITION BY ano, mes ORDER BY faturamento DESC) AS rnk " +
            "FROM " +
            "Faturamento " +
            ") " +
            "SELECT " +
            "categoria, " +
            "SUM(faturamento) AS total_faturamento " +
            "FROM " +
            "RankedFaturamento " +
            "WHERE " +
            "rnk <= 8 " +
            "GROUP BY " +
            "categoria " +
            "ORDER BY " +
            "total_faturamento DESC", nativeQuery = true)
    List<Object[]> findCategoriaByFaturamento();
}
