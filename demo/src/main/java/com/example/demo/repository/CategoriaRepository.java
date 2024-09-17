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
            "SUM(ip.quantidade * ip.preco) AS total_faturamento, " +
            "YEAR(p.data_prazo) AS ano, " +
            "MONTH(p.data_prazo) AS mes " +
            "FROM " +
            "item_pedido ip " +
            "JOIN pedido p ON ip.id_pedido = p.id_pedido " +
            "JOIN produto pr ON ip.id_produto = pr.id_produto " +
            "JOIN categoria c ON pr.id_categoria = c.id_categoria " +
            "WHERE p.data_prazo >= DATEADD(MONTH, -6, GETDATE()) " +  // últimos 6 meses
            "AND YEAR(p.data_prazo) BETWEEN YEAR(GETDATE()) - 4 AND YEAR(GETDATE()) " +  // últimos 5 anos
            "GROUP BY c.id_categoria, c.nome, YEAR(p.data_prazo), MONTH(p.data_prazo) " +
            "), TopCategorias AS ( " +
            "SELECT F1.ano, F1.mes, F1.categoria, F1.total_faturamento, " +
            "DENSE_RANK() OVER (PARTITION BY F1.ano, F1.mes ORDER BY F1.total_faturamento DESC) AS ranking " +
            "FROM Faturamento F1 " +
            ") " +
            "SELECT ano, mes, categoria, total_faturamento " +
            "FROM TopCategorias " +
            "WHERE ranking <= 8 " +
            "ORDER BY ano DESC, mes DESC, total_faturamento DESC", 
            nativeQuery = true)
    List<Object[]> findCategoriaByFaturamento();
}
