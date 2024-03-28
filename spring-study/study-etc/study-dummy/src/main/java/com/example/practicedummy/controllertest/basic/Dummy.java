package com.example.practicedummy.controllertest.basic;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "dummy")
@Entity
public class Dummy {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Type(type = "yes_no")
    private Boolean bool;
}
