package com.rentagirlfriend.entities;

import com.rentagirlfriend.utils.EyeColor;
import com.rentagirlfriend.utils.HairColor;
import com.rentagirlfriend.utils.Height;
import com.rentagirlfriend.utils.Race;
import com.rentagirlfriend.utils.Weight;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private long profile_id;
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
    @Column(name = "weight_category")
    private Weight weight_category;
    @Column(name = "height_category")
    private Height height_category;


}
