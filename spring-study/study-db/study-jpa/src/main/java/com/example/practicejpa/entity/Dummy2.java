package com.example.practicejpa.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dummy_2")
public class Dummy2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String str;

    @Column
    private Integer num;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dummy1_id", referencedColumnName = "id", nullable = false)
    private Dummy1 dummy1;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dummy2")
    private List<Dummy3> dummy3List = new ArrayList<>();
}
