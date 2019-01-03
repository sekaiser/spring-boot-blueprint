package de.seka.news.modules.articles.jpa.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "article")
@Builder
@Getter
@Setter
public class ArticleEntity extends BaseEntity {

    @Column
    private String header;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String text;

    // assumption: keywords are separated by spaces
    @Column
    private String keywords;

    // assumption author consists of firstname lastname; authors are seprated by ','
    @Column
    private String authors;

    public Optional<String> getAuthors() {
        return Optional.ofNullable(this.authors);
    }

    public Optional<String> getKeywords() {
        return Optional.ofNullable(this.keywords);
    }
}