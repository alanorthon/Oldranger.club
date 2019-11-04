package ru.java.mentor.oldranger.club.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recovery_token")
public class PasswordRecoveryToken {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "iss_date", nullable = false)
    private LocalDateTime issueDate;

    @Column(name = "token", nullable = false)
    private String token;

    public PasswordRecoveryToken(User user, LocalDateTime issueDate, String token) {
        this.user = user;
        this.issueDate = issueDate;
        this.token = token;
    }
}
