package k23cnt2.nhsDay06Lab8.nhsentity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NhsAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String name;
    private String description;
    private String slugUrl;
    private String email;
    private String phone;
    private String address;
    private Boolean isActive;

    // Quan hệ nhiều-mối nhiều với Book (bên bị mappedBy)
    @ManyToMany(mappedBy = "authors")
    private List<NhsBook> books = new ArrayList<>();
}
