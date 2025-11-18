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
public class NhsBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String name;
    private String description;
    private String slugUrl;

    private String imgUrl;   // ⭐ BẮT BUỘC — controller đang dùng trường này

    private Double quantity;
    private Double price;
    private Boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "NhsBook_NhsAuthor",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "authorId")
    )
    private List<NhsAuthor> authors = new ArrayList<>();
}
