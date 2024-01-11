package com.taxi.app.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "coord")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CoordEntityId.class)
public class CoordEntity {

    @Id
    private Double latitude;

    @Id
    private Double longitude;

    @NotEmpty
    @Column(name = "coord_name")
    private String coordName;

    @NotEmpty
    private String cep;

    public static CoordEntity of(Double latitude, Double longitude) {
        final CoordEntity coordEntity = new CoordEntity();
        coordEntity.longitude = longitude;
        coordEntity.latitude = latitude;
        return coordEntity;
    }

}
