package com.rentagirlfriend.entities;

import com.rentagirlfriend.utils.EyeColor;
import com.rentagirlfriend.utils.HairColor;
import com.rentagirlfriend.utils.Race;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@Table(name = "information")
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private long info_id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "hair_color")
    private HairColor hair_color;
    @Column(name = "eye_color")
    private EyeColor eye_color;
    @Column(name = "racial_identity")
    private Race racial_identity;

}
