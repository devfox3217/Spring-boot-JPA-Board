package com.devfox.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "F_SETTING")
@Data
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "version")
    private String version;

    @Column(name = "point_b_read")
    private int contentReadPoint;

    @Column(name = "point_b_write")
    private int contentWritePoint;

    @Column(name = "point_b_reply")
    private int contentReplyPoint;

    @Column(name = "point_b_up")
    private int contentUpPoint;

    @Column(name = "point_article")
    private int articleReadPoint;

    @Column(name = "point_signup")
    private int signupPoint;

    @Column(name = "point_recommand")
    private int recommandPoint;

    @Column(name = "timestamp")
    private Date regdate;
}
