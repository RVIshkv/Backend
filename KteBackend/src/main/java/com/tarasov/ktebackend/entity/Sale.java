package com.tarasov.ktebackend.entity;

import com.tarasov.ktebackend.utils.CheckNumberGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;


@Table
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private LocalDate saleDate;
    @GeneratorType(type = CheckNumberGenerator.class, when = GenerationTime.INSERT)
    @Column(nullable = false, updatable = false)
    private String checkNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale")
    private List<Position> positions;

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", saleDate=" + saleDate +
                ", checkNumber='" + checkNumber + '\'' +
                '}';
    }
}
