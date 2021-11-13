package com.devfox.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "U_POINT")
@Data
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "log")
    private String log;

    @OneToOne(mappedBy = "username")
    private User user;
}
