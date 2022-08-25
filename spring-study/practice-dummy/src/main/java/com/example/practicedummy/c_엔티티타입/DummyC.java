package com.example.practicedummy.c_엔티티타입;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "dummy_c")
@Entity
public class DummyC {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Type(type = "yes_no")
    private Boolean bool;
}
