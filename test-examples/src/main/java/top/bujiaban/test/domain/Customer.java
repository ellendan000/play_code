package top.bujiaban.test.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Set<Project> projectSet = newHashSet();

    public void addProject(Project project) {
        projectSet.add(project);
    }
}
